package io.github.steven_gleason.util;

import java.util.List;

public interface ListMultiMap<K,V> extends MultiMap<K,V>
{
	public List<V> getList(K key);
}
