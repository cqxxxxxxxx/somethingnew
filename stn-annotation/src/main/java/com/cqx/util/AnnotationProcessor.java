package com.cqx.util;

import com.cqx.annotation.Autowired;
import com.cqx.annotation.Bean;
import com.cqx.factory.BeanFactoryImpl;

import java.lang.reflect.Field;

/**
 * Created by Shan on 2017/1/20.
 */
public class AnnotationProcessor {
    private BeanFactoryImpl beanFactory = new BeanFactoryImpl();

    /**
     * 处理 @bean 注解
     * @param clazz
     */
    public void beanProcess(Class clazz){
        Bean annotation = (Bean) clazz.getAnnotation(Bean.class);
        if (annotation == null){
            return;
        }
        String name = annotation.name();
        try {
            if (name == null || name.equals(""))
                beanFactory.addBean(clazz.newInstance());
            else
                beanFactory.addBean(name, clazz.newInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理 @bean 注解
     * @param clazz
     */
    public void autowiredProcess(Class clazz) throws Exception {
        Field[] fields = clazz.getDeclaredFields();
        String name = defaultName(clazz.getSimpleName());
        Object obj = beanFactory.getBean(name);
        if (obj == null)
            throw new Exception(name + "bean not found");
        for (Field field: fields){
            Autowired autowired = field.getAnnotation(Autowired.class);
            if (autowired == null)
                continue;
            String fieldName = defaultName(field.getName());
            Object objTarget = beanFactory.getBean(fieldName);
            if (autowired.required() && objTarget == null)
                throw new Exception(name + "not found and it is required");
            field.setAccessible(true);
            field.set(obj, objTarget);
        }
    }


    public String defaultName(String str){
        return str.substring(0,1).toLowerCase() + str.substring(1);
    }
}
