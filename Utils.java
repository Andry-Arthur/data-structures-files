import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.lang.Comparable;
import java.util.Random;

public class Utils
{
    // returns String representation of the given primitive array
    // only the first n numbers are included in the String
    public static <E> String toString(E[] items, int n)
    {
     	return asList(items).subList(0, n).toString();
    }

    // returns String representation of the given primitive array
    public static <E> String toString(E[] items)
    {
     	return asList(items).subList(0, items.length).toString();
    }

    // exchanges the values at indices i,j
    public static <E> void swap(E[] items, int i, int j)
    {
     	E temp   = items[i];
     	items[i] = items[j];
     	items[j] = temp;
    }

    // returns 1, 0, +1 depending on the comparison a <, ==, > b
    public static <E> int compare(E a, E b)
    {
	return ((Comparable<E>) a).compareTo(b);
    }
    
    // returns Doble primitive array of n elements such that:
    // * if order is 'i' the array elements have values 1,2,...n
    // * if order is 'd' the array elements have values n,n-1,n-2,...,1
    // * if order is 'r' the array elements have random values
    //
    public static Double[] generateArray( int n, char order )
    {
        Double[] numbers = new Double[n];
        for (int i = 0; i < n; ++i) {
            if (order == 'i') {
                numbers[i] = (double) (i+1);
            }
            else if (order == 'd') {
                numbers[i] = (double) (n-i);
            }
            else if (order == 'r') {
		Random random = new Random(n);
                numbers = generateArray(n , 'i');
		ArrayList<Double> numbersList = asList(numbers);
		Collections.shuffle(numbersList, random);
		return numbersList.toArray(numbers);
            }
        }
        return numbers;
    }

    // returns String representation of the given primitive array
    public static <E> ArrayList<E> asList(E[] items)
    {
	ArrayList<E> result = new ArrayList<E>();
	for (E value : items) {
	    result.add(value);
	}
	return result;
    }

    // returns String representation of the given primitive array
    public static <E> boolean isSorted(E[] items)
    {
	for (int i = 0; i < items.length - 1; ++i) {
	    if (Utils.compare(items[i], items[i+1]) > 0) {
		return false;
	    }
	}
	return true;
    }
}