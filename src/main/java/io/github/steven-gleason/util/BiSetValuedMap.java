package io.github.steven_gleason.util;

import java.util.Set;

import org.apache.commons.collections4.SetValuedMap;

public interface BiSetValuedMap<K,V> extends BiMultiMap<K,V>, SetValuedMap<K,V>
{
	/**
		* Synonym of SetValuedMap.get(K key)
		*/
	public Set<V> getValSet(K key);

	/**
		* Reversing the relationship of an ordinary SetValuedMap, use a Value to get a Set of related Keys.
		*/
	public Set<K> getKeySet(V val);
}
