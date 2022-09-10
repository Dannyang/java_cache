package cache;

/**
 * 缓存的明细
 *
 * @param <K>
 * @param <V>
 */
public interface ICacheEntry<K, V> {
    K key();

    V value();
}
