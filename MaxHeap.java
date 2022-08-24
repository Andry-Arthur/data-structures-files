
public class MaxHeap<E>
{
	private E[] items;
	private static int size;
	
	public MaxHeap(E[] theItems) 
	{
		items = theItems;
		size = theItems.length;
		makeHeap();
	}

	private void pushDown(int i) 
	{
		int indL;
		int indR;
		while(!isLeaf(i)) {
			indL = 2*i+1;
			indR = 2*i+2;
			E parent = items[i];
			E left = items[indL];
			if(indR < size) {
				E right = items[indR];
				
				if(Utils.compare(right, left) > 0) {
					if(Utils.compare(parent, right) < 0) {
						Utils.swap(items, i, indR);
					}
				}
				else {
					if(Utils.compare(parent, left) < 0) {
						Utils.swap(items, i, indL);
					} 
				}
			}
			else {
				if(Utils.compare(parent, left) < 0) {
					Utils.swap(items, i, indL);
				} 
			}
			++i;
		}
	}

	private void makeHeap() 
	{
		int startInd = (size-1)/2;
		for(int i = startInd; i >= 0; --i) {
			pushDown(i);
		}
	}

	public E pop() 
	{
		E max = items[0];
		items[0] = items[size -1];
		pushDown(0);
		return max;
	}

	private boolean isLeaf(int i) 
	{
		return (2*i + 1) >= size;
	}

	public String toString()
	{
		return Utils.toString(items, size);
	}

	public static <E> void sort(E[] items) 
	{
		MaxHeap<E> heap = new MaxHeap(items);
		for(int i = items.length -1; i > 0; --i) {
			items[size - 1] = heap.pop();
			size = i;
		}
	}
}

