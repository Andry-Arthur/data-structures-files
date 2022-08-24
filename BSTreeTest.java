/**
 * Unit test cases for the implementation of a Binary Search Tree.
 *
 * @author  Andry
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.Test;

public class BSTreeTest
{
	/**
	 * the tree to use for testing
	 */
	private BSTree<Integer> tree;

	// returns a tree loaded with the given items
	static BSTree<Integer> load(Integer... items)
	{
		IntComparator compare = new IntComparator();
		BSTree<Integer> tree = new BSTree<Integer>(compare);
		for (Integer value : items) {
			tree.addLoop(value);		//changed to addLoop(E) to test add(E) 
		}
		return tree;
	}
	
	@Test
	public void test_isEmpty() 
	{
		tree = load();
		assertTrue(tree.isEmpty());
		assertEquals( tree.toString(), "[]" );

		tree = load(1);
		assertFalse(tree.isEmpty());
		assertEquals( tree.toString(), "[1]" );

		tree = load(1, 2, -21, 30, 0);
		assertFalse(tree.isEmpty());
		assertEquals( tree.toString(), "[1 -21 2 0 30]" );
	}

	@Test
	public void test_add()
	{
		// testing empty
		tree = load();
		tree.add(1);
		assertEquals( tree.toString(), "[1]" );

		// testing single
		tree = load(2);
		tree.add(3);
		assertEquals( tree.toString(), "[2 3]" );

		tree = load(2);
		tree.add(1);
		assertEquals( tree.toString(), "[2 1]" );

		tree = load(2);
		tree.add(2);
		assertEquals( tree.toString(), "[2 2]" );

		//*** one test case for single may not be enough ***

		// testing multi
		tree = load(10, 1, 2, 3, 4, 15, 6, 14, 26, 4, 11);
		tree.add(20);
		assertEquals( tree.toString(), "[10 1 15 2 14 26 3 11 20 4 6 4]" );

		tree = load(10, 1, 2, 3, 4, 15, 6, 14, 26, 4, 11);
		tree.add(0); 
		assertEquals( tree.toString(), "[10 1 15 0 2 14 26 3 11 4 6 4]" );

	}

	@Test
	public void test_addLoop()
	{
		// testing empty
		tree = load();
		tree.addLoop(1);
		assertEquals( tree.toString(), "[1]" );

		// testing single
		tree = load(2);
		tree.addLoop(3);
		assertEquals( tree.toString(), "[2 3]" );

		tree = load(2);
		tree.addLoop(1);
		assertEquals( tree.toString(), "[2 1]" );

		tree = load(2);
		tree.addLoop(2);
		assertEquals( tree.toString(), "[2 2]" );

		//*** one test case for single may not be enough ***

		// testing multi
		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		tree.addLoop(20);
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 20 4]" );

		tree = load(10, 1, 2, 3, 4, 15, 6, 14, 26, 4, 11);
		tree.addLoop(0); 
		assertEquals( tree.toString(), "[10 1 15 0 2 14 26 3 11 4 6 4]" );
	}

	@Test
	public void test_maxValueLoop()
	{
		// testing empty
		tree = load();
		try {tree.maxValueLoop(); fail(); } 
		catch (NoSuchElementException e) {/* test passed */ }; 
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.maxValueLoop() == 1 );
		assertEquals( tree.toString(), "[1]" );

		//*** one test case for single may not be enough ***

		// testing multi
		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.maxValueLoop() ==  26 );
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 100, 2, 3, 4, 15, 6, 14, 4, 11);
		assertFalse( tree.maxValueLoop() ==  26 );
		assertEquals( tree.toString(), "[10 2 100 3 15 4 14 6 11 4]" );
	}

	@Test
	public void test_maxValue()
	{
		// testing empty
		tree = load();
		try {tree.maxValue(); fail(); } 
		catch (NoSuchElementException e) {/* test passed */ }; 
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.maxValue() == 1 );
		assertEquals( tree.toString(), "[1]" );

		//*** one test case for single may not be enough ***

		// testing multi
		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.maxValue() ==  26 );
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 100, 2, 3, 4, 15, 6, 14, 4, 11);
		assertFalse( tree.maxValue() ==  26 );
		assertEquals( tree.toString(), "[10 2 100 3 15 4 14 6 11 4]" );
	}

	@Test
	public void test_minValueLoop() 
	{
		tree = load();
		try {tree.minValueLoop(); fail(); } 
		catch (NoSuchElementException e) {/* test passed */ }; 
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.minValueLoop() == 1 );
		assertEquals( tree.toString(), "[1]" );

		//*** one test case for single may not be enough ***

		// testing multi
		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.minValueLoop() ==  1 );
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 100, 2, 3, 4, 15, 6, 14, 4, 0, 11);
		assertFalse( tree.maxValueLoop() ==  1 );
		assertEquals( tree.toString(), "[10 2 100 0 3 15 4 14 6 11 4]" );
	}

	@Test
	public void test_minValue() 
	{
		tree = load();
		try {tree.minValue(); fail(); } 
		catch (NoSuchElementException e) {/* test passed */ }; 
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.minValue() == 1 );
		assertEquals( tree.toString(), "[1]" );

		//*** one test case for single may not be enough ***

		// testing multi
		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.minValue() ==  1 );
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 100, 2, 3, 4, 15, 6, 14, 4, 0, 11);
		assertFalse( tree.minValue() ==  1 );
		assertEquals( tree.toString(), "[10 2 100 0 3 15 4 14 6 11 4]" );
	}

	@Test
	public void test_contains() 
	{
		tree = load();
		assertFalse(tree.contains(0));
		assertEquals( tree.toString(), "[]" );

		tree = load(1);
		assertFalse(tree.contains(0));
		assertEquals( tree.toString(), "[1]" );

		tree = load(1);
		assertTrue(tree.contains(1));
		assertEquals( tree.toString(), "[1]" );

		tree = load(10, 12);
		assertTrue( tree.contains(12));
		assertEquals( tree.toString(), "[10 12]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.contains(10));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.contains(15));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertFalse( tree.contains(100));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );
	}

	@Test
	public void test_containsLoop() 
	{
		tree = load();
		assertFalse(tree.containsLoop(0));
		assertEquals( tree.toString(), "[]" );

		tree = load(1);
		assertFalse(tree.containsLoop(0));
		assertEquals( tree.toString(), "[1]" );

		tree = load(1);
		assertTrue(tree.containsLoop(1));
		assertEquals( tree.toString(), "[1]" );

		tree = load(10, 12);
		assertTrue( tree.containsLoop(12));
		assertEquals( tree.toString(), "[10 12]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.containsLoop(10));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.containsLoop(15));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertFalse( tree.containsLoop(100));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );
	}

	@Test
	public void test_remove() 
	{
		tree = load();
		assertFalse(tree.remove(0));
		assertEquals( tree.toString(), "[]" );

		tree = load(1);
		assertFalse(tree.remove(0));
		assertEquals( tree.toString(), "[1]" );

		tree = load(1);
		assertTrue(tree.remove(1));
		assertEquals( tree.toString(), "[]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.remove(11));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 4]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.remove(2));
		assertEquals( tree.toString(), "[10 3 15 1 4 14 26 6 11 4]" );

		tree = load(10, 4, 12, 15);
		assertTrue( tree.remove(12));
		assertEquals( tree.toString(), "[10 4 15]" );

		tree = load(10, 1, 2, 3, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.remove(10));
		assertEquals( tree.toString(), "[]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.remove(15));
		assertEquals( tree.toString(), "[10 3 26 2 4 14 1 6 11 4]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertTrue( tree.remove(1));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 6 11 4]" );

		tree = load(10, 3, 2, 1, 4, 15, 6, 14, 26, 4, 11);
		assertFalse( tree.remove(100));
		assertEquals( tree.toString(), "[10 3 15 2 4 14 26 1 6 11 4]" );
		
		tree = load(11, 9, 8, 10);
		assertTrue(tree.remove(11));
		assertEquals(tree.toString(), "[9 8 10]");
	}
	
	
	//Assignment #6
	@Test
	public void test_preorder() 
	{
		tree = load();
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		tree = load(50);
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[50]" );
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
		
		tree = load(50, 60, 30, 20);
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[50 30 20 60]" );
		assertEquals( tree.toString(), "[50 30 60 20]" );
		assertEquals( tree.toStringPre(), "[50 30 20 60]" );
		
		tree = load(50, 30, 70, 20, 35, 85, 65, 32, 82, 45);
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[50 30 20 35 32 45 70 65 85 82]" );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]" );
	}
	
	@Test
	public void test_inorder() 
	{
		tree = load();
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		tree = load(50);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[50]" );
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
		
		tree = load(50, 60, 30, 20);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder(strVisitor);
		assertEquals( strVisitor.getValue(), "[20 30 50 60]" );
		assertEquals( tree.toString(), "[50 30 60 20]" );
		assertEquals( tree.toStringPre(), "[50 30 20 60]" );
		
		tree = load(50, 30, 70, 20, 35, 85, 65, 32, 82, 45);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder(strVisitor);
		assertEquals( strVisitor.getValue(), "[20 30 32 35 45 50 65 70 82 85]" );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]" );
	}
	
	@Test
	public void test_postorder() 
	{
		tree = load();
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		tree = load(50);
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[50]" );
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
		
		tree = load(50, 60, 30, 20);
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[20 30 60 50]" );
		assertEquals( tree.toString(), "[50 30 60 20]" );
		assertEquals( tree.toStringPre(), "[50 30 20 60]" );
		
		tree = load(50, 30, 70, 20, 35, 85, 65, 32, 82, 45);
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[20 32 45 35 30 65 82 85 70 50]" );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]" );
	}
	
	@Test
	public void test_equals() 
	{
		tree = load();
		assertTrue( tree.equals(tree) );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		tree = load(50);
		assertTrue( tree.equals(tree) );
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
		
		tree = load(50, 60, 30, 20);
		assertTrue( tree.equals(tree) );
		assertEquals( tree.toString(), "[50 30 60 20]" );
		assertEquals( tree.toStringPre(), "[50 30 20 60]" );
		
		tree = load(50, 30, 70, 20, 35, 85, 65, 32, 82, 45);
		assertTrue( tree.equals(tree) );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]");
		
		tree = load(50, 30, 70, 20, 35, 85, 65, 32, 82, 45);
		assertFalse(tree.equals(new ArrayList<Integer>()));
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]");
		
		BSTree<Integer> other = load(50, 30, 70, 20, 35, 85, 65, 32, 82, 45);
		assertTrue( tree.equals(other) );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]");
		
		other = load(50, 60, 30, 20);
		assertFalse( tree.equals(other) );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]");
		
		tree = load();
		assertFalse(tree.equals(other));
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		tree = load(50);
		other = load(80);
		assertFalse(tree.equals(other));
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
	}
	
	@Test
	public void test_clone() 
	{
		tree = load();
		BSTree<Integer> copy = (BSTree<Integer>) tree.clone();
		assertTrue( copy.equals(tree) );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]");
		
		tree = load(50);
		copy = (BSTree<Integer>) tree.clone();
		assertTrue( copy.equals(tree) );
		assertEquals( copy.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]");
		
		tree = load(50, 20);
		copy = (BSTree<Integer>) tree.clone();
		assertTrue( copy.equals(tree) );
		assertEquals( copy.toString(), "[50 20]" );
		assertEquals( tree.toStringPre(), "[50 20]");
		
		
	}
	
	
	@Test
	public void test_BSTreeFromPreorder() 
	{
		IntComparator comp = new IntComparator();
		
		Integer[] numbers0 = {};
		BSTree<Integer> tree = new BSTree(numbers0, comp);
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		Integer[] numbers1 = {50};
		tree = new BSTree(numbers1, comp);
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
		
		Integer[] numbers2 = {50, 30, 20, 35, 60, 55, 70};
		tree = new BSTree(numbers2, comp);
		assertEquals( tree.toString(), "[50 30 60 20 35 55 70]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 60 55 70]" );
	}
	
	@Test
	public void test_CountRangeVisitor() 
	{
		//Integer Tree test
		tree = load();
		IntComparator intComp = new IntComparator();
		CountRangeVisitor<Integer> countVisitor = new CountRangeVisitor<Integer>(0, 5, intComp);
		tree.inorder( countVisitor );
		assertEquals( countVisitor.getCount(), 0 );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		tree = load(50);
		countVisitor = new CountRangeVisitor<Integer>(0, 5, intComp);
		tree.inorder( countVisitor );
		assertEquals( countVisitor.getCount(), 0 );
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
		
		tree = load(50);
		countVisitor = new CountRangeVisitor<Integer>(10, 60, intComp);
		tree.inorder( countVisitor );
		assertEquals( countVisitor.getCount(), 1 );
		assertEquals( tree.toString(), "[50]" );
		assertEquals( tree.toStringPre(), "[50]" );
		
		tree = load(50, 30, 70, 20, 35, 85, 65, 32, 82, 45);
		countVisitor = new CountRangeVisitor<Integer>(100, 6000, intComp);
		tree.inorder(countVisitor);
		assertEquals( countVisitor.getCount(), 0 );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]" );
		
		countVisitor = new CountRangeVisitor<Integer>(0, 100, intComp);
		tree.inorder(countVisitor);
		assertEquals( countVisitor.getCount(), 10 );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]" );
		
		countVisitor = new CountRangeVisitor<Integer>(0, 50, intComp);
		tree.inorder(countVisitor);
		assertEquals( countVisitor.getCount(), 6 );
		assertEquals( tree.toString(), "[50 30 70 20 35 65 85 32 45 82]" );
		assertEquals( tree.toStringPre(), "[50 30 20 35 32 45 70 65 85 82]" );
		
		//String Tree test
		StringComparator strComp = new StringComparator();
		BSTree<String> strTree = new BSTree<String>(strComp);
		CountRangeVisitor<String> countVisitor2 = new CountRangeVisitor<String>("A" , "Z", strComp);
		strTree.inorder( countVisitor2 );
		assertEquals( countVisitor2.getCount(), 0 );
		assertEquals( strTree.toString(), "[]" );
		assertEquals( strTree.toStringPre(), "[]" );
		
		strTree.add("P");
		countVisitor2 = new CountRangeVisitor<String>("A" , "Z", strComp);
		strTree.inorder( countVisitor2 );
		assertEquals( countVisitor2.getCount(), 1 );
		assertEquals( strTree.toString(), "[P]" );
		assertEquals( strTree.toStringPre(), "[P]" );
		
		countVisitor2 = new CountRangeVisitor<String>("A" , "B", strComp);
		strTree.inorder( countVisitor2 );
		assertEquals( countVisitor2.getCount(), 0 );
		assertEquals( strTree.toString(), "[P]" );
		assertEquals( strTree.toStringPre(), "[P]" );
		
		strTree.add("Bee");
		strTree.add("Car");
		strTree.add("Zebra");
		strTree.add("Queue");
		strTree.add("Zoom");
		countVisitor2 = new CountRangeVisitor<String>("A" , "Zz", strComp);
		strTree.inorder( countVisitor2 );
		assertEquals( countVisitor2.getCount(), 6 );
		assertEquals( strTree.toString(), "[P Bee Zebra Car Queue Zoom]" );
		assertEquals( strTree.toStringPre(), "[P Bee Car Zebra Queue Zoom]" );
		
		countVisitor2 = new CountRangeVisitor<String>("A" , "G", strComp);
		strTree.inorder( countVisitor2 );
		assertEquals( countVisitor2.getCount(), 2 );
		assertEquals( strTree.toString(), "[P Bee Zebra Car Queue Zoom]" );
		assertEquals( strTree.toStringPre(), "[P Bee Car Zebra Queue Zoom]" );
		
		countVisitor2 = new CountRangeVisitor<String>("Zzz" , "Zzzzz", strComp);
		strTree.inorder( countVisitor2 );
		assertEquals( countVisitor2.getCount(), 0 );
		assertEquals( strTree.toString(), "[P Bee Zebra Car Queue Zoom]" );
		assertEquals( strTree.toStringPre(), "[P Bee Car Zebra Queue Zoom]" );
		
		strTree.add("A");
		countVisitor2 = new CountRangeVisitor<String>("A" , "Zz", strComp);
		strTree.inorder( countVisitor2 );
		assertEquals( countVisitor2.getCount(), 7 );
		assertEquals( strTree.toString(), "[P Bee Zebra A Car Queue Zoom]" );
		assertEquals( strTree.toStringPre(), "[P Bee A Car Zebra Queue Zoom]" );
	}
}