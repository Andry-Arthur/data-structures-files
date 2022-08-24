import java.util.Iterator;
import java.util.NoSuchElementException;

public class LHashMap<K, V> implements Iterable<V>
{
	private class Entry
	{
		K key;
		V value;

		public Entry(K key, V value) 
		{
			this.key = key;
			this.value = value;
		}

		public String toString() 
		{
			return key+":"+value;
		}
	}

	private Object[] mainArray;

	private double loadFactor;

	private int size;

	private Entry empty;
	private Entry deleted;

	public LHashMap(int initialCapacity, double loadFactor) 
	{
		mainArray = new Object[initialCapacity];
		empty = new Entry(null, null);
		deleted = new Entry(null, null);

		for (int i = 0; i < mainArray.length; ++i) {
			mainArray[i] = empty;
		}

		this.loadFactor = loadFactor;
		size = 0;
	}

	private class HashMapIterator implements Iterator<V> 
	{
		private int currPos;
		Entry curr;
		public HashMapIterator() 
		{
			currPos = 0;
			curr = (Entry) mainArray[currPos];
		}

		@Override
		public boolean hasNext() 
		{
			while (currPos < mainArray.length - 1) {
				Entry next = (Entry) mainArray[currPos];

				if (next == empty || next == deleted) {
					++currPos;
				}
				else {
					return true;
				}	
			}
			return false;
		}

		@Override
		public V next() 
		{
			if (this.hasNext()) {
				Entry currEntry = (Entry) mainArray[currPos];
				V value = currEntry.value;
				++currPos;
				return value;
			}
			else {
				throw new NoSuchElementException();
			}
		}
	}

	/**
	 * Returns true if CHashMap is empty
	 * 
	 * @return	true if CHashMap is empty
	 */
	public boolean isEmpty() 
	{
		if (size == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of entries in the CHashMap
	 * 
	 * @return	the number of entries in the CHashMap
	 */
	public int size() 
	{
		return size;
	}

	/**
	 * Put given entry at its specified key an returns either the old value at the key
	 * 
	 * @param key
	 * @param value
	 * @return	previous value found at key
	 */
	public V put(K key, V value) 
	{
		int bucketInd = key.hashCode() % mainArray.length;
		V oldValue = null;
		Entry newEntry = new Entry(key, value);
		Entry currBucket = (Entry) mainArray[bucketInd];

		if (currBucket == empty) {
			mainArray[bucketInd] = newEntry;
			++size;
			this.rehash();
		}
		else if (key.equals(currBucket.key)) {
			oldValue = currBucket.value;
			currBucket.value = value;
		}
		else {
			int ind = bucketInd + 1;
			while (ind <= mainArray.length - 1) {
				currBucket = (Entry) mainArray[ind];
				if (mainArray[ind] == empty || mainArray[ind] == deleted) {
					mainArray[ind] = newEntry;
					++size;
					this.rehash();
					return oldValue;
				}
				else if (key.equals(currBucket.key)) {
					oldValue = currBucket.value;
					currBucket.value = value;
					return oldValue;
				}
				else {
					if (ind == mainArray.length - 1) {
						ind = 0;
					}
					else {
						++ind;
					}
				}
			}
		}
		return oldValue;
	}

	/**
	 * Returns value with given key
	 * 
	 * @param key		key to be searched for
	 * @return	value with given key
	 */
	public V get(K key) 
	{
		V value = null;

		int bucketInd = key.hashCode() % mainArray.length;
		Entry currBucket = (Entry) mainArray[bucketInd];

		if (currBucket == empty) {
			return value;
		}
		else if (key.equals(currBucket.key)) {
			value = currBucket.value;
			return value;
		}
		else {
			int ind = bucketInd + 1;
			while (ind <= mainArray.length - 1) {
				currBucket = (Entry) mainArray[ind];
				if (mainArray[ind] == empty || mainArray[ind] == deleted) {
					return value;
				}
				else if (key.equals(currBucket.key)) {
					value = currBucket.value;
					return value;
				}
				else {
					if (ind == mainArray.length - 1) {
						ind = 0;
					}
					else {
						++ind;
					}
				}
			}		
		}
		return value; 
	}

	/**
	 * Removes entry with given key and returns the value of this entry
	 * 
	 * @param key		key to be searched for
	 * @return	value of this entry
	 */
	public V remove(K key) 
	{
		V value = null;

		int bucketInd = key.hashCode() % mainArray.length;
		Entry currBucket = (Entry) mainArray[bucketInd];

		if (currBucket == empty || currBucket == deleted) {
			return value;
		}
		else if (key.equals(currBucket.key)) {
			value = currBucket.value;
			mainArray[bucketInd] = deleted;
			--size;
			return value;
		}
		else {
			int ind = bucketInd + 1;
			while (ind <= mainArray.length - 1) {
				currBucket = (Entry) mainArray[ind];
				if (mainArray[ind] == empty || mainArray[ind] == deleted) {
					return value;
				}
				else if (key.equals(currBucket.key)) {
					value = currBucket.value;
					mainArray[ind] = deleted;
					--size;
					return value;
				}
				else {
					if (ind == mainArray.length - 1) {
						ind = 0;
					}
					else {
						++ind;
					}
				}
			}		
		}
		return value;
	}

	/**
	 * Empties the HashMap
	 * 
	 */
	public void clear() 
	{
		mainArray = new Object[mainArray.length];

		for (int i = 0; i < mainArray.length; ++i) {
			mainArray[i] = empty;
		}

		size = 0;
	}

	/**
	 * Returns true if map has entry with given key
	 * 
	 * @param key		key to be searched for
	 * @return 	true if map has entry with given key
	 */
	public boolean containsKey(K key) 
	{
		for (int i = 0; i < mainArray.length; ++i) {
			Entry currEntry = (Entry) mainArray[i];
			if (key.equals(currEntry.key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns true if map has entry with given value
	 * 
	 * @param value		value to be searched for
	 * @return	true if map has entry with given value
	 */
	public boolean containsValue(V value)
	{
		Iterator<V> iterator = this.iterator();

		while (iterator.hasNext()) {
			V next = iterator.next();
			System.out.println(next);
			if (value.equals(next)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Expands the capacity and rehashes the entries after a new entry is added
	 * 
	 */
	private void rehash() 
	{
		if (size >= loadFactor * mainArray.length) {
			Object[] oldArray = mainArray;

			mainArray = new Object[mainArray.length * 2];
			
			for (int i = 0; i < mainArray.length; ++i) {
				mainArray[i] = empty;
			}

			for (int i = 0; i < oldArray.length; ++i) {
				Entry e = (Entry) oldArray[i];

				if (e == empty || e == deleted) {
					mainArray[i] = e;
				}
				else {
					int currInd = e.key.hashCode() % mainArray.length;
					while (mainArray[currInd] != empty) {
						++currInd;
					}
					mainArray[currInd] = e;
				}
			}
		}
	}

	/**
	 * Returns string version of the HashMap
	 * 
	 * @return	string version of the HashMap
	 */
	public String toString() 
	{
		String str = "" ;

		for (int i = 0; i < mainArray.length;  ++i) {
			Entry currEntry = (Entry) mainArray[i];
			if (currEntry == empty) {
				str = str + " E";
			}
			else if (currEntry == deleted) {
				str = str + " D";
			}
			else {
				str = str + " " + currEntry.toString();
			}
		}

		str = size + ":[" + str.trim() + "]"; 

		return str;
	}

	@Override
	public Iterator<V> iterator() 
	{
		return new HashMapIterator();
	}
}
