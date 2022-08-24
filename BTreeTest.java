import static org.junit.Assert.*;

import org.junit.Test;

public class BTreeTest {

	/**
	 * the tree to use for testing
	 */
	private BTree<Integer> tree;

	// returns a tree loaded with the given items
	static BTree<Integer> load(int order, Integer... items)
	{
		IntComparator compare = new IntComparator();
		BTree<Integer> tree = new BTree<Integer>(order, compare);
		for (Integer value : items) {
			tree.add(value);
		}
		return tree;
	}
	
	@Test
	public void test_add_2() {
		tree = load(2);
		tree.add(1);
		assertEquals(tree.toString(), "[[1]]");
		assertEquals(tree.toStringSorted(), "[1]");
		
		tree = load(2, 1, 2);
		tree.add(2);
		assertEquals(tree.toString(), "[[2] [1] [2]]");
		assertEquals(tree.toStringSorted(), "[1 2 2]");
		
		tree = load(2, 1, 2);
		tree.add(0);
		assertEquals(tree.toString(), "[[1] [0] [2]]");
		assertEquals(tree.toStringSorted(), "[0 1 2]");
		
		tree = load(2, 1, 2);
		tree.add(10);
		assertEquals(tree.toString(), "[[2] [1] [10]]");
		assertEquals(tree.toStringSorted(), "[1 2 10]");
		
		tree = load(2, 1, 3, 5);
		tree.add(2);
		assertEquals(tree.toString(), "[[3] [1 2] [5]]");
		assertEquals(tree.toStringSorted(), "[1 2 3 5]");
		
		tree = load(2, 1, 3, 5);
		tree.add(10);
		assertEquals(tree.toString(), "[[3] [1] [5 10]]");
		assertEquals(tree.toStringSorted(), "[1 3 5 10]");
		
		tree = load(2, 1, 3, 5);
		tree.add(1);
		assertEquals(tree.toString(), "[[3] [1 1] [5]]");
		assertEquals(tree.toStringSorted(), "[1 1 3 5]");
	}

	@Test
	public void test_add_4() {
		tree = load(4);
		assertEquals(tree.toString(), "[[]]");
		
		tree = load(4);
		tree.add(1);
		assertEquals(tree.toString(), "[[1]]");
		assertEquals(tree.toStringSorted(), "[1]");
		
		tree = load(4, 1, 2);
		tree.add(2);
		assertEquals(tree.toString(), "[[1 2 2]]");
		assertEquals(tree.toStringSorted(), "[1 2 2]");
		
		tree = load(4, 1, 2);
		tree.add(0);
		assertEquals(tree.toString(), "[[0 1 2]]");
		assertEquals(tree.toStringSorted(), "[0 1 2]");
		
		tree = load(4, 1, 2);
		tree.add(10);
		assertEquals(tree.toString(), "[[1 2 10]]");
		assertEquals(tree.toStringSorted(), "[1 2 10]");
		
		tree = load(4, 1, 3, 5);
		tree.add(2);
		assertEquals(tree.toString(), "[[1 2 3 5]]");
		assertEquals(tree.toStringSorted(), "[1 2 3 5]");
		
		tree = load(4, 1, 3, 5);
		tree.add(10);
		assertEquals(tree.toString(), "[[1 3 5 10]]");
		assertEquals(tree.toStringSorted(), "[1 3 5 10]");
		
		tree = load(4, 1, 3, 5);
		tree.add(1);
		assertEquals(tree.toString(), "[[1 1 3 5]]");
		assertEquals(tree.toStringSorted(), "[1 1 3 5]");
		
		tree = load(4, 1, 3, 5, 7);
		tree.add(0);
		assertEquals(tree.toString(), "[[3] [0 1] [5 7]]");
		assertEquals(tree.toStringSorted(), "[0 1 3 5 7]");
		
		tree = load(4, 1, 3, 5, 7);
		tree.add(10);
		assertEquals(tree.toString(), "[[5] [1 3] [7 10]]");
		assertEquals(tree.toStringSorted(), "[1 3 5 7 10]");

		tree = load(4, 1, 3, 5, 7);
		tree.add(2);
		assertEquals(tree.toString(), "[[3] [1 2] [5 7]]");
		assertEquals(tree.toStringSorted(), "[1 2 3 5 7]");
	}

