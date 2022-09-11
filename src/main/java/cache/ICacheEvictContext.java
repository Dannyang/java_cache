package cache;

/**
 * 用于存放需要执行驱逐策略的缓存信息
 * @author binbin.hou
 * @since 0.0.2
 */
public interface ICacheEvictContext<K, V> {

    /**
     * map 信息
     * @return map
     * @since 0.0.2
     */
    Cache<K, V> cache();

    /**
     * 大小限制
     * @return 大小限制
     * @since 0.0.2
     */
    int limit();

    /**
     * 新增的元素的key
     *
     * @return
     */
    K key();

}
