package com.cqx.leetcode.zailai.lookback.recursion;

public class E342_4的幂 {

    /**
     * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
     *
     * @param num
     * @return
     */
    public boolean isPowerOfFour(int num) {
        if (num == 0) {
            return false;
        }
        if (num == 1) {
            return true;
        }
        if (num % 4 != 0) {
            return false;
        }
        return isPowerOfFour(num / 4);
    }
}
