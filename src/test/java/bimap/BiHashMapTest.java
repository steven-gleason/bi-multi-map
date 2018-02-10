package bimap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class BiHashMapTest
{

	@Test
	public void emptyBiMap()
	{
		BiMap<String, Integer> testSubject = new BiHashMap<>();
		List<String> keys = testSubject.getKeys(5);
		List<Integer> vals = testSubject.getVals("word");

		assertNull(keys);
		assertNull(vals);
	}

	@Test
	public void fullBiMap()
	{
		BiMap<String, String> testSubject = new BiHashMap<>();
		testSubject.add("One", "1");
		testSubject.add("Uno", "1");
		testSubject.add("Two", "2");
		testSubject.add("Two", "II");

		List<String> oneVals = testSubject.getVals("One");
		List<String> unoVals = testSubject.getVals("Uno");
		List<String> twoVals = testSubject.getVals("Two");

		List<String> keys1 = testSubject.getKeys("1");
		List<String> keys2 = testSubject.getKeys("2");
		List<String> keysII = testSubject.getKeys("II");

		assertEquals(1, oneVals.size());
		assertTrue(oneVals.contains("1"));
		assertEquals(1, unoVals.size());
		assertTrue(unoVals.contains("1"));
		assertEquals(2, twoVals.size());
		assertTrue(twoVals.contains("2"));
		assertTrue(twoVals.contains("II"));

		assertEquals(2, keys1.size());
		assertTrue(keys1.contains("One"));
		assertTrue(keys1.contains("Uno"));
		assertEquals(1, keys2.size());
		assertTrue(keys2.contains("Two"));
		assertEquals(1, keysII.size());
		assertTrue(keysII.contains("Two"));
	}
}
