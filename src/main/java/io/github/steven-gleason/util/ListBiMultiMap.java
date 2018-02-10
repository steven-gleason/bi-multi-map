package io.github.steven_gleason.util;

import java.util.List;

public interface ListBiMultiMap<K,V> extends BiMultiMap<K,V>, ListMultiMap<K,V>
{
	/**
		* Synonym of ListMultiMap.getList(K key)
		*/
	public List<V> getValList(K key);

	/**
		* Reversing the relationship of an ordinary ListMultiMap, use a Value to get a List of Keys.
		*/
	public List<K> getKeyList(V val);
}
