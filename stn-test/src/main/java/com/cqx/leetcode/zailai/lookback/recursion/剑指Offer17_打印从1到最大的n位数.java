package com.cqx.leetcode.zailai.lookback.recursion;

/**
 * https://leetcode-cn.com/problems/da-yin-cong-1dao-zui-da-de-nwei-shu-lcof/
 */
public class 剑指Offer17_打印从1到最大的n位数 {

    /**
     * n位数 10^n - 1
     * 2位最大值为 100 - 1 = 99
     * 3位最大值为 1000 - 1 = 999
     *
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        //用快速幂计算10的N次
        int max = (int) Math.pow(10, n);
        int[] r = new int[max];
        for (int i = 0; i < max; i++) {
            r[i] = i;
        }
        return r;
    }

    /**
     * @param n
     * @return
     */
    public int[] printNumbers1(int n) {

        return null;
    }
}
