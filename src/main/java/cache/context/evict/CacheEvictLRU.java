package cache.context.evict;

import cache.Cache;
import cache.ICacheEntry;
import cache.ICacheEvict;

public class CacheEvictLRU<K, V> implements ICacheEvict<K, V> {
    @Override
    public ICacheEntry<K, V> evict(Cache<K, V> cache) {
        return null;
    }

    @Override
    public void updateKey(Object key) {

    }

    @Override
    public void removeKey(Object key) {

    }
}
