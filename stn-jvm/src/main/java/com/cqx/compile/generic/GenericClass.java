package com.cqx.compile.generic;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/1/8
 */
public class GenericClass<T> {
    T t;


    /**
     * checkcast：用于检查类型强制转换是否可以进行。如果可以进行，checkcast指令不会改变操作数栈，否则它会抛出ClassCastException异常；
     *
     * @param args
     */
    public static void main(String[] args) {

        GenericClass<Specific> genericClass = new GenericClass<>();
        Specific specific = genericClass.t;
        specific.sayName();
        System.out.println(genericClass.t);
        System.out.println(genericClass.t.getClass().getGenericSuperclass());

        GenericClass<SpecificTwo> twoGenericClass = new GenericClass<>();
        SpecificTwo specificTwo = twoGenericClass.t;
        specificTwo.sayNameTwo();
        System.out.println(twoGenericClass.t);
        System.out.println(twoGenericClass.t.getClass().getGenericSuperclass());
    }

    public static class Specific {
        public String sayName() {
            return "SPECIFIC";
        }
    }

    public static class SpecificTwo {
        public String sayNameTwo() {
            return "SPECIFICTWO";
        }
    }
}
