package io.github.steven_gleason.util;

import java.util.List;

import org.apache.commons.collections4.ListValuedMap;

public interface BiListValuedMap<K,V> extends BiMultiMap<K,V>, ListValuedMap<K,V>
{
	/**
	 * Synonym of ListValuedMap.get(K key)
	 */
	@Override
	public List<V> getVals(K key);

	/**
	 * Reversing the relationship of an ordinary ListValuedMap, use a Value to get a List of related Keys.
	 */
	@Override
	public List<K> getKeys(V val);

	@Override
	public BiListValuedMap<V,K> inverse();
}
