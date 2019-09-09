package com.cqx.uboost;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/9
 */
public class UBoostFactoryBean implements FactoryBean<UBoostContext>, InitializingBean, ApplicationContextAware {

    private Class<?> type;

    private ApplicationContext applicationContext;

    /**
     * 获取UBoostContext的上下文
     *
     * @return
     * @throws Exception
     */
    @Override
    public UBoostContext getObject() throws Exception {
        UBoostContext context = new UBoostContext();
        /**
         * 从容器中获取所有的UBoostHandler注入到context中
         */
        Map<String, UBoostHandler> handlerMap = applicationContext.getBeansOfType(UBoostHandler.class);
        handlerMap.entrySet().stream()
                .forEach(x -> {
                    context.add(x.getKey(), x.getValue());
                });
        return context;
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
        return this.type;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        Assert.hasText(String.valueOf(this.type), "Type must be set");
//        do nothing

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
