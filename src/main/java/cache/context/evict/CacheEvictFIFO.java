package cache.context.evict;

import cache.Cache;
import cache.ICacheEntry;
import cache.ICacheEvict;
import cache.ICacheEvictContext;
import cache.core.CacheEntry;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 先进先出缓存删除策略
 *
 * @param <K>
 * @param <V>
 */
public class CacheEvictFIFO<K, V> implements ICacheEvict<K, V> {
    private final Queue<K> queue = new LinkedList<>();

    @Override
    public ICacheEntry<K, V> evict(ICacheEvictContext<K, V> context) {
        updateKey(context.key());
        ICacheEntry<K, V> result = null;
        // 如果此时缓存的元素数量大小超过限定大小则删除最先入队的元素
        if (context.limit() < context.cache().size()) {
            // 队列remove直接移除头元素并返回头元素
            K theKeyRemoved = queue.remove();
            V theValueRemoved = context.cache().remove(theKeyRemoved);
            result = new CacheEntry<>(theKeyRemoved, theValueRemoved);
        }
        return result;
    }

    @Override
    public void updateKey(K key) {
        this.queue.add(key);
    }

    @Override
    public void removeKey(Object key) {

    }
}
