package com.cqx.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Shan on 2017/1/19.
 */
public class BeanFactoryImpl extends AbstractBeanFactory {
    private final static Map<String, Object> beans = new ConcurrentHashMap<>();

    /**
     * 获取
     * @param name
     * @return
     */
    public Object getBean(String name) {
        return beans.get(name);
    }


    /**
     * 默认添加 类名首字母小写作为name
     * @param obj
     * @throws Exception
     */
    @Override
    public void addBean(Object obj) throws Exception {
        String name = defaultName(obj);
        addBean(name, obj);
    }

    /**
     * 指定name 添加
     * @param name
     * @param obj
     * @throws Exception
     */
    @Override
    public void addBean(String name, Object obj) throws Exception {
        if (containsBean(name))
            throw new Exception(name + "Bean has already exist");
        beans.put(name, obj);
    }


}
