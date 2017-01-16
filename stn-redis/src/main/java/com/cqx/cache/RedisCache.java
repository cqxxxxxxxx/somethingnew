package com.cqx.cache;

import org.springframework.cache.Cache;
import java.util.concurrent.Callable;

/**
 * 可以自定义redis的具体get set操作
 * 不过已经有RedisTemplate了，不需要这个东西
 * Created by Shan on 2017/1/16.
 */
public class RedisCache implements Cache {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object o) {
        return null;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        return null;
    }

    @Override
    public void put(Object o, Object o1) {

    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        return null;
    }

    @Override
    public void evict(Object o) {

    }

    @Override
    public void clear() {

    }
}
