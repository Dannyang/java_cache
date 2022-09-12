package cache.proxy;

import cache.Cache;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibCacheProxy implements MethodInterceptor {
    // 被代理的对象
    private Cache cache;
    public CglibCacheProxy(Cache cache){
        this.cache = cache;
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("调用前");
        Object o1 = methodProxy.invokeSuper(cache, objects);
        System.out.println("调用后");
        return o1;
    }

    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        // 目标对象类
        enhancer.setSuperclass(cache.getClass());
        enhancer.setCallback(this);
        // 创建目标对象类的子类示例作为代理
        return enhancer.create();
    }
}
