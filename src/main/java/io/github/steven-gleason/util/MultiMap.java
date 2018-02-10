package io.github.steven_gleason.util;

import java.util.Collection;

public interface MultiMap<K,V>
{
	public void add(K key, V val);
	public Collection<V> get(K key);
}
