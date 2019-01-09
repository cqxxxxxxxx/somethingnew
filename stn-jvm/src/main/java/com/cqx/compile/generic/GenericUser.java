package com.cqx.compile.generic;

import org.junit.Test;

import java.lang.reflect.Type;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/8
 */
public class GenericUser {

    public <T> T getObj(Class<T> t) throws IllegalAccessException, InstantiationException {
        Type[] types = t.getGenericInterfaces();
        Type type = t.getGenericSuperclass();
        return t.newInstance();
    }

    public <E> E getE(E e) {
        Class c = e.getClass();
        Type[] types = c.getGenericInterfaces();
        Type type = c.getGenericSuperclass();
        return e;
    }

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        CQX cqx = getObj(CQX.class);
        cqx.setName("111");
        getE(new CQX());
    }

    public static class CQX {
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }
}
