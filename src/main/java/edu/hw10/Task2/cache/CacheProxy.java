package edu.hw10.Task2.cache;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private final String directory;

    private CacheProxy(Object target, String directory) {
        this.target = target;
        this.cache = new HashMap<>();
        this.directory = directory;
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, String directory) {
        return (T) Proxy.newProxyInstance(
            target.getClass().getClassLoader(),
            target.getClass().getInterfaces(),
            new CacheProxy(target, directory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            String key = generateCacheKey(method, args);
            if (cache.containsKey(key)) {
                return cache.get(key);
            } else {
                Object result = method.invoke(target, args);
                cache.put(key, result);
                if (method.getAnnotation(Cache.class).persist()) {
                    saveToDisk(key, result);
                }
                return result;
            }
        }

        return method.invoke(target, args);
    }

    private void saveToDisk(String key, Object result) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(directory, true))) {
            bw.write(key + " = " + result.toString());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String generateCacheKey(Method method, Object[] args) {
        StringBuilder key = new StringBuilder(method.getName());

        for (var object : args) {
            key.append(", ").append(object);
        }

        return key.toString();
    }
}
