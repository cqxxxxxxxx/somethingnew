package com.cqx.leetcode.zailai.lookback.greedyAlgorithm;

import java.util.Arrays;

public class E455_分发饼干 {
    /**
     * 贪心算法
     *
     * @param g 小孩
     * @param s 饼干
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, result = 0;
        while (i < g.length) {
            int kidVal = g[i++];
            while (j < s.length) {
                int cakeVal = s[j++];
                if (cakeVal >= kidVal) {
                    result++;
                    break;
                }
            }
        }
        return result;
    }
}
