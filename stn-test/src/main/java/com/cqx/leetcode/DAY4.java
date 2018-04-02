package com.cqx.leetcode;

import org.junit.Test;

/**
 * Created by cqx on 2018/3/27.
 */
public class DAY4 {

    /**
     * Given an integer array, find three numbers whose product is maximum and output the maximum product.
     * Example 1:
     * Input: [1,2,3]
     * Output: 6
     * <p>
     * Example 2:
     * Input: [1,2,3,4]
     * Output: 24
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
//        考虑了负数
//        Arrays.sort(nums);
//        //One of the Three Numbers is the maximum value in the array.
//
//        int a = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
//        int b = nums[0] * nums[1] * nums[nums.length - 1];
//        return a > b ? a : b;


//        Time complexity : O(n)O(n). Only one iteration over the numsnums array of length nn is required.
//        Space complexity : O(1)O(1). Constant extra space is used.
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies betwen max2 and max3
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);


//        没有考虑负数 找出最大的3个 以及最小的两个(可能为负数，负负得正) 比较min1*min2*max3 max1*max2*max3的大小 取最大的
//        int[] ints = new int[3];
//        int minIndex = 0;
//        for (int i = 0; i < nums.length; i++) {
//            if (i < 2) {
//                ints[i] = nums[i];
//                continue;
//            }
//            if (i == 2) {
//                ints[i] = nums[i];
//                minIndex = findIndexOfMin(ints);
//                continue;
//            }
//            int tmp = nums[i];
//            if (ints[minIndex] < tmp) {
//                ints[minIndex] = tmp;
//            }
//            minIndex = findIndexOfMin(ints);
//        }
//        return ints[0] * ints[1] * ints[2];
    }

    private int findIndexOfMin(int[] ints) {
        int tmp;
        if (ints[0] < ints[1]) {
            tmp = 0;
        } else {
            tmp = 1;
        }
        if (ints[tmp] < ints[2]) {
            return tmp;
        } else {
            return 2;
        }
    }


    @Test
    public void test1() {
        int[] aa = {9,1,5,6,7,2};
        int c = maximumProduct(aa);
        System.out.println(c);
    }
}
