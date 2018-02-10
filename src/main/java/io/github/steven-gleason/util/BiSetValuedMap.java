package io.github.steven_gleason.util;

import java.util.Set;

public interface SetBiMultiMap<K,V> extends BiMultiMap<K,V>, SetMultiMap<K,V>
{
	/**
		* Synonym of SetMultiMap.getSet(K key)
		*/
	public Set<V> getValSet(K key);

	/**
		* Reversing the relationship of an ordinary SetMultiMap, use a Value to get a Set of Keys.
		*/
	public Set<K> getKeySet(V val);
}
