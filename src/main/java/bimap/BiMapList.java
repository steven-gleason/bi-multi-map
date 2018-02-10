package bimap;

import java.util.Map;
import java.util.List;

public interface BiMapList<K,V> extends BiMap<K,V>
{
	public List<V> getValList(K key);
	public List<K> getKeyList(V val);
}
