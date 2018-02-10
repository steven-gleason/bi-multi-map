package io.github.steven_gleason.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ListBiMultiHashMap<K,V> implements ListBiMultiMap<K,V>
{
	private Map<K,List<V>> keyValMap;
	private Map<V,List<K>> valKeyMap;

	public ListBiMultiHashMap()
	{
		// TODO: implement generic class that takes a Map class parameter
		keyValMap = new HashMap<>();
		valKeyMap = new HashMap<>();
	}

	@Override
	public void add(K key, V val)
	{
		List<V> vals = getValsNotNull(key);
		List<K> keys = getKeysNotNull(val);

		vals.add(val);
		keys.add(key);
	}

	@Override
	public Collection<V> get(K key)
	{
		return getVals(key);
	}

	@Override
	public Collection<V> getVals(K key)
	{
		return keyValMap.get(key);
	}

	@Override
	public Collection<K> getKeys(V val)
	{
		return valKeyMap.get(val);
	}

	@Override
	public List<V> getList(K key)
	{
		return getValList(key);
	}

	@Override
	public List<V> getValList(K key)
	{
		return keyValMap.get(key);
	}

	@Override
	public List<K> getKeyList(V val)
	{
		return valKeyMap.get(val);
	}

	private List<V> getValsNotNull(K key)
	{
		List<V> vals = keyValMap.get(key);

		if (vals == null)
		{
			vals = new LinkedList<>();
			keyValMap.put(key, vals);
		}

		return vals;
	}

	private List<K> getKeysNotNull(V val)
	{
		List<K> keys = valKeyMap.get(val);

		if (keys == null)
		{
			keys = new LinkedList<>();
			valKeyMap.put(val, keys);
		}

		return keys;
	}
}
