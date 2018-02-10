package io.github.steven_gleason.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.junit.Test;

public class BiMultiHashMapTest
{

	@Test
	public void emptyBiMultiMap()
	{
		BiMultiHashMap<String, Integer> testSubject = new BiMultiHashMap<>();
		Collection<String> keys = testSubject.getKeys(5);
		Collection<Integer> vals = testSubject.getVals("word");

		assertNull(keys);
		assertNull(vals);
	}

	@Test
	public void fullBiMultiMap()
	{
		BiMultiHashMap<String, String> testSubject = new BiMultiHashMap<>();
		testSubject.add("One", "1");
		testSubject.add("Uno", "1");
		testSubject.add("Two", "2");
		testSubject.add("Two", "II");

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
}
