import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DLinkedListTest 
{
	private DLinkedList<Integer> list;
	
	private static <E> DLinkedList<E> load(E... items)
	{
		DLinkedList<E> list = new DLinkedList<E>();
		
		for(E value: items) {
			list.addLast(value); 
		}
		return list;
	}
	
	@Test
	public void test_isEmpty() 
	{
		list = load();
		assertTrue(list.isEmpty());
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load( 10 );
		assertFalse(list.isEmpty());
		assertEquals(list.toStringNext(), "[10]");
		assertEquals(list.toStringPrev(), "[10]");
	
		list = load( 10, 8, 0, 2, 70, 4 );
		assertFalse(list.isEmpty());
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_addFirst() 
	{
		list = load();
		list.addFirst(2);
		assertEquals(list.toStringNext(), "[2]");
		assertEquals(list.toStringPrev(), "[2]");
		
		list = load (10); 
		list.addFirst(4);
		assertEquals(list.toStringNext(), "[4 10]");
		assertEquals(list.toStringPrev(), "[4 10]");
		
		list = load(10, 8, 0, 2, 70, 4);
		list.addFirst(5);
		assertEquals(list.toStringNext(), "[5 10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[5 10 8 0 2 70 4]");
	}
	
	@Test
	public void test_addLast() 
	{
		list = load();
		list.addLast(3);
		assertEquals(list.toStringNext(), "[3]");
		assertEquals(list.toStringPrev(), "[3]");
		
		list = load (10); 
		list.addLast(4);
		assertEquals(list.toStringNext(), "[10 4]");
		assertEquals(list.toStringPrev(), "[10 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		list.addLast(5);
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4 5]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4 5]");
	}
	
	@Test
	public void test_add() 
	{
		list = load();
		try { list.add(1, 10); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load();
		try { list.add(-1, 10); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load();
		list.add(0, 10);
		assertEquals(list.toStringNext(), "[10]");
		assertEquals(list.toStringPrev(), "[10]");
		
		list = load();
		list.add(0, 3);
		assertEquals(list.toStringNext(), "[3]");
		assertEquals(list.toStringPrev(), "[3]");
		
		list = load(30);
		list.add(0, 2);
		assertEquals(list.toStringNext(), "[2 30]");
		assertEquals(list.toStringPrev(), "[2 30]");
		
		list = load(10, 8, 0, 2, 70, 4);
		list.add(0, 0);
		assertEquals(list.toStringNext(), "[0 10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[0 10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		list.add(6, 3);
//		assertEquals(list.toStringNext(), "[10 8 0 2 70 4 3]");
//		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4 3]");
//		
//		list = load(10, 8, 0, 2, 70, 4);
//		list.add(3, 3);
//		assertEquals(list.toStringNext(), "[10 8 0 3 2 70 4]");
//		assertEquals(list.toStringPrev(), "[10 8 0 3 2 70 4]");
	}
	
	@Test
	public void test_getFirst()
	{
		list = load();
		try { list.getFirst(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(5);
		assertTrue(list.getFirst() == 5);
		assertEquals(list.toStringNext(), "[5]");
		assertEquals(list.toStringPrev(), "[5]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.getFirst() == 10);
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_getLast() 
	{
		list = load();
		try { list.getLast(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(5);
		assertTrue(list.getLast() == 5);
		assertEquals(list.toStringNext(), "[5]");
		assertEquals(list.toStringPrev(), "[5]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.getLast() == 4);
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_get() 
	{	
		list = load();
		try { list.get(-1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load();
		try { list.get(0); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(5);
		assertTrue(list.get(0) == 5);
		assertEquals(list.toStringNext(), "[5]");
		assertEquals(list.toStringPrev(), "[5]");
		
		list = load(5);
		try{ list.get(1); fail(); }
		catch(IndexOutOfBoundsException e) { /* test passed */} 
		assertEquals(list.toStringNext(), "[5]");
		assertEquals(list.toStringPrev(), "[5]");
		
		list = load(5);
		try{ list.get(-1); fail(); }
		catch(IndexOutOfBoundsException e) { /* test passed */} 
		assertEquals(list.toStringNext(), "[5]");
		assertEquals(list.toStringPrev(), "[5]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.get(0) == 10);
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.get(3) == 2);
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.get(5) == 4);
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		try { list.get(6); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		try { list.get(-1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_set() 
	{	
		list = load();
		try { list.set(-1, 20); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load();
		try { list.set(0, 20); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(9);
		assertTrue(list.set(0, 10) == 9);
		assertEquals(list.toStringNext(), "[10]");
		assertEquals(list.toStringPrev(), "[10]");
		
		list = load(9);
		try { list.set(-1, 10); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[9]");
		assertEquals(list.toStringPrev(), "[9]");
		
		list = load(9);
		try { list.set(1, 10); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[9]");
		assertEquals(list.toStringPrev(), "[9]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.set(0, 1) == 10);
		assertEquals(list.toStringNext(), "[1 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[1 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.set(2, 15) == 0);
		assertEquals(list.toStringNext(), "[10 8 15 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 15 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.set(5, 15) == 4);
		assertEquals(list.toStringNext(), "[10 8 0 2 70 15]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 15]");
		
		list = load(10, 8, 0, 2, 70, 4);
		try { list.set(6, 32); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		try { list.set(-1, 32); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_contains() 
	{
		list = load(10);
		assertFalse(list.contains(20));
		assertEquals(list.toStringNext(), "[10]");
		assertEquals(list.toStringPrev(), "[10]");
		
		list = load(10);
		assertTrue(list.contains(10));
		assertEquals(list.toStringNext(), "[10]");
		assertEquals(list.toStringPrev(), "[10]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.contains(10));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.contains(2));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.contains(4));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertFalse(list.contains(5));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_containsIter() 
	{	
		list = load(10);
		assertFalse(list.containsIter(20));
		assertEquals(list.toStringNext(), "[10]");
		assertEquals(list.toStringPrev(), "[10]");
		
		list = load(10);
		assertTrue(list.containsIter(10));
		assertEquals(list.toStringNext(), "[10]");
		assertEquals(list.toStringPrev(), "[10]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.containsIter(10));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.containsIter(2));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.containsIter(4));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertFalse(list.containsIter(5));
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_clear() 
	{
		list = load();
		list.clear();
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(10);
		list.clear();
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(10, 8, 0, 2, 70, 15);
		list.clear();
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
	}
	
	
	@Test
	public void test_removeFirst() 
	{
		list = load();
		try { list.removeFirst(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(10);
		list.removeFirst();
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(10, 8, 0, 2);
		list.removeFirst();
		assertEquals(list.toStringNext(), "[8 0 2]");
		assertEquals(list.toStringPrev(), "[8 0 2]");
	}
	
	@Test
	public void test_removeLast() 
	{
		list = load();
		try { list.removeLast(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(10);
		list.removeLast();
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(10, 8, 0, 2);
		list.removeLast();
		assertEquals(list.toStringNext(), "[10 8 0]");
		assertEquals(list.toStringPrev(), "[10 8 0]");
	}
	
	@Test
	public void test_remove() 
	{
		list = load();
		try { list.remove(-1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load();
		try { list.remove(0); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
		
		list = load(5);
		try{ list.remove(1); fail(); }
		catch(IndexOutOfBoundsException e) { /* test passed */} 
		assertEquals(list.toStringNext(), "[5]");
		assertEquals(list.toStringPrev(), "[5]");
		
		list = load(5);
		try{ list.remove(-1); fail(); }
		catch(IndexOutOfBoundsException e) { /* test passed */} 
		assertEquals(list.toStringNext(), "[5]");
		assertEquals(list.toStringPrev(), "[5]");
		
		list = load(5);
		assertTrue(list.remove(0) == 5);
		assertEquals(list.toStringNext(), "[]");
		assertEquals(list.toStringPrev(), "[]");
	
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.remove(0) == 10);
		assertEquals(list.toStringNext(), "[8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.remove(3) == 2);
		assertEquals(list.toStringNext(), "[10 8 0 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		assertTrue(list.remove(5) == 4);
		assertEquals(list.toStringNext(), "[10 8 0 2 70]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70]");
		
		list = load(10, 8, 0, 2, 70, 4);
		try { list.remove(6); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
		
		list = load(10, 8, 0, 2, 70, 4);
		try { list.remove(-1); fail(); }
		catch (IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals(list.toStringNext(), "[10 8 0 2 70 4]");
		assertEquals(list.toStringPrev(), "[10 8 0 2 70 4]");
	}
	
	@Test
	public void test_removeItem() 
	{
		list = load();
		assertFalse( list.removeItem(4) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(5);
		assertFalse( list.removeItem(4) );
		assertEquals( list.toStringNext(), "[5]" );
		assertEquals( list.toStringPrev(), "[5]" );
		
		list = load(4);
		assertTrue( list.removeItem(4) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(10, 8, 0, 2, 70);
		assertFalse( list.removeItem(4) );
		assertEquals( list.toStringNext(), "[10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2 70]" );
		
		list = load(10, 8, 0, 2, 70);
		assertTrue( list.removeItem(10) );
		assertEquals( list.toStringNext(), "[8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[8 0 2 70]" );
		
		list = load(0, 8, 10, 10, 70);
		assertTrue( list.removeItem(10) );
		assertEquals( list.toStringNext(), "[0 8 10 70]" );
		assertEquals( list.toStringPrev(), "[0 8 10 70]" );
		
		list = load(10, 8, 0, 2, 70);
		assertTrue( list.removeItem(70) );
		assertEquals( list.toStringNext(), "[10 8 0 2]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2]" );
	}
	
	@Test
	public void test_removeAll() 
	{
		list = load();
		assertFalse( list.removeAll(4) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(5);
		assertFalse( list.removeAll(4) );
		assertEquals( list.toStringNext(), "[5]" );
		assertEquals( list.toStringPrev(), "[5]" );
		
		list = load(4);
		assertTrue( list.removeAll(4) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(10, 10, 10, 70);
		assertFalse( list.removeAll(4) );
		assertEquals( list.toStringNext(), "[10 10 10 70]" );
		assertEquals( list.toStringPrev(), "[10 10 10 70]" );
		
		list = load(10, 10, 10, 70);
		assertTrue( list.removeAll(10) );
		assertEquals( list.toStringNext(), "[70]" );
		assertEquals( list.toStringPrev(), "[70]" );
		
		list = load(0, 8, 10, 10, 70);
		assertTrue( list.removeAll(10) );
		assertEquals( list.toStringNext(), "[0 8 70]" );
		assertEquals( list.toStringPrev(), "[0 8 70]" );
		
		list = load(10, 8, 0, 2, 70, 70);
		assertTrue( list.removeAll(70) );
		assertEquals( list.toStringNext(), "[10 8 0 2]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2]" );
	}
	
	@Test
	public void test_equals() 
	{
		list = load();
		assertTrue( list.equals(load()) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load();
		assertTrue( list.equals(list));
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load();
		assertFalse( list.equals("List"));
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load();
		assertFalse( list.equals(load(4)) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(5);
		assertTrue(list.equals(load(5)));
		assertEquals( list.toStringNext(), "[5]" );
		assertEquals( list.toStringPrev(), "[5]" );
		
		list = load(5);
		assertFalse(list.equals(load(4)));
		assertEquals( list.toStringNext(), "[5]" );
		assertEquals( list.toStringPrev(), "[5]" );
		
		list = load(5);
		assertFalse(list.equals(load("List")));
		assertEquals( list.toStringNext(), "[5]" );
		assertEquals( list.toStringPrev(), "[5]" );
		
		list = load(10, 8, 0, 2, 70);
		assertFalse(list.equals(load(10, 8, 0, 2)));
		assertEquals( list.toStringNext(), "[10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2 70]" );
		
		list = load(10, 8, 0, 2, 70);
		assertFalse(list.equals(load(8, 0, 2, 70)));
		assertEquals( list.toStringNext(), "[10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2 70]" );
		
		list = load(10, 8, 0, 2, 70);
		assertFalse(list.equals(load(10, 8, 3, 0, 2, 70)));
		assertEquals( list.toStringNext(), "[10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2 70]" );
		
		list = load(10, 8, 0, 2, 70);
		assertTrue(list.equals(load(10, 8, 0, 2, 70)));
		assertEquals( list.toStringNext(), "[10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2 70]" );
		
		list = load(10, 8, 0, 2, 70);
		assertTrue(list.equals(list));
		assertEquals( list.toStringNext(), "[10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2 70]" );
		
		list = load(10, 8, 0, 2, 70);
		assertFalse(list.equals("List"));
		assertEquals( list.toStringNext(), "[10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2 70]" );
	}
	
	@Test
	public void test_addAll() 
	{
		list = load();
		try {list.addAll(-1, load(1, 2, 3, 4)); fail();}
		catch(IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load();
		try {list.addAll(1, load(1, 2, 3, 4)); fail();}
		catch(IndexOutOfBoundsException e) { /* test passed */ }
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load();
		list.addAll(0, load(1, 2, 3, 4));
		assertEquals( list.toStringNext(), "[1 2 3 4]" );
		assertEquals( list.toStringPrev(), "[1 2 3 4]" );
		
		
		list = load(10);
		list.addAll(0, load(1, 2, 3, 4));
		assertEquals( list.toStringNext(), "[1 2 3 4 10]" );
		assertEquals( list.toStringPrev(), "[1 2 3 4 10]" );
		
		list = load(10);
		list.addAll(1, load(1, 2, 3, 4));
		assertEquals( list.toStringNext(), "[10 1 2 3 4]" );
		assertEquals( list.toStringPrev(), "[10 1 2 3 4]" );
		
		list = load(10, 8, 0, 2, 70);
		list.addAll(0, load(1, 2, 3, 4));
		assertEquals( list.toStringNext(), "[1 2 3 4 10 8 0 2 70]" );
		assertEquals( list.toStringPrev(), "[1 2 3 4 10 8 0 2 70]" );
		
		list = load(10, 8, 0, 2, 70);
		list.addAll(2, load(1, 2, 3, 4));
		assertEquals( list.toStringNext(), "[10 8 1 2 3 4 0 2 70]" );
		assertEquals( list.toStringPrev(), "[10 8 1 2 3 4 0 2 70]" );
	}
	
	@Test
	public void test_removeAllIter() 
	{
		list = load();
		assertFalse( list.removeAllIter(4) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(5);
		assertFalse( list.removeAllIter(4) );
		assertEquals( list.toStringNext(), "[5]" );
		assertEquals( list.toStringPrev(), "[5]" );
		
		list = load(4);
		assertTrue( list.removeAllIter(4) );
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(10, 10, 10, 70);
		assertFalse( list.removeAllIter(4) );
		assertEquals( list.toStringNext(), "[10 10 10 70]" );
		assertEquals( list.toStringPrev(), "[10 10 10 70]" );
		
		list = load(10, 10, 10, 70);
		assertTrue( list.removeAllIter(10) );
		assertEquals( list.toStringNext(), "[70]" );
		assertEquals( list.toStringPrev(), "[70]" );
		
		list = load(0, 8, 10, 10, 70);
		assertTrue( list.removeAllIter(10) );
		assertEquals( list.toStringNext(), "[0 8 70]" );
		assertEquals( list.toStringPrev(), "[0 8 70]" );
		
		list = load(10, 8, 0, 2, 70, 70);
		assertTrue( list.removeAllIter(70) );
		assertEquals( list.toStringNext(), "[10 8 0 2]" );
		assertEquals( list.toStringPrev(), "[10 8 0 2]" );
	}
	
	@Test
	public void test_iter_fails() 
	{
		list = load();
		Iterator<Integer> iter = list.iterator();
		try {iter.next(); fail();}
		catch(NoSuchElementException e) { /* Test Passed*/ }
		assertEquals( list.toStringNext(), "[]" );
		assertEquals( list.toStringPrev(), "[]" );
		
		list = load(10);
		Iterator<Integer> iter0 = list.iterator();
		assertTrue(iter0.next() == 10);
		assertEquals( list.toStringNext(), "[10]" );
		assertEquals( list.toStringPrev(), "[10]" );
		
		list = load(10);
		Iterator<Integer> iter1 = list.iterator();
		try {iter1.remove(); fail();}
		catch(IllegalStateException e) { /* Test Passed*/ }
		assertEquals( list.toStringNext(), "[10]" );
		assertEquals( list.toStringPrev(), "[10]" );
		
		list = load(10, 20, 30);
		Iterator<Integer> iter2 = list.iterator();
		try {iter2.remove(); iter2.remove();}
		catch(IllegalStateException e) { /* Test Passed*/ }
		assertEquals( list.toStringNext(), "[10 20 30]" );
		assertEquals( list.toStringPrev(), "[10 20 30]" );
	}
}
