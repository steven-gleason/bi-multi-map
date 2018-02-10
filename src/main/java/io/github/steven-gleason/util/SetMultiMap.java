package io.github.steven_gleason.util;

import java.util.Set;

public interface SetMultiMap<K,V> extends MultiMap<K,V>
{
	public Set<V> getSet(K key);
}
