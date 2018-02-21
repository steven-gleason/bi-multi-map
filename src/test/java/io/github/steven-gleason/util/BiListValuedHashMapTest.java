package io.github.steven_gleason.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

public class BiListValuedHashMapTest
{

	@Test
	public void emptyBiMultiMap()
	{
		BiListValuedMap<String, Integer> testSubject = new BiListValuedHashMap<>();
		Collection<String> keys = testSubject.getKeys(5);
		Collection<Integer> vals = testSubject.getVals("word");

		assertNotNull(keys);
		assertNotNull(vals);
		assertTrue(keys.isEmpty());
		assertTrue(vals.isEmpty());
	}

	@Test
	public void fullBiMultiMap()
	{
		BiListValuedMap<String, String> testSubject = new BiListValuedHashMap<>();
		testSubject.put("One", "1");
		testSubject.put("Uno", "1");
		testSubject.put("Two", "2");
		testSubject.put("Two", "II");

		Collection<String> oneVals = testSubject.getVals("One");
		Collection<String> unoVals = testSubject.getVals("Uno");
		Collection<String> twoVals = testSubject.getVals("Two");

		Collection<String> keys1 = testSubject.getKeys("1");
		Collection<String> keys2 = testSubject.getKeys("2");
		Collection<String> keysII = testSubject.getKeys("II");

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

	@Test
	public void inverseFullBiMultiMap()
	{
		BiListValuedMap<String, Integer> testSubject = new BiListValuedHashMap<>();
		testSubject.put("One", 1);
		testSubject.put("Uno", 1);
		testSubject.put("Two", 2);
		testSubject.put("Two", 10);
		BiListValuedMap<Integer, String> inverted = testSubject.inverse();

		Collection<Integer> oneKeys = inverted.getKeys("One");
		Collection<Integer> unoKeys = inverted.getKeys("Uno");
		Collection<Integer> twoKeys = inverted.getKeys("Two");

		Collection<String> vals1 = inverted.getVals(1);
		Collection<String> vals2 = inverted.getVals(2);
		Collection<String> vals10 = inverted.getVals(10);

		assertEquals(1, oneKeys.size());
		assertTrue(oneKeys.contains(1));
		assertEquals(1, unoKeys.size());
		assertTrue(unoKeys.contains(1));
		assertEquals(2, twoKeys.size());
		assertTrue(twoKeys.contains(2));
		assertTrue(twoKeys.contains(10));

		assertEquals(2, vals1.size());
		assertTrue(vals1.contains("One"));
		assertTrue(vals1.contains("Uno"));
		assertEquals(1, vals2.size());
		assertTrue(vals2.contains("Two"));
		assertEquals(1, vals10.size());
		assertTrue(vals10.contains("Two"));
	}
}
