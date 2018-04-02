package com.cqx.leetcode;

import org.junit.Test;

/**
 * Created by cqx on 2018/3/24.
 */
public class DAY1 {

    public int[] twoSum(int[] nums, int target) {
        int a = 0;
        int b = 0;
        outer:
        for(int i = 0; i < nums.length; i++) {
            int tmp = nums[i];
            a = i;
            for (int j = i + 1; j < nums.length; j++) {
                int tmp2 = nums[j];
                if (tmp + tmp2 == target) {
                    b = j;
                    break outer;
                }
            }
        }
        return new int[]{a, b};
    }

    @Test
    public void twoSumTest() {
        int[] result = twoSum(new int[]{-1,-2,-3,-4,-5}, -8);

    }
}
