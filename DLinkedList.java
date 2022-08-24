/**
 * Implementation of a Doubly-Linked List.
 *
 * @author  Andry R.
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DLinkedList<E> implements Iterable<E>
{
	private class Node
	{
		E data;
		Node next;
		Node prev;

		Node(E d) 
		{
			data = d;
			next = null;
			prev = null;
		}
	}

	/**
	 * The first node of the list
	 */
	private Node head;

	/**
	 * The last node of the list
	 */
	private Node tail;

	/**
	 * Constructs empty double linked list
	 */
	public DLinkedList() 
	{
		head = new Node(null);
		tail = head;
	}

	/**
	 * Returns true if list is empty
	 * 
	 * @return true if list is empty
	 */
	public boolean isEmpty() 
	{
		return head.next == null;
	}

	/**
	 * Adds the given element to the front of the list.
	 * 
	 * @param value		the added element
	 */
	public void addFirst(E value) 
	{
		Node node = new Node(value);
		node.next = head.next;
		head.next = node;
		node.prev = head;

		if (tail.equals(head)) {
			tail = node;
		}
		else {
			node.next.prev = node;
		}
	}

	/**
	 * Adds the given element towards the end of the list
	 * 
	 * @param value		the added element
	 */
	public void addLast(E value) 
	{
		Node node = new Node(value);
		tail.next = node;
		node.prev = tail;
		tail = node;
	}

	/**
	 * Adds given item at the given index
	 * 
	 * @param index		index where item is placed
	 * @param item		item to be placed
	 */
	public void add(int index, E item) 
	{
		if (index < 0) {
			throw new IndexOutOfBoundsException();
		}

		if (index == 0) {
			this.addFirst(item);
			return;
		}

		Node curr = head.next;
		int curInd = 0;

		while (curr != null) {
			if (curInd == index -1) {
				Node node = new Node(item);
				curr.prev.next = node;
				node.prev = curr.prev;
				node.next = curr;
				curr.prev = node;
				return;
			}
			curr = curr.next;
			++curInd;
		}
		if (curr == null) {
			throw new IndexOutOfBoundsException();
		}
	}

	/**
	 * Returns the first element of the list.
	 * 
	 * @return	the first element of the list
	 * @throws NoSuchElementException
	 */
	public E getFirst() 
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return head.next.data;
	}

	/**
	 * Returns the last element of the list.
	 * 
	 * @return the last element of the list
	 * @throws 	NoSuchElementException
	 */
	public E getLast() 
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}
		return tail.data;
	}

	/**
	 * Returns element from given index
	 * 
	 * @param index		index at which element is location
	 * @return	element at given index
	 * @throws 	IndexOutOfBoundsException
	 */
	public E get(int index) 
	{
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException();
		}

		Node curr = head.next;
		int curInd = 0;

		while (curInd != index) {
			curr = curr.next;
			++curInd;
			if (curr == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		return curr.data;
	}

	/**
	 * Changes current item at given index to the given item
	 * & returns the old item.
	 * 
	 * @param index 	index at which element is changed
	 * @param item		item to be placed at index
	 * @return	old item that was replaced
	 * @throws 	IndexOutOfBoundsException when list is empty
	 */
	public E set(int index, E item) 
	{
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException();
		}

		Node curr = head.next;
		E oldItem = null;
		int curInd = 0;

		while (curInd != index) {
			curr = curr.next;
			++curInd;
			if (curr == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		oldItem = curr.data;
		curr.data = item;
		return oldItem;
	}

	/**
	 * Returns true if given item is in the list
	 * 
	 * @param item		item to be checked in the list
	 * @return	true if given item is in the list
	 * @throws 	NoSuchElementException
	 */
	public boolean contains(E item) 
	{		
		Node curr = head.next;
		int curInd = 0;

		while (curr != null) {
			if (curr.data.equals(item)) {
				return true;
			}
			curr = curr.next;
			++curInd;
		}

		return false;
	}

	/**
	 * Removes all elements from the list
	 * 
	 */
	public void clear() 
	{
		Node curr = head.next;
		while (curr != null) {
			curr.prev.next = null;
			curr.prev = null;
			curr = curr.next;
		}
		tail = head;
	}

	/**
	 * Returns a String version of list
	 * 
	 * @return	a String version of list
	 */
	public String toStringNext() 
	{
		String str = "";
		Node curr = head.next;
		while (curr != null) {
			str += curr.data + " ";
			curr = curr.next;
		}

		str = "[" + str.trim() + "]";

		return str;
	}

	/**
	 * Returns a String version of list by going from tail to head
	 * 
	 */
	public String toStringPrev() 
	{
		String str = "";
		Node curr = tail;

		while (curr != head) {
			str = curr.data + " " + str;
			curr = curr.prev;
		}

		str = "[" + str.trim() + "]";

		return str;
	}

	private class DLinkedIterator implements Iterator<E>
	{
		private Node curr;
		private boolean removable;
		public DLinkedIterator() 
		{
			curr = head;
			removable = false;
		}

		public boolean hasNext() 
		{
			return curr.next != null;
		}

		public E next() 
		{
			if (this.hasNext()) {
				E element = curr.next.data;
				curr = curr.next;
				removable = true;
				return element;
			}
			else {
				throw new NoSuchElementException();
			}
		}

		/**
		 * Removes item at which iterator is located
		 * 
		 * @throws IllegelStateException when remove() is called successively
		 * or remove is called after Iterator is constructed.
		 */
		public void remove() 
		{
			if (removable == false) {
				throw new IllegalStateException();
			}
			else {
				curr.prev.next = curr.next;
				if (curr.next != null) {
					curr.next.prev = curr.prev;
				}
				else {
					tail = tail.prev;
				}
				removable = false;
			}
		}
	}

	/**
	 * Returns iterator for the list.
	 * 
	 * @return	iterator for the list
	 */
	public Iterator<E> iterator() 
	{
		return new DLinkedIterator();
	}

	/**
	 * Verifies if item is in the list using iterator.
	 * 
	 * @param item		item to be checked
	 * @return	true if given item is in the list
	 * @throws 	NoSuchElementException 
	 */
	public boolean containsIter(E item) 
	{	
		for (E element : this) {
			if (element.equals(item)) {
				return true;
			}
		}
		return false;
	}

	//Assignment 3 starts here:

	/**
	 * Removes and returns the first element from the list.
	 * 
	 * @return	the first element which was removed
	 * @throws	NoSuchElementException when list is empty
	 */
	public E removeFirst() 
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		Node nodeFst = head.next;
		Node nodeScd = nodeFst.next;
		head.next = nodeFst.next;

		if (head.next == null) {
			tail = head;
		}
		else {
			nodeScd.prev = head;	
		}

		return nodeFst.data;
	}

	/**
	 * Removes and returns the last element from the list.
	 * 
	 * @return	the last element which was removed
	 * @throws	NoSuchElementException when list is empty
	 */
	public E removeLast() 
	{
		if (this.isEmpty()) {
			throw new NoSuchElementException();
		}

		E lastData = tail.data;

		tail.prev.next = null;
		tail = tail.prev;
		return lastData;
	}

	/**
	 * Removes item located at given index.
	 * 
	 * @param index		index of item to be removed
	 * @return	removed item
	 * @throws 	IndexOutOfBoundsException when item is empty
	 */
	public E remove(int index) 
	{
		if (this.isEmpty()) {
			throw new IndexOutOfBoundsException();
		}

		int currInd = 0;
		Node curr = head.next;

		while (currInd != index) {
			++currInd;
			curr = curr.next;

			if (curr == null) {
				throw new IndexOutOfBoundsException();
			}
		}
		if (curr == tail) {
			curr.prev.next = null;
			tail = tail.prev;
		}
		else {
			curr.prev.next = curr.next;
			curr.next.prev = curr.prev;
		}

		return curr.data;
	}

	/**
	 * Removes the first item identical to given item from the list 
	 * 
	 * @param item		item to remove
	 * @return	true is removal successfully done
	 */
	public boolean removeItem(E item) 
	{	
		if (this.isEmpty()) {
			return false;
		}
		Node curr = head.next;

		while (curr != null) {
			if (item.equals(curr.data)) { 
				curr.prev.next = curr.next;
				if (curr.next != null) {
					curr.next.prev = curr.prev;
				}
				else {
					tail = tail.prev;
				}
				return true;
			}
			else {
				curr = curr.next;
			}	
		}
		return false;
	}

	/**
	 * Removes all element identical to given item from the list.
	 * 
	 * @param item		item to be removed
	 * @return	true is removal successfully done
	 */
	public boolean removeAll(E item) 
	{
		if (this.isEmpty()) {
			return false;
		}

		boolean removed = false;
		Node curr = head.next;

		while (curr != null) {
			if (item.equals(curr.data)) {
				curr.prev.next = curr.next;
				if (curr.next != null) {
					curr.next.prev = curr.prev;
				}
				else {
					tail = tail.prev;
				}
				removed = true;
			}
			curr = curr.next;
		}
		return removed;
	}

	/**
	 * Checks if given list is identical to current list
	 * 
	 * @param list 		list to compare
	 * @return	true if list is identical to current list
	 */
	public boolean equals(Object list) 
	{
		if (this == list) {
			return true;
		}
		else if (!(list instanceof DLinkedList)) {
			return false;
		}
		else {
			DLinkedList<E> otherList = (DLinkedList<E>) list;
			Iterator<E> iter1 = this.iterator();
			Iterator<E> iter2 = otherList.iterator();
			
			if (otherList.isEmpty() && this.isEmpty()) {
				return true;
			}
			
			while (iter1.hasNext() && iter2.hasNext()) {			
				if (iter1.next() != iter2.next()) {
					return false;
				}
				else if (!iter1.hasNext() && !iter2.hasNext()) {
					return true;	
				}
			}
			return false ;
		}		
	}

	/**
	 * Adds given collection into current list starting at given index
	 * 
	 * @param index		index at which adding starts
	 * @param collection	collection that supports iterators
	 */
	public void addAll(int index, Iterable<E> collection) 
	{
		if (this.isEmpty() && (index > 0 || index < 0)) {
			throw new IndexOutOfBoundsException();
		}

		int currInd = 0;
		Node curr = head; //starting with the head

		while (currInd != index) {
			++currInd;
			curr = curr.next;
		}

		for (E element: collection) {
			Node nodeNew = new Node(element);

			if (curr.next != null) {
				curr.next.prev = nodeNew;
				nodeNew.next = curr.next;
			}
			curr.next = nodeNew;
			nodeNew.prev = curr;
			curr = curr.next;
		}
		if (curr.next == null) {
			tail = curr;
		}
		return;
	}

	/**
	 * Removes all element identical to given item from the list using iterator.
	 * 
	 * @param item		item to be removed
	 * @return	true is removal successfully done
	 */
	public boolean removeAllIter(E item) 
	{	
		boolean removed = false;
		if (this.isEmpty()) {
			return removed;
		}
		else {
			DLinkedIterator iter =  (DLinkedList<E>.DLinkedIterator) this.iterator();
			while (iter.hasNext()) {
				E element = iter.next();
				if (element.equals(item)) {
					iter.remove();
					removed = true;
				}
			}
		}
		return removed;
	}
}
