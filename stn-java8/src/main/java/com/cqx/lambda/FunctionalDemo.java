package com.cqx.lambda;

/**
 * 这是一个函数式接口，通过@FunctionalInterface来显示的定义
 * Created by Shan on 2017/2/2.
 */
@FunctionalInterface
public interface FunctionalDemo<T> {

    T getSmaller(T a, T b);

    /**
     * java8中新加的 default 关键字
     * 用于扩充原有的旧的接口，添加默认方法，
     * 如果不这么做，就得吧所有的接口实现类都修改过去，这是一种折中的方法吧。
     * @param a
     * @param b
     * @return
     */
    default T getBigger(T a, T b){
        return a;
    }
}
