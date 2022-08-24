import java.util.Random;

public class QuickSort 
{
	private static <E> int partition(E[] items, int i, int j) 
	{
		int mid =  (j+i)/2;
		E pivot =  items[mid];

		Utils.swap(items, mid, j);

		int last = j;
		while(i < j) {
			while(i < j && Utils.compare(items[i], pivot) <= 0) {
				++i;
			}

			while(i < j && Utils.compare(items[j], pivot) >= 0) {
				--j;
			}
			Utils.swap(items, i, j);
//				++i;
//				--j;		
		}
		Utils.swap(items, i, last);

		return i;
	}

	private static <E> void sort(E[] items, int i, int j) 
	{	
		if(i < j) {
			int piv = partition(items, i, j);
			sort(items, i, piv-1);
			sort(items, piv + 1, j);
		}
	}

	public static <E> void sort(E[] items) 
	{
		sort(items, 0, items.length - 1);
	}
}
