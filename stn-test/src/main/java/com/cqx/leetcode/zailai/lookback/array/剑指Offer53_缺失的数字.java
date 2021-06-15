package com.cqx.leetcode.zailai.lookback.array;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * <p>
 * <p>
 * 示例 1:
 * 输入: [0,1,3]
 * 输出: 2
 * <p>
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 */
public class 剑指Offer53_缺失的数字 {

    /**
     * 遍历
     *
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 二分查找
     *
     * @param nums
     * @return
     */
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
