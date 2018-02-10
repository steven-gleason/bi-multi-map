package bimap;

import java.util.Map;
import java.util.Set;

public interface BiMapSet<K,V> extends BiMap<K,V>
{
	public Set<V> getValSet(K key);
	public Set<K> getKeySet(V val);
}
