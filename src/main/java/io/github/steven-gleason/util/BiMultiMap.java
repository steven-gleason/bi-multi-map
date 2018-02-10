package io.github.steven_gleason.util;

import java.util.Collection;

public interface BiMultiMap<K,V> extends MultiMap<K,V>
{
	/**
		* Synonym for MultiMap.get(K key)
		*/
	public Collection<V> getVals(K key);

	/**
		* Reversing the relationship of an ordinary MultiMap, use a Value to get a Collection of Keys.
		*/
	public Collection<K> getKeys(V val);
}
