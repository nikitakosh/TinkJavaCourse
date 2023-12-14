package edu.hw10.task2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheInvocationHandler implements InvocationHandler {

    public static final File CACHE_FILE = new File("src/main/resources/task2/cache.dat");
    private final Object object;
    private final Map<String, Object> localCache = new HashMap<>();
    private Map<String, Object> cacheFromFile = new HashMap<>();

    public CacheInvocationHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        String key = getKey(method, args);
        Cache cacheAnnotation = findCacheAnnotation(method);
        if (cacheAnnotation == null) {
            try {
                return method.invoke(object, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }
        Object result = getResultFromCache(key, cacheAnnotation);
        if (result != null) {
            return result;
        }
        try {
            result = method.invoke(object, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        putResultInCache(key, result, cacheAnnotation);
        return result;
    }

    private void putResultInCache(String key, Object result, Cache cacheAnnotation) {
        if (cacheAnnotation.persist()) {
            try {
                FileOutputStream fos = new FileOutputStream(CACHE_FILE);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                cacheFromFile.put(key, result);
                oos.writeObject(cacheFromFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            localCache.put(key, result);
        }
    }

    private Object getResultFromCache(String key, Cache cacheAnnotation) {
        if (cacheAnnotation.persist()) {
            cacheFromFile = getCache();
            if (cacheFromFile.containsKey(key)) {
                return cacheFromFile.get(key);
            }
        } else {
            if (localCache.containsKey(key)) {
                return localCache.get(key);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private Map<String, Object> getCache() {
        if (CACHE_FILE.length() == 0) {
            return cacheFromFile;
        }
        try {
            FileInputStream fis = new FileInputStream(CACHE_FILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return (Map<String, Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getKey(Method method, Object[] args) {
        StringBuilder key = new StringBuilder(method.getName());
        Arrays.stream(args).forEach(arg -> key.append(" ").append(arg.toString()));
        return key.toString();
    }

    public Cache findCacheAnnotation(Method method) {
        return method.getAnnotation(Cache.class);
    }
}
