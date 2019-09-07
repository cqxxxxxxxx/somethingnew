package com.cqx.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shan on 2017/1/13.
 */
@Configuration
@EnableCaching
public class RedisConfig {

    /**
     * 创建Jedis工厂
     * 里面自动由spring-boot根据yml中的配置完成自动配置，也可以自己set方法注入
     * 覆盖掉了spring自动配置的。。默认了LOCALHOST,所以连不上远程redis ,注释掉就好了
     * @return
     */
//    @Bean
//    JedisConnectionFactory jedisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }

    /**
     * 自定义的template
     * 用于操作对象的在redis数据库中的CRUD
     * 其中要设置key和value的序列化相关的东西
     * ps. spring有提供StringRedisTemplate之类的可以直接用的
     *
     * @param factory
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> userRedisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    /**
     * cacheManager
     *
     * @param userRedisTemplate
     * @return
     */
    @Bean
    public CacheManager redisCacheManager(RedisTemplate redisTemplate) {
        List<String> cacheNames = new ArrayList<>();
        cacheNames.add("user");
        cacheNames.add("test1");
        cacheNames.add("test2");
        RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate);
        redisCacheManager.setCacheNames(cacheNames);    //可以预先初始化这些cache
        redisCacheManager.afterPropertiesSet(); //在属性设置好后执行此方法，进行缓存初始化等等操作。
        return redisCacheManager;
    }


    /**
     * 自定义的缓存key的生成策略
     * Object target, Method method, Object... params
     * 生成的key为类名+方法名+参数名
     *
     * @return
     */
    @Bean
    public KeyGenerator customKeyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(o.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }
}
