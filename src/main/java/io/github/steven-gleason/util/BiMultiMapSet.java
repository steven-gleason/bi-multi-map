package io.github.steven_gleason.util;

import java.util.Map;
import java.util.Set;

public interface BiMultiMapSet<K,V> extends BiMultiMap<K,V>
{
	public Set<V> getValSet(K key);
	public Set<K> getKeySet(V val);
}
