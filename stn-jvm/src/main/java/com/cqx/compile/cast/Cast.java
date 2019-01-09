package com.cqx.compile.cast;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/9
 */
public class Cast {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void test1() {
        Object o = new Object();
        Cast fake = (Cast) o;
        fake.getName();
    }

    public void test2() {
        Cast cast = new Cast();
        Object o = cast;
        Cast fake = (Cast) o;
        fake.getName();
    }
}
