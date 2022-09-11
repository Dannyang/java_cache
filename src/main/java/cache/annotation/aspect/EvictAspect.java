package cache.annotation.aspect;

import cache.ICacheEvict;
import cache.annotation.EvictInterceptor;
import cache.context.evict.EvictInterceptorContext;

import java.lang.reflect.Method;

public class EvictAspect<K, V> implements EvictInterceptor<K, V> {


    @Override
    public void before(EvictInterceptorContext<K, V> context) {
        ICacheEvict<K, V> evict = context.cache().evict();
        Method method = context.method();
        K param = (K) context.params()[0];
        if ("remove".equals(method.getName())) {
            evict.updateKey(param);
        } else {
            evict.removeKey(param);
        }
    }

    @Override
    public void after(EvictInterceptorContext<K, V> context) {

    }
}
