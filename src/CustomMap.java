public interface CustomMap<K,V> {


    V put(K key, V value);

    V get(Object key);

    Boolean remove(Object key);
}
