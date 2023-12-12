package edu.hw10.task2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<Method, CacheEntry> cache;

    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new ConcurrentHashMap<>();
    }

    public static <T> T create(T target, Class<?> targetClass) {
        Class<?>[] interfaces = {targetClass};
        CacheProxy handler = new CacheProxy(target);
        return (T) Proxy.newProxyInstance(targetClass.getClassLoader(), interfaces, handler);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Cache.class)) {
            CacheEntry entry = cache.get(method);

            if (entry == null) {
                entry = new CacheEntry(method, args);
                cache.put(method, entry);
            } else if (entry.isValid(args)) {
                return entry.getValue();
            }

            Object result = method.invoke(target, args);
            entry.setValue(result);

            if (method.getAnnotation(Cache.class).persist()) {
                persistCacheEntry(entry);
            }

            return result;
        } else {
            return method.invoke(target, args);
        }
    }

    private void persistCacheEntry(CacheEntry entry) {
        String cacheDir = System.getProperty("java.io.tmpdir");
        String cacheFile = cacheDir + entry.getMethod().getName()+".cache";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
            out.writeObject(entry.getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class CacheEntry {
        private final Method method;
        private final List<Object> arguments;
        private Object value;

        public CacheEntry(Method method, Object[] args) {
            this.method = method;
            this.arguments = Arrays.asList(args);
        }

        public Method getMethod() {
            return method;
        }

        public boolean isValid(Object[] args) {
            return arguments.equals(Arrays.asList(args));
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }
}
