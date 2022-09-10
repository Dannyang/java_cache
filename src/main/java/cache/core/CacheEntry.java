package cache.core;

import cache.ICacheEntry;

public class CacheEntry<K, V> implements ICacheEntry<K, V> {
    private K key;
    private V value;

    public CacheEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K key() {
        return this.key;
    }

    @Override
    public V value() {
        return this.value;
    }
}
