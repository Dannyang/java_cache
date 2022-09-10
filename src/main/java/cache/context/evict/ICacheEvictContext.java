package cache.context.evict;

import cache.Cache;

/**
 * 驱逐策略
 *
 * @param <K>
 * @param <V>
 */
public interface ICacheEvictContext<K, V> {
    /**
     * 新加的 key
     * @return key
     * @since 0.0.2
     */
    K key();

    /**
     * cache 实现
     * @return map
     * @since 0.0.2
     */
    Cache<K, V> cache();

    /**
     * 大小
     * @return 大小
     * @since 0.0.2
     */
    int limit();
}
