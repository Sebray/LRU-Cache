public interface LruCache<K, V> {
    V get(K key);

    void set(K key, V value);

    int getSize();

    int getLimit();

    String toStringValue();
}