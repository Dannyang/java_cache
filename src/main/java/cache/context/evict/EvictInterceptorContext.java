package cache.context.evict;

import cache.Cache;

import java.lang.reflect.Method;

public class EvictInterceptorContext<K, V> {
    private Cache<K, V> cache;

    private Method method;

    private Object[] params;


    public EvictInterceptorContext<K, V> cache(Cache<K, V> cache) {
        this.cache = cache;
        return this;
    }

    public Method method() {
        return method;
    }

    public Cache<K, V> cache() {
        return cache;
    }

    public Object[] params() {
        return params;
    }

    public EvictInterceptorContext<K, V> method(Method method) {
        this.method = method;
        return this;
    }


    public EvictInterceptorContext<K, V> params(Object[] params) {
        this.params = params;
        return this;
    }
}
