package com.cqx.factory;


/**
 * Created by Shan on 2017/1/20.
 */
public abstract class AbstractBeanFactory implements BeanFactory{


    /**
     * 默认添加 类名首字母小写作为name
     * @param obj
     * @throws Exception
     */
    public abstract void addBean(Object obj) throws Exception;

    /**
     * 指定name 添加
     * @param name
     * @param obj
     * @throws Exception
     */
    public abstract void addBean(String name, Object obj) throws Exception;

    /**
     * 判断是否已经存在
     * @param name
     * @return
     */
    public boolean containsBean(String name){
        Object bean = getBean(name);
        return !isNull(bean);
    }

    public boolean isNull(Object obj){
        return obj == null;
    }

    /**
     * 根据类名 吧第一个字母小写 生成默认beanname
     * @param obj
     * @return
     */
    public String defaultName(Object obj){
        String str = obj.getClass().getSimpleName();
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }
}
