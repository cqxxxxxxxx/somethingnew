package com.cqx.dynamic_proxy;

/**
 * Created by admin on 2017/3/16.
 */
public class Dog implements IAnimal {

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void bark() {
        System.out.println("fuck off");
    }
}
