package com.cqx.leetcode.zailai.lookback.array;

/**
 * https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof/
 */
public class 剑指Offer53_缺失的数字 {

    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return -1;
    }


    public int missingNumber1(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == mid) {
                //如果nums[mid] == mid也就是说当前元素的
                //下标等于他自己，比如数组[0,1,2,3,4,5]每
                //个元素的下标都等于他自己，说明[start,mid]
                //没有缺少任何数字，那么缺少的肯定是在[mid+1,end]
                start = mid + 1;
            } else {
                //如果当前元素的下标不等于他自己，比如[0,1,2,4]中
                //nums[3]==4，说明说明缺少的数字就在这个区间内
                end = mid;
            }
        }
        //如果类似于[0,1,2,3]这种start指向了数组的最后一个，我们让他加1
        return start == nums[start] ? start + 1 : start;
    }
}
