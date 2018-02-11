package io.github.steven_gleason.util;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.iterators.EntrySetMapIterator;
import org.apache.commons.collections4.multiset.HashMultiSet;

public class BiListValuedHashMap<K,V> implements BiListValuedMap<K,V>
{
	private Map<K,List<V>> keyValMap;
	private Map<V,List<K>> valKeyMap;

	public BiListValuedHashMap()
	{
		keyValMap = new HashMap<>();
		valKeyMap = new HashMap<>();
	}

	@Override
	public Map<K,Collection<V>> asMap()
	{
		return new HashMap<K,Collection<V>>(keyValMap);
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
		return keyValMap.get(key).contains(val);
	}

	@Override
	public boolean containsValue(Object value)
	{
		return valKeyMap.containsKey(value);
	}

	@Override
	public Collection<Map.Entry<K,V>>  entries()
	{
		Collection<Map.Entry<K,V>> allEntries = new LinkedList<>();
		for (Map.Entry<K,List<V>> listEntry : keyValMap.entrySet())
		{
			for (V val : listEntry.getValue())
			{
				allEntries.add(new AbstractMap.SimpleEntry(listEntry.getKey(), val));
			}
		}
		return allEntries;
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
		MultiSet keySet = new HashMultiSet();
		for (Map.Entry<K,List<V>> entry : keyValMap.entrySet())
		{
			keySet.add(entry.getKey(), entry.getValue().size());
		}
		return keySet;
	}

	@Override
	public Set<K> keySet()
	{
		return keyValMap.keySet();
	}

	@Override
	public MapIterator<K,V> mapIterator()
	{
		return new EntrySetMapIterator(keyValMap);
	}

	@Override
	public boolean put(K key, V val)
	{
		List<V> vals = getVals(key);
		List<K> keys = getKeys(val);

		vals.add(val);
		keys.add(key);

		return true; // Lists always grow
	}

	@Override
	public boolean putAll(K key, Iterable<? extends V> values)
	{
		List<V> vals = getVals(key);
		int previousSize = vals.size();
		boolean changed = values.iterator().hasNext();

		for (V val : values)
		{
			vals.add(val);
			getKeys(val).add(key);
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
		if (removedVals == null)
		{
			removedVals = new LinkedList<>();
		}

		for (V val : removedVals)
		{
			valKeyMap.get(val).remove(key);
		}

		return removedVals;
	}

	@Override
	public boolean removeMapping(Object key, Object val)
	{
		keyValMap.get(key).remove(val);
		return valKeyMap.get(val).remove(key);
	}

	@Override
	public int size()
	{
		return keyValMap.size();
	}

	@Override
	public Collection<V> values()
	{
		return valKeyMap.keySet();
	}

	@Override
	public List<V> getVals(K key)
	{
		List<V> vals = keyValMap.get(key);

		if (vals == null)
		{
			vals = new LinkedList<>();
			keyValMap.put(key, vals);
		}

		return vals;
	}

	@Override
	public List<K> getKeys(V val)
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
