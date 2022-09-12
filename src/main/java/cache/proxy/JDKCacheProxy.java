package cache.proxy;

import cache.Cache;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKCacheProxy implements InvocationHandler {
    private Cache target;

    public JDKCacheProxy(Cache target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(target, args);
    }

    public Object getProxyInstance() {
        InvocationHandler jdkCacheProxy = new JDKCacheProxy(target);
        Object proxyInstance = Proxy.newProxyInstance(jdkCacheProxy.getClass().getClassLoader(), target.getClass().getInterfaces(), jdkCacheProxy);
        return proxyInstance;
    }
}
