package io.github.steven_gleason.util;

import java.util.Map;
import java.util.List;

public interface BiMultiMapList<K,V> extends BiMultiMap<K,V>
{
	public List<V> getValList(K key);
	public List<K> getKeyList(V val);
}
