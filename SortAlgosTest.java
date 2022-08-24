import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SortAlgosTest {

	@Test
	void test_HeapSort() {
		Double[] it1 = Utils.generateArray(10, 'i');
		Integer[] it2 = {0, 42, 81, 2, 90, 30, 23};
		
		MaxHeap<Double> max1 = new MaxHeap(it1);
		max1.pop();
		
		MaxHeap<Integer> max2 = new MaxHeap(it2);
		max2.pop();
		assertEquals(max2.toString(), Utils.toString(it2));
		
		it1 = Utils.generateArray(10, 'i');
		MaxHeap.sort(it1);
		assertTrue(Utils.isSorted(it1));
		assertEquals(Utils.toString(it1), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		Double[] it3 = Utils.generateArray(10, 'r');
		MaxHeap.sort(it3);
		assertTrue(Utils.isSorted(it3));
		assertEquals(Utils.toString(it3), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		it3 = Utils.generateArray(10, 'd');
		MaxHeap.sort(it3);
		assertTrue(Utils.isSorted(it3));
		assertEquals(Utils.toString(it3), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		Integer[] it4 = {};
		MaxHeap.sort(it4);
		assertEquals(Utils.toString(it4), "[]");
		
		Double[] it5 = Utils.generateArray(2, 'r');
		MaxHeap.sort(it5);
		assertEquals(Utils.toString(it5), "[1.0, 2.0]");
		
		Double[] it6 = Utils.generateArray(2, 'i');
		MaxHeap.sort(it6);
		assertEquals(Utils.toString(it5), "[1.0, 2.0]");
	}
	
	@Test
	void test_MergeSort() {
		Double[] it1 = Utils.generateArray(10, 'r');
		Double [] it2 = {0.0, 42.0, 81.0, 2.0, 90.0, 30.0, 23.0};
		
		Double[] sorted = MergeSort.sort(it1);
		
		assertTrue(Utils.isSorted(sorted));
		assertEquals(Utils.toString(sorted), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		Double[] it3 = Utils.generateArray(10, 'i');
		sorted = MergeSort.sort(it3);
		assertTrue(Utils.isSorted(sorted));
		assertEquals(Utils.toString(sorted), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		it3 = Utils.generateArray(10,  'd');
		sorted = MergeSort.sort(it3);
		assertTrue(Utils.isSorted(sorted));
		assertEquals(Utils.toString(sorted), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		Double[] it4 = {};
		sorted = MergeSort.sort(it4);
		assertEquals(Utils.toString(sorted), "[]");
		
		Double[] it5 = Utils.generateArray(2, 'r');
		MergeSort.sort(it5);
		assertEquals(Utils.toString(it5), "[1.0, 2.0]");
		
		Double[] it6 = Utils.generateArray(2, 'i');
		MergeSort.sort(it6);
		assertEquals(Utils.toString(it5), "[1.0, 2.0]");
	}
	
	@Test
	void test_QuickSort() {
		Double[] it1 = Utils.generateArray(10, 'i');
		Double [] it2 = {0.0, 42.0, 81.0, 2.0, 90.0, 30.0, 23.0};
		Double[] two = Utils.generateArray(2, 'i');
		
		it1 = Utils.generateArray(10, 'i');
		QuickSort.sort(it1);
		assertTrue(Utils.isSorted(it1));
		assertEquals(Utils.toString(it1), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		Double[] it3 = Utils.generateArray(10, 'r');
		QuickSort.sort(it3);
		assertTrue(Utils.isSorted(it3));
		assertEquals(Utils.toString(it3), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		it3 = Utils.generateArray(10, 'd');
		QuickSort.sort(it3);
		assertTrue(Utils.isSorted(it3));
		assertEquals(Utils.toString(it3), "[1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0]");
		
		Integer[] it4 = {};
		QuickSort.sort(it4);
		assertEquals(Utils.toString(it4), "[]");
		
		Double[] it5 = Utils.generateArray(2, 'r');
		QuickSort.sort(it5);
		assertEquals(Utils.toString(it5), "[1.0, 2.0]");
		
		Double[] it6 = Utils.generateArray(2, 'i');
		QuickSort.sort(it6);
		assertEquals(Utils.toString(it5), "[1.0, 2.0]");
	}
}
