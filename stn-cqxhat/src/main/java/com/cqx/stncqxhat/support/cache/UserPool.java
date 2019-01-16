package com.cqx.stncqxhat.support.cache;

import com.cqx.stncqxhat.model.User;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/15
 */
public class UserPool implements CachePool<String, User> {

    private UserPool() {
    }

    private static final UserPool INSTANCE = new UserPool();
    private static final Cache<String, User> userPool;

    static {
        //todo 序列化使用protobuf
        String alias = UserPool.class.getSimpleName();
        CacheManagerBuilder builder = CacheManagerBuilder.newCacheManagerBuilder();
        CacheConfiguration configuration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, User.class, ResourcePoolsBuilder.heap(10))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(2, TimeUnit.HOURS)))
                .build();
        CacheManager cacheManager = builder.withCache(alias, configuration).build();
        cacheManager.init();
        userPool = cacheManager.getCache(alias, String.class, User.class);
    }

    public static CachePool<String, User> getInstance() {
        return INSTANCE;
    }

    @Override
    public User get(String key) {
        return userPool.get(key);
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userPool.forEach(x -> users.add(x.getValue()));
        return users;
    }

    @Override
    public void put(String key, User value) {
        userPool.put(key, value);
    }

    @Override
    public boolean evict(String key) {
        userPool.remove(key);
        return true;
    }

    @Override
    public boolean evictAll() {
        userPool.clear();
        return true;
    }
}
