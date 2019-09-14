package com.cqx.skeleton.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 封装方法的调用
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/9/10
 */
public class FoxMethod {

    private static final Logger log = LoggerFactory.getLogger(FoxMethod.class);

    private Method method;
    private Class<?> foxInterface;
    private String name;
    private Class<?> returnType;

    public <T> FoxMethod(Class<T> foxInterface, Method method, String name) {
        this.method = method;
        this.foxInterface = foxInterface;
        this.name = name;
        this.returnType = method.getReturnType();
    }

    /**
     * 自定义的增强处理
     * 这边只是简单打印一点日志
     *
     * @param originalObject
     * @param args
     * @return
     */
    public Object execute(Object originalObject, Object[] args) {
        log.info("当前执行方法:" + method.getName());
        log.info("所属对象:" + originalObject);
        log.info("当前执行参数:" + Arrays.stream(args)
                .map(x -> x.toString())
                .collect(Collectors.joining("###")).toString());
        log.info("随意增强 => 输出fox配置的name:" + name);
        try {
            return returnType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
