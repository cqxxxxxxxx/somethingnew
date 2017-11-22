package com.cqx.gen;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by BG307435 on 2017/9/21.
 */
public class ClazzContext {

    private Class clazz;

    private Map<Integer, Method> setMethodsMap;

    private List<Method> getMethodsSQL;

    private List<Method> getMethods;

    private List<Method> setMethods;

    private List<Field> fields;

    public ClazzContext(Class clazz) {
        this.clazz = clazz;
        parse();
    }

    private void parse() {
        Field[] fields = clazz.getDeclaredFields();
        Method[] methods = clazz.getMethods();
        String setMethodName, getMethodName;
        List<Method> setMethods = new ArrayList<>(fields.length);
        List<Method> getMethods = new ArrayList<>(fields.length);
        Map<Integer, Method> setMethodsMap = new HashMap<>(fields.length);
        Method[] getMethodsSQL = new Method[100];

        for (Field field : fields) {
            //根据排序 排列set方法
            Mapping mapping = field.getAnnotation(Mapping.class);
            Integer excelIndex = null;
            int sqlIndex = 0;
            if (mapping != null) {
                excelIndex = mapping.index();
                sqlIndex = mapping.SQLIndex();
            }
            setMethodName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            for (Method method : methods) {
                if (method.getName().equals(setMethodName)) {
                    setMethods.add(method);
                    if (excelIndex != null) {
                        setMethodsMap.put(excelIndex, method);
                    }
                }
            }
            getMethodName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            for (Method method : methods) {
                if (method.getName().equals(getMethodName)) {
                    getMethods.add(method);
                    if (sqlIndex != 0) {
                        getMethodsSQL[sqlIndex] = method;
                    }
                }
            }
        }
        this.getMethodsSQL = Arrays.stream(getMethodsSQL)
                .filter(x -> x != null)
                .collect(Collectors.toList());
        this.setMethodsMap = setMethodsMap;
        this.getMethods = getMethods;
        this.setMethods = setMethods;
        this.fields = Arrays.asList(fields);
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Map<Integer, Method> getSetMethodsMap() {
        return setMethodsMap;
    }

    public void setSetMethodsMap(Map<Integer, Method> setMethodsMap) {
        this.setMethodsMap = setMethodsMap;
    }

    public List<Method> getGetMethodsSQL() {
        return getMethodsSQL;
    }

    public void setGetMethodsSQL(List<Method> getMethodsSQL) {
        this.getMethodsSQL = getMethodsSQL;
    }

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
}
