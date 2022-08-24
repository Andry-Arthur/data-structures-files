import static org.junit.Assert.*;

import org.junit.Test;

public class LHashMapTest 
{

	// shortcut for making MyString objects
	// * instead of:   myMap.put( new MyString("cat"), 4 );
	// * can  write:   myMap.put( ms("cat"), 4 );
	private static MyString ms(String str)
	{
		return new MyString(str);
	}

	@Test
	public void testAllMethods() 
	{
		LHashMap<MyString, Integer> map = new LHashMap<MyString, Integer>(10, 0.5);
		assertEquals(map.toString(), "0:[E E E E E E E E E E]");
		
		map.put(ms("Cat"), 80);
		assertEquals(map.toString(), "1:[E E E Cat:80 E E E E E E]");
		
		map.put(ms("Cat"), 31);
		assertFalse(map.isEmpty());
		
		map.put(ms("Pig"), 81);
		assertEquals(map.toString(), "2:[E E E Cat:31 Pig:81 E E E E E]");

		map.put(ms("A"), 91);
		assertTrue(map.get(ms("Pig")) == 81);
		
		map.remove(ms("Pig"));
		assertEquals(map.toString(), "2:[E A:91 E Cat:31 D E E E E E]");
		
		map.put(ms("Bunny"), 54);
		assertTrue(map.get(ms("Bunny")) == 54);
		
		map.clear();
		assertEquals(map.toString(), "0:[E E E E E E E E E E]");
		
		//testing for rehash
		map.put(ms("koala"), 65);
		assertTrue(map.containsKey(ms("koala")));
		
		map.put(ms("Lion"), 97);
		assertFalse(map.containsValue(0));
		
		map.put(ms("koala"), 23);
		assertTrue(map.containsValue(23));
		
		map.put(ms("Whale"), 23);
		assertTrue(map.containsKey(ms("Lion")));
		
		map.put(ms("A"), 0);
		map.remove(ms("A"));
		
		map.put(ms("Whale"), 43);
		
		map.put(ms("Penguin"), 90);
		assertFalse(map.containsKey(ms("Yellow")));
		map.put(ms("Snake"), 70);
		
		map.put(ms("Shark"), 300);
		assertEquals(map.toString(), "6:[E D E E Lion:97 koala:23 Whale:43 Penguin:90 Snake:70 Shark:300 E E E E E E E E E E]");
		
		map.clear();
		assertTrue(map.isEmpty());
	}
}
