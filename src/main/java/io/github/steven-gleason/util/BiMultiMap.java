package io.github.steven_gleason.util;

import java.util.Collection;
import java.util.Map;

public interface BiMultiMap<K,V>
{
	public void add(K key, V val);
	public Collection<V> getVals(K key);
	public Collection<K> getKeys(V val);
}
