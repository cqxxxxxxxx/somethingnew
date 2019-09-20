package com.cqx.config.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/3
 */
@Component
public class DemoBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("=======BeanFactoryPostProcessor");
    }
}
