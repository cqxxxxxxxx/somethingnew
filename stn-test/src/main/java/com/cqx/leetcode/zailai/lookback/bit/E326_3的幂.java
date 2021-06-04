package com.cqx.leetcode.zailai.lookback.bit;

/**
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 * <p>
 * <p>
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 * <p>
 * 链接：https://leetcode-cn.com/problems/power-of-three
 */
public class E326_3的幂 {

    public boolean isPowerOfThree0(int n) {
        if (n < 1) {
            return false;
        }

        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }


    public boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        int n = 9;
        StringBuilder sb = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            sb.append(((1 << i) & n) > 0 ? 1 : 0);
        }
        System.out.println(sb.toString());
    }


}
