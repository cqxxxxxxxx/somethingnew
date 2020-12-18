package com.cqx.jvm;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/3/20
 */
public class UnsafeTest {


    public static Unsafe getUnsafeInstance() throws Exception {
        // 通过反射获取rt.jar下的Unsafe类
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        // return (Unsafe) theUnsafeInstance.get(null);是等价的
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }

    public static void main(String[] args) throws Exception {
//        Unsafe unsafe = Unsafe.getUnsafe();

        Unsafe unsafe = getUnsafeInstance();
        User user = new User();
        user.setName("cqx");
        user.setAge(12);
        long offsetValue = unsafe.objectFieldOffset(User.class.getDeclaredField("name"));
        System.out.println(offsetValue);


    }


    public static class User {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
