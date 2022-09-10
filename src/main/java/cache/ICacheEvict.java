package cache;

import cache.context.evict.ICacheEvictContext;

/**
 * 缓存刷新和淘汰策略
 *
 * @param <K>
 * @param <V>
 */
public interface ICacheEvict<K, V> {
    /**
     * 驱除策略
     *
     * @param context 上下文
     * @return 被移除的明细，没有时返回 null
     * @since 0.0.2
     */
   ICacheEntry<K, V> evict(Cache<K,V> cache);

    /**
     * 更新 key 信息
     *
     * @param key key
     * @since 0.0.11
     */
    void updateKey(final K key);

    /**
     * 删除 key 信息
     *
     * @param key key
     * @since 0.0.11
     */
    void removeKey(final K key);
}
