package edu.hw10.task2;

import java.lang.reflect.Proxy;

public class CacheProxy {

    private CacheProxy() {}

    public static <T> T create(T cacheObject, Class<T> clazz) {
        return clazz.cast(Proxy.newProxyInstance(
            clazz.getClassLoader(),
            new Class[] {clazz},
            new CacheInvocationHandler(cacheObject)
        ));
    }
}
