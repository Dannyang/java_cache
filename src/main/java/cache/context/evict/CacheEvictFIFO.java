package cache.context.evict;

import cache.Cache;
import cache.ICacheEntry;
import cache.ICacheEvict;
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
    private int limitSize;
    private final Queue<K> queue = new LinkedList<>();

    public CacheEvictFIFO(int limitSize) {
        this.limitSize = limitSize;
    }

    @Override
    public ICacheEntry<K, V> evict(Cache<K, V> cache) {
        ICacheEntry<K, V> result = null;
        // 如果此时缓存的元素数量大小超过限定大小则删除最先入队的元素
        if (this.limitSize < cache.size()) {
            // 队列remove直接移除头元素并返回头元素
            K theKeyRemoved = queue.remove();
            V theValueRemoved = cache.remove(theKeyRemoved);
            result = new CacheEntry<>(theKeyRemoved, theValueRemoved);

        }
        // 删除最先入队的元素后放入新的元素
//        K newKey = context.key();
//        queue.add(newKey);
        return result;
    }

    @Override
    public void updateKey(Object key) {

    }

    @Override
    public void removeKey(Object key) {

    }
}
