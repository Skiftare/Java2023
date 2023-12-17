package edu.hw10.task2;

import edu.hw10.ErrorLogger;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final Map<String, Object> cache;
    private final File cacheDirectory;

    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new HashMap<>();
        this.cacheDirectory = new File(System.getProperty("java.io.tmpdir"), "cache");
        this.cacheDirectory.mkdirs();
    }

    public static <T> T create(T target, Class<?> targetClass) {
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            targetClass.getInterfaces(),
            new CacheProxy(target)
        );
    }

    private String generateFileName(Method method, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(cacheDirectory.getAbsolutePath())
            .append("/")
            .append(method.getName())
            .append("-")
            .append(Arrays.hashCode(args))
            .append(".ser");
        return sb.toString();
    }

    private Boolean isFileExist(Method method, Object[] args) {
        String fileName = generateFileName(method, args);
        File file = new File(fileName);
        return file.exists();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);

        Object result;
        if (cacheAnnotation.persist() && isFileExist(method, args)) {
            result = restoreCacheEntry(method, args);
            return result;
        } else if (cacheAnnotation.persist()) {
            result = method.invoke(target, args);
            persistCacheEntry(method, args, method.invoke(target, args));
        } else if (cache.containsKey(args)) {
            result = cache.get(generateFileName(method, args));
        } else {
            result = method.invoke(target, args);
            cache.put(generateFileName(method, args), result);
        }

        return result;

    }

    private Object restoreCacheEntry(Method method, Object[] args) {
        String fileName = generateFileName(method, args);
        File file = new File(fileName);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return ois.readObject();
            } catch (Exception e) {
                ErrorLogger.createLogError(e.getMessage());
            }
        }
        ErrorLogger.createLogError("file does not exist, null has been returned");
        return null;
    }

    private void persistCacheEntry(Method method, Object[] args, Object result) {
        String fileName = generateFileName(method, args);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(result);
        } catch (Exception e) {
            ErrorLogger.createLogError(e.getMessage());
        }
    }
}
