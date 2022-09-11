package cache.context.evict;

import cache.Cache;
import cache.ICacheEvictContext;

public class CacheEvictContext<K, V> implements ICacheEvictContext<K, V> {

    // 新增数据的key
    private K key;
    // 缓存
    private Cache<K, V> cache;
    // 缓存限定大小
    private int limit;

    @Override
    public K key() {
        return key;
    }

    public CacheEvictContext<K, V> setKey(K key) {
        this.key = key;
        return this;
    }

    public Cache<K, V> cache() {
        return cache;
    }

    public CacheEvictContext<K, V> setCache(Cache<K, V> cache) {
        this.cache = cache;
        return this;
    }

    @Override
    public int limit() {
        return limit;
    }

    public CacheEvictContext<K, V> setLimit(int limit) {
        this.limit = limit;
        return this;
    }
}
