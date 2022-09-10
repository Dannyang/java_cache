package cache;

import java.util.Map;

/**
 * 缓存采用map结构需要采用数据淘汰策略避免数据一直扩张
 * 缓存接口
 *
 * @param <K>
 * @param <V>
 */
public interface Cache<K, V> extends Map<K, V> {
    // 指定Key——Value的过期时间
    Cache<K, V> expire(K key, long expireTime);

    // 指定驱逐策略
    ICacheEvict<K, V> evict();


    /**
     * 指定用来缓存数据的map,可以用linkedHashMap并重写removeEldestEntry方法实现LRU但会有安全问题，需要在put和get时加锁
     * 也可以使用ConcurrentHashMap
     *
     * @param map
     * @return
     */
    Cache<K, V> cacheMap(Map<K, V> map);

    Cache<K, V> expireAt(K key, long time);

    Cache<K, V> expireStrategy(ICacheExpire<K, V> expire);

    Cache<K, V> evictStrategy(ICacheEvict<K, V> expire);


//    /**
//     * 获取缓存的过期处理类
//     * @return 处理类实现
//     * @since 0.0.4
//     */
//    ICacheExpire<K,V> expire();
}
