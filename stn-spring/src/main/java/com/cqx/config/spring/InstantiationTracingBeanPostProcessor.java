package com.cqx.config.spring;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * bean实例化的追踪
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/3
 */
@Component
public class InstantiationTracingBeanPostProcessor implements BeanPostProcessor {

    // simply return the instantiated bean as-is
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
//        System.out.println("Bean '" + beanName + "' beforeInitialization : " + bean.toString());
        return bean; // we could potentially return any object reference here...
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("Bean '" + beanName + "' created : " + bean.toString());
        return bean;
    }
}
