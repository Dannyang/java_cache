import cache.context.evict.CacheEvictFIFO;
import cache.context.evict.CacheEvictLRU;
import cache.core.CacheCore;
import cache.expire.CacheExpire;
import org.junit.Test;

import java.util.HashMap;

public class TestMyCache {

    @Test
    public void testExpire() throws InterruptedException {
        CacheCore<String, String> cacheCore = new CacheCore<>();
        cacheCore.cacheMap(new HashMap<>());
        cacheCore.expireStrategy(new CacheExpire<>(cacheCore));
        cacheCore.evictStrategy(new CacheEvictFIFO<>(2));
        cacheCore.put("1", "1");
        cacheCore.put("2", "2");
        cacheCore.put("3","3");
        cacheCore.expire("1", 1000L);
        cacheCore.expire("2", 6000L);
        cacheCore.expire("3", 18000L);
        Thread.sleep(1000L);
        System.out.println(cacheCore.size());
        Thread.sleep(5000L);
        System.out.println(cacheCore.size());
        Thread.sleep(12000L);
        System.out.println(cacheCore.size());
    }

    @Test
    public void testEvict() {
        CacheCore<String, String> cacheCore = new CacheCore<>();
        cacheCore.setSizeLimit(2);
        cacheCore.cacheMap(new HashMap<>());
        cacheCore.evictStrategy(new CacheEvictLRU<>());
        cacheCore.put("1", "1");
        cacheCore.get("1");
        cacheCore.put("2", "2");
        cacheCore.put("3","3");
        System.out.println(cacheCore.size());

    }
}
