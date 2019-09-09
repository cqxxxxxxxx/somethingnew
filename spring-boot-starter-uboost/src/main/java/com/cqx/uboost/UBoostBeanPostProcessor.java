package com.cqx.uboost;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class UBoostBeanPostProcessor implements BeanPostProcessor, Ordered, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private BeanDefinitionRegistry beanDefinitionRegistry;

    private UBoostContext uBoostContext;

    @Nullable
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }


    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        //根据bean是否有UBoost注解来进行增强
        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(beanName);

        return bean;
    }

    /**
     * 优先级最低的beanPostProcessor
     * 意味着会在比如SpringAOP代理对象生成之后再进行拦截处理
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.uBoostContext = applicationContext.getBean(UBoostContext.class);
        this.beanDefinitionRegistry = getBeanDefinitionRegistry(applicationContext);
    }

    private BeanDefinitionRegistry getBeanDefinitionRegistry(ApplicationContext context) {
        if (context instanceof BeanDefinitionRegistry) {
            return (BeanDefinitionRegistry) context;
        }
        if (context instanceof AbstractApplicationContext) {
            return (BeanDefinitionRegistry) ((AbstractApplicationContext) context)
                    .getBeanFactory();
        }
        throw new IllegalStateException("Could not locate BeanDefinitionRegistry");
    }
}