	@Test 
	public void test_contains_2() 
	{
		tree = load(2);
		assertFalse(tree.contains(4));
		assertEquals(tree.toString(), "[[]]");
		assertEquals(tree.toStringSorted(), "[]");
		
		tree = load(2, 1);
		assertTrue(tree.contains(1));
		assertEquals(tree.toString(), "[[1]]");
		assertEquals(tree.toStringSorted(), "[1]");
		
		tree = load(2, 1);
		assertFalse(tree.contains(2));
		assertEquals(tree.toString(), "[[1]]");
		assertEquals(tree.toStringSorted(), "[1]");
		
		tree = load(2, 2, 4, 6, 8, 10);
		assertFalse(tree.contains(100));
		assertEquals(tree.toString(), "[[4 8] [2] [6] [10]]");
		assertEquals(tree.toStringSorted(), "[2 4 6 8 10]");
		
		assertFalse(tree.contains(0));
		assertEquals(tree.toString(), "[[4 8] [2] [6] [10]]");
		assertEquals(tree.toStringSorted(), "[2 4 6 8 10]");
		
		assertTrue(tree.contains(6));
		assertEquals(tree.toString(), "[[4 8] [2] [6] [10]]");
		assertEquals(tree.toStringSorted(), "[2 4 6 8 10]");
	}
	
	@Test 
	public void test_contains_4() 
	{
		tree = load(4);
		assertFalse(tree.contains(4));
		assertEquals(tree.toString(), "[[]]");
		assertEquals(tree.toStringSorted(), "[]");
		
		tree = load(4, 1);
		assertFalse(tree.contains(4));
		assertEquals(tree.toString(), "[[1]]");
		assertEquals(tree.toStringSorted(), "[1]");
		
		tree = load(4, 1);
		assertFalse(tree.contains(2));
		assertEquals(tree.toString(), "[[1]]");
		assertEquals(tree.toStringSorted(), "[1]");
		
		tree = load(4, 2, 4, 6, 8, 10);
		assertFalse(tree.contains(100));
		assertEquals(tree.toString(), "[[6] [2 4] [8 10]]");
		assertEquals(tree.toStringSorted(), "[2 4 6 8 10]");
		
		assertFalse(tree.contains(0));
		assertEquals(tree.toString(), "[[6] [2 4] [8 10]]");
		assertEquals(tree.toStringSorted(), "[2 4 6 8 10]");
		
		assertTrue(tree.contains(6));
		assertEquals(tree.toString(), "[[6] [2 4] [8 10]]");
		assertEquals(tree.toStringSorted(), "[2 4 6 8 10]");
	}
	
	@Test
	public void test_inorder() 
	{
		tree = load(2);
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[[]]" );
		assertEquals( tree.toStringSorted(), "[]" );
		
		tree = load(2);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[[]]" );
		assertEquals( tree.toStringSorted(), "[]" );
		
		tree = load(2, 2);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[2]" );
		assertEquals( tree.toString(), "[[2]]" );
		assertEquals( tree.toStringSorted(), "[2]" );
		
		tree = load(2, 2, 4, 6, 8);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[2 4 6 8]" );
		assertEquals( tree.toString(), "[[4] [2] [6 8]]" );
		assertEquals( tree.toStringSorted(), "[2 4 6 8]" );
		
		tree = load(2, 10, 20, 40, 30, 80, 60, 50, 70);
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[10 20 30 40 50 60 70 80]" );
		assertEquals( tree.toString(), "[[40] [20] [60 70] [10] [30] [50] [80]]" );
		//assertEquals( tree.toStringSorted(), "[10 20 30 40 50 60 70 80]" );
	}
}
