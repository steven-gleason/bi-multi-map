package bimap;

import java.util.List;
import java.util.Map;

public interface BiMap<K,V>
{
	public void add(K key, V val);
	public Map<K,List<V>> getKeyMap();
	public Map<V,List<K>> getValMap();
	public List<V> getVals(K key);
	public List<K> getKeys(V val);
}
