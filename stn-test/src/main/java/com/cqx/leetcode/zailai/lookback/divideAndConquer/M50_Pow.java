package com.cqx.leetcode.zailai.lookback.divideAndConquer;

public class M50_Pow {

    /**
     * 计算X的N次幂
     *
     * @param x
     * @param N
     * @return
     */
    public double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    public double myPow(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }

}
