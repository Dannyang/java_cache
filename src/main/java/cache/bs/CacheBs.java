package cache.bs;

import cache.Cache;
import cache.ICacheEvict;
import cache.context.evict.CacheEvictFIFO;
import cache.core.CacheCore;

public class CacheBs<K, V> {
    private CacheBs() {
    }


    public static <K, V> CacheBs<K, V> getInstance() {
        return new CacheBs<>();
    }

    private int limit = Integer.MAX_VALUE;
    private ICacheEvict<K, V> cacheEvict = new CacheEvictFIFO<K, V>(10);

    public CacheBs<K, V> size(int size) {
        this.limit = size;
        return this;
    }

    public CacheBs<K, V> evictStrategy(ICacheEvict<K, V> cacheEvict) {
        this.cacheEvict = cacheEvict;
        return this;
    }

    // 构建缓存信息
    public Cache<K, V> build(){
        return  new CacheCore<>();
    }

}
