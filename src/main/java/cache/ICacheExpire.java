package cache;

/**
 * 过期策略
 *
 * @param <K>
 * @param <V>
 */
public interface ICacheExpire<K, V> {
    /**
     * 指定过期信息
     *
     * @param key      key
     * @param expireAt 什么时候过期
     * @since 0.0.3
     */
    void expire(final K key, final long expireAt);

}
