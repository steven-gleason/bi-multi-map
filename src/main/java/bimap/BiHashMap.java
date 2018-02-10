package bimap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BiHashMap<K,V> implements BiMap<K,V>
{
	// TODO: use Collection, specific implementations choose type
	private Map<K,List<V>> keyValMap;
	private Map<V,List<K>> valKeyMap;

	public BiHashMap()
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
	public Map<K,List<V>> getKeyMap()
	{
		return keyValMap;
	}

	@Override
	public Map<V,List<K>> getValMap()
	{
		return valKeyMap;
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

	private List<V> getValsNotNull(K key)
	{
		List<V> vals = keyValMap.get(key);

		if (vals == null)
		{
			vals = new ArrayList<>();
			keyValMap.put(key, vals);
		}

		return vals;
	}

	private List<K> getKeysNotNull(V val)
	{
		List<K> keys = valKeyMap.get(val);

		if (keys == null)
		{
			keys = new ArrayList<>();
			valKeyMap.put(val, keys);
		}

		return keys;
	}
}
