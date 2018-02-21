package io.github.steven_gleason.util;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.ListValuedMap;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;

public class BiListValuedHashMap<K,V> implements BiListValuedMap<K,V>
{
	private ListValuedMap<K,V> keyValMap;
	private ListValuedMap<V,K> valKeyMap;

	public BiListValuedHashMap()
	{
		keyValMap = new ArrayListValuedHashMap<>();
		valKeyMap = new ArrayListValuedHashMap<>();
	}

	@Override
	public Map<K,Collection<V>> asMap()
	{
		return keyValMap.asMap();
	}

	@Override
	public void clear()
	{
		keyValMap.clear();
		valKeyMap.clear();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return keyValMap.containsKey(key);
	}

	@Override
	public boolean containsMapping(Object key, Object val)
	{
		return keyValMap.containsMapping(key, val);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return valKeyMap.containsKey(value);
	}

	@Override
	public Collection<Map.Entry<K,V>>  entries()
	{
		return keyValMap.entries();
	}

	@Override
	public List<V> get(K key)
	{
		return getVals(key);
	}

	@Override
	public boolean isEmpty()
	{
		return keyValMap.isEmpty();
	}

	@Override
	public MultiSet<K> keys()
	{
		return keyValMap.keys();
	}

	@Override
	public Set<K> keySet()
	{
		return keyValMap.keySet();
	}

	@Override
	public MapIterator<K,V> mapIterator()
	{
		return keyValMap.mapIterator();
	}

	@Override
	public boolean put(K key, V val)
	{
		keyValMap.put(key, val);
		valKeyMap.put(val, key);

		return true; // Lists always grow
	}

	@Override
	public boolean putAll(K key, Iterable<? extends V> values)
	{
		boolean changed = values.iterator().hasNext();

		keyValMap.putAll(key, values);
		for (V val : values)
		{
			valKeyMap.put(val, key);
		}

		return changed;
	}

	@Override
	public boolean putAll(Map<? extends K,? extends V> map)
	{
		boolean changed = false;
		for (Map.Entry<? extends K,? extends V> entry : map.entrySet())
		{
			changed |= put(entry.getKey(), entry.getValue());
		}

		return changed;
	}

	@Override
	public boolean putAll(MultiValuedMap<? extends K,? extends V> map)
	{
		//TODO: optimize to prevent repeated lookups of key
		boolean changed = false;
		Collection<? extends Map.Entry<? extends K,? extends V>> entries = map.entries();
		for (Map.Entry<? extends K,? extends V> entry : entries)
		{
			changed |= put(entry.getKey(), entry.getValue());
		}

		return changed;
	}

	@Override
	public List<V> remove(Object key)
	{
		List<V> removedVals = keyValMap.remove(key);

		for (V val : removedVals)
		{
			valKeyMap.get(val).remove(key);
		}

		return removedVals;
	}

	@Override
	public boolean removeMapping(Object key, Object val)
	{
		keyValMap.removeMapping(key, val);
		return valKeyMap.removeMapping(val, key);
	}

	@Override
	public int size()
	{
		return keyValMap.size();
	}

	@Override
	public Collection<V> values()
	{
		return keyValMap.values();
	}

	@Override
	public List<V> getVals(K key)
	{
		return keyValMap.get(key);
	}

	@Override
	public List<K> getKeys(V val)
	{
		return valKeyMap.get(val);
	}

	@Override
	public BiListValuedHashMap<V,K> inverse()
	{
		BiListValuedHashMap<V,K> newMap = new BiListValuedHashMap<>();
		newMap.keyValMap = valKeyMap;
		newMap.valKeyMap = keyValMap;
		return newMap;
	}
}
