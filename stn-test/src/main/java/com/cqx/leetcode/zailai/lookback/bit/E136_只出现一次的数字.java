package com.cqx.leetcode.zailai.lookback.bit;

/**
 * https://leetcode-cn.com/problems/single-number/
 */
public class E136_只出现一次的数字 {
    /**
     * 异或
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int r = 0;
        for (int num : nums) {
            r ^= num;
        }
        return r;
    }
}
