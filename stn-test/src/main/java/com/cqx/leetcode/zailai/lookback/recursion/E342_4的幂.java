package com.cqx.leetcode.zailai.lookback.recursion;

public class E342_4的幂 {
    /**
     * @param n
     * @return
     */
    public boolean isPowerOfFour0(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0xaaaaaaaa) == 0;
    }


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
