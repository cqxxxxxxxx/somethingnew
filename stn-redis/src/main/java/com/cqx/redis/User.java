package com.cqx.redis;

import java.io.Serializable;

/**
 * Created by Shan on 2017/1/13.
 */
public class User implements Serializable{

    private static final long serialVersionUID = -1L;

    private String name;
    private String sex;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
