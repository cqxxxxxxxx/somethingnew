package com.cqx.skeleton;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 实现FactoryBean接口，spring实例化时会调用getObject方法返回代理对象
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class FoxFactoryBean<T> implements FactoryBean, ApplicationContextAware {

    /**
     * beanDefinition中定义了的属性 会自动注入进来
     */
    private Class<T> foxInterface;

    private ApplicationContext applicationContext;

    /**
     * beanDefinition中定义了的属性 会自动注入进来
     */
    private String name;


    public FoxFactoryBean() {
        //intentionally empty
    }

    /**
     * 获取UBoostContext的上下文
     *
     * @return
     * @throws Exception
     */
    @Override
    public T getObject() throws Exception {
        //get proxy
        T t = FoxRegistry.singleton().getFox(foxInterface, name);
        return t;
    }

    /**
     * 单例对象
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }


    @Override
    public Class<?> getObjectType() {
        return this.foxInterface;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
