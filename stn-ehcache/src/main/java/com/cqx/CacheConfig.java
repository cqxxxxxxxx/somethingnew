package com.cqx;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * 当同一个类有多个bean的时候按类型注入就会失效
 * @autowired会自动按名称进行注入配置 如果不匹配则抛出异常
 * 也可以用@resource注解 用name属性指明注入哪个bean
 * Created by admin on 2017/3/23.
 */
@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, String> tokenCache1(){
        CacheManagerBuilder builder = CacheManagerBuilder.newCacheManagerBuilder();
        CacheManager cacheManager = builder.withCache("tokenCache1",
                CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();
        Cache tokenCache = cacheManager.getCache("tokenCache1", String.class, String.class);
        return tokenCache;
    }

    @Bean
    public Cache<String, String> tokenCache2(){
        CacheManagerBuilder builder = CacheManagerBuilder.newCacheManagerBuilder();
        CacheManager cacheManager = builder.withCache("tokenCache2",
                CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10)))
                .build();
        cacheManager.init();
        Cache tokenCache = cacheManager.getCache("tokenCache2", String.class, String.class);
        return tokenCache;
    }

    /**
     * 设置了过期时间  保存2s后自动失效
     * @return
     */
    @Bean
    public Cache<String, String> tokenCache3(){
        CacheManagerBuilder builder = CacheManagerBuilder.newCacheManagerBuilder();
        CacheConfiguration configuration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(String.class, String.class, ResourcePoolsBuilder.heap(10))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(2, TimeUnit.SECONDS)))
                .build();
        CacheManager cacheManager = builder.withCache("tokenCache3", configuration).build();
        cacheManager.init();
        Cache tokenCache = cacheManager.getCache("tokenCache3", String.class, String.class);
        return tokenCache;
    }
}
