package com.cqx.stncqxhat.config;

import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.support.cache.CachePool;
import com.cqx.stncqxhat.support.cache.UserPool;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/16
 */
@Configuration
public class CommonConfig {

    @Bean
    public CachePool<String, User> userPool() {
        return UserPool.getInstance();
    }

}
