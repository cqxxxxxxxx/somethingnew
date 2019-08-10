package com.cqx.stncqxhat.config;

import com.cqx.stncqxhat.model.User;
import com.cqx.stncqxhat.plugin.PluginProvider;
import com.cqx.stncqxhat.plugin.PluginUtil;
import com.cqx.stncqxhat.service.ChatService;
import com.cqx.stncqxhat.support.cache.CachePool;
import com.cqx.stncqxhat.support.cache.UserPool;
import com.cqx.stncqxhat.support.util.ApplicationContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/16
 */
@Configuration
public class CommonConfig {

    @Autowired
    ChatService chatService;

    @Bean
    public CachePool<String, User> userPool() {
        return UserPool.getInstance();
    }

    @Bean
    public ApplicationContextUtil applicationContextUtil() {
        return new ApplicationContextUtil();
    }

}
