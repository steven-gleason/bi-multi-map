package io.github.steven_gleason.util;

import java.util.List;

import org.apache.commons.collections4.ListValuedMap;

public interface BiListValuedMap<K,V> extends BiMultiMap<K,V>, ListValuedMap<K,V>
{
	/**
		* Synonym of ListValuedMap.get(K key)
		*/
	public List<V> getValList(K key);

	/**
		* Reversing the relationship of an ordinary ListValuedMap, use a Value to get a List of related Keys.
		*/
	public List<K> getKeyList(V val);
}
