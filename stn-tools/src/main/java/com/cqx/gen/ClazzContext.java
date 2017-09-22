package com.cqx.gen;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by BG307435 on 2017/9/21.
 */
public class ClazzContext {

    public ClazzContext(Class clazz) {
        this.clazz = clazz;
        parse();
    }

    private void parse() {
        int sort;
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        String setMethodName, getMethodName;
        List<Method> setMethods = new ArrayList<>(fields.length);
        List<Method> getMethods = new ArrayList<>(fields.length);
        for (Field field : fields) {
            //根据排序 排列set方法
            ColumnSort columnSort = field.getAnnotation(ColumnSort.class);
            if (columnSort != null) {
                sort = columnSort.value();
                setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
                for (Method method : methods) {
                    if (method.getName().equals(setMethodName)) {
                        setMethods.add(sort - 1, method);
                    }
                }
            }
            getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            for (Method method : methods) {
                if (method.getName().equals(getMethodName)) {
                    getMethods.add(method);
                }
            }
        }

        this.getMethods = getMethods;
        this.setMethods = setMethods;
        this.fields = Arrays.asList(fields);
    }

    private Class clazz;

    private List<Method> getMethods;

    private List<Method> setMethods;

    private List<Field> fields;

    public List<Method> getGetMethods() {
        return getMethods;
    }

    public void setGetMethods(List<Method> getMethods) {
        this.getMethods = getMethods;
    }

    public List<Method> getSetMethods() {
        return setMethods;
    }

    public void setSetMethods(List<Method> setMethods) {
        this.setMethods = setMethods;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Class getClazz() {
        return clazz;
    }
}
