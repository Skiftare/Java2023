package edu.hw10.task2;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class CacheProxy {
    private static Map<Object, Object> cache;

    public CacheProxy() {
        cache = new HashMap<>();
    }

    public static <T> T create(T target, Class<?> targetType) {
        Object proxy = Proxy.newProxyInstance(targetType.getClassLoader(),
            targetType.getInterfaces(),
            (proxyObj, method, args) -> {
                Method targetMethod = targetType.getMethod(method.getName(), method.getParameterTypes());
                Cache cacheAnnotation = targetMethod.getAnnotation(Cache.class);

                if (cacheAnnotation != null) {
                    CacheProxy cacheProxy = new CacheProxy() {
                        @Override
                        protected Object loadFromCache(Object key) {
                            // Загрузка значения из кэша по ключу
                            return cache.get(key);
                        }

                        @Override
                        protected void saveToCache(Object key, Object value) {
                            // Сохранение значения в кэш по ключу
                            cache.put(key, value);
                        }
                    };

                    return cacheProxy.invoke(target, targetMethod, args);
                }

                return method.invoke(target, args);
            }
        );

        return (T) proxy;
    }

    protected abstract Object loadFromCache(Object key);

    protected abstract void saveToCache(Object key, Object value);

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Генерируем уникальный ключ для метода и его аргументов
        Object key = generateKey(method, args);

        // Проверяем, есть ли значение в кэше
        Object value = cache.get(key);

        if (value == null) {
            // Значение отсутствует в кэше, вызываем реальный метод и сохраняем его результат в кэш
            value = method.invoke(this, args);
            cache.put(key, value);
        }

        return value;
    }

    private Object generateKey(Method method, Object[] args) {
        // TODO: это пиздец
        return Arrays.hashCode(args) + method.hashCode();
    }
}
