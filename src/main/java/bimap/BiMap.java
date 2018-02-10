package bimap;

import java.util.Collection;
import java.util.Map;

public interface BiMap<K,V>
{
	public void add(K key, V val);
	public Collection<V> getVals(K key);
	public Collection<K> getKeys(V val);
}
