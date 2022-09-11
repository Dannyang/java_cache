package cache.annotation;

import cache.ICacheEvictContext;
import cache.context.evict.EvictInterceptorContext;

import java.lang.annotation.*;


public interface EvictInterceptor<K,V> {
    void before(EvictInterceptorContext<K,V> context);

    void after(EvictInterceptorContext<K,V> context);

}
