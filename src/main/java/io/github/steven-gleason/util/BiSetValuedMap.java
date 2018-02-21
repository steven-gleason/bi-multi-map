package io.github.steven_gleason.util;

import java.util.Set;

import org.apache.commons.collections4.SetValuedMap;

public interface BiSetValuedMap<K,V> extends BiMultiMap<K,V>, SetValuedMap<K,V>
{
	/**
	 * Synonym of SetValuedMap.get(K key)
	 */
	@Override
	public Set<V> getVals(K key);

	/**
	 * Reversing the relationship of an ordinary SetValuedMap, use a Value to get a Set of related Keys.
	 */
	@Override
	public Set<K> getKeys(V val);

	@Override
	public BiSetValuedMap<V,K> inverse();
}
