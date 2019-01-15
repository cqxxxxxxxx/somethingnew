package com.cqx.cqxhat.support.cache;

import java.util.List;

/**
 * 缓存池接口
 *
 * @param <T> key
 * @param <D> value
 */
public interface CachePool<T, D> {

    D get(T key);

    List<D> getAll();

    void put(T key, D value);

    boolean evict(T key);

    boolean evictAll();
}
