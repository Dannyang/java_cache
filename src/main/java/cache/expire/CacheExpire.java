package cache.expire;

import cache.Cache;
import cache.ICacheExpire;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CacheExpire<K, V> implements ICacheExpire<K, V> {

    // 需要进行过期处理的缓存
    private final Cache<K, V> cache;
    // 用来储存key对应的过期时间的map
    private final Map<K, Long> expireMap = new HashMap<>();

    // 用一个定时线程池用来执行删除操作
    private static final ScheduledExecutorService expireThread = Executors.newSingleThreadScheduledExecutor();

    /**
     * 指定某个key过期的时间
     * @param key      key
     * @param expireAt 什么时候过期
     */
    @Override
    public void expire(K key, long expireAt) {
        this.expireMap.put(key, expireAt);
    }

    public CacheExpire(Cache<K, V> cache) {
        this.cache = cache;
        init();
    }

    /**
     * 设定单例定时线程的执行参数
     */
    private void init() {
        expireThread.scheduleAtFixedRate(new ExpireCacheThread(), 1000, 1000, TimeUnit.MILLISECONDS);
    }

    /**
     * 用来执行清除过期缓存
     */
    private class ExpireCacheThread implements Runnable {

        @Override
        public void run() {
            Set<Map.Entry<K, Long>> entries = expireMap.entrySet();
            for (Map.Entry<K, Long> entry : entries) {
                expireCache(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * @param key
     * @param expireAt
     */
    private void expireCache(K key, long expireAt) {
        long nowTime = System.currentTimeMillis();
        if (nowTime > expireAt) {
            cache.remove(key);
        }

    }
}
