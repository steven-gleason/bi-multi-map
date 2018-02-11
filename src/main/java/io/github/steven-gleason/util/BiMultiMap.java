package io.github.steven_gleason.util;

import java.util.Collection;

import org.apache.commons.collections4.MultiValuedMap;

public interface BiMultiMap<K,V> extends MultiValuedMap<K,V>
{
	/**
	 * Synonym for MultiValuedMap.get(K key)
	 */
	public Collection<V> getVals(K key);

	/**
	 * Reversing the relationship of an ordinary MultiValuedMap, use a Value to get a Collection of Keys.
	 */
	public Collection<K> getKeys(V val);
}
