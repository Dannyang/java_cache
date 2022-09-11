package cache.core;

import cache.Cache;
import cache.ICacheEvict;
import cache.ICacheEvictContext;
import cache.ICacheExpire;
import cache.context.evict.CacheEvictContext;

import java.security.Key;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class CacheCore<K, V> implements Cache<K, V> {
    private Map<K, V> cacheMap;//用来缓存用的map
    private int sizeLimit;// 限制缓存大小
    private ICacheExpire<K, V> iCacheExpire; //过期策略
    private ICacheEvict<K, V> iCacheEvict; //数据满时的数据淘汰策略

    /**
     * ]
     *
     * @param key        需要过期的key
     * @param expireTime key的保质期
     * @return
     */
    @Override
    public Cache<K, V> expire(K key, long expireTime) {
        // 当前时间+expireTime = k-v过期的时间点
        long theTimeToExpire = System.currentTimeMillis() + expireTime;
        this.expireAt(key, theTimeToExpire);
        return this;
    }

    public Cache<K, V> setSizeLimit(int sizeLimit) {
        this.sizeLimit = sizeLimit;
        return this;
    }

    @Override
    public ICacheEvict<K, V> evict() {
        return this.iCacheEvict;
    }

    @Override
    public Cache<K, V> expireStrategy(ICacheExpire<K, V> expire) {
        this.iCacheExpire = expire;
        return this;
    }

    @Override
    public Cache<K, V> evictStrategy(ICacheEvict<K, V> evict) {
        this.iCacheEvict = evict;
        return this;
    }

    @Override
    public Cache<K, V> cacheMap(Map<K, V> map) {
        this.cacheMap = map;
        return this;
    }

    /**
     * @param key
     * @param time
     * @return
     */
    @Override
    public Cache<K, V> expireAt(K key, long time) {
        this.iCacheExpire.expire(key, time);
        return this;
    }

    @Override
    public int size() {
        return cacheMap.size();
    }

    @Override
    public boolean isEmpty() {
        return cacheMap.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return cacheMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return cacheMap.containsKey(value);
    }

    @Override
    public V get(Object key) {
        return this.cacheMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        CacheEvictContext<K, V> cacheEvictContext = new CacheEvictContext<>();
        cacheEvictContext.setCache(this).setKey(key).setLimit(this.sizeLimit);
        this.iCacheEvict.evict(cacheEvictContext);
        return this.cacheMap.put(key, value);
    }

    /**
     * 执行清除操作时直接代用用于储存缓存的map的remove方法
     *
     * @param key
     * @return
     */
    @Override
    public V remove(Object key) {
        return this.cacheMap.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        this.cacheMap.putAll(m);
    }

    @Override
    public void clear() {
        this.cacheMap.clear();
    }

    @Override
    public Set<K> keySet() {
        return this.cacheMap.keySet();
    }

    @Override
    public Collection<V> values() {
        return this.cacheMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.cacheMap.entrySet();
    }
}
