package com.cqx.config.spring;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/3
 */
@Component
public class PostDestroyConfig {

    @PostConstruct
    public void init() {
        System.out.println("=======PostConstruct");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("=======PreDestroy");
    }
}
