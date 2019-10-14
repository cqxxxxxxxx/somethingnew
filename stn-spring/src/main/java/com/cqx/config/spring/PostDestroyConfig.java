package com.cqx.config.spring;

import com.cqx.component.DemoAA;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
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
public class PostDestroyConfig implements InitializingBean, DisposableBean,
        ApplicationContextAware, BeanFactoryAware, BeanNameAware, CommandLineRunner, BeanPostProcessor {

    @Autowired
    private DemoAA demoAA;

    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=======PostDestroyConfig BeanPostProcessor postProcessBeforeInitialization");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("=======PostDestroyConfig BeanPostProcessor postProcessAfterInitialization");
        return bean;
    }

    @PostConstruct
    public void init() {
        System.out.println("=======PostDestroyConfig PostConstruct");
    }

    @PreDestroy
    public void destroyAnno() {
        System.out.println("=======PostDestroyConfig PreDestroy");
    }

    public void destroy() {
        System.out.println("=======PostDestroyConfig Destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("=======PostDestroyConfig afterPropertiesSet");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("=======PostDestroyConfig BeanFactoryAware");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("=======PostDestroyConfig ApplicationContextAware");

    }

    @Override
    public void setBeanName(String name) {
        System.out.println("=======PostDestroyConfig BeanNameAware");
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=======PostDestroyConfig CommandLineRunner");
    }
}
