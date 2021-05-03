package com.cqx.leetcode.zailai.lookback.search;

/**
 * https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof/
 */
public class 剑指Offer53_在排序数组中查找数字I {

    /**
     * 二分查找
     *
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        boolean found = false;
        int mid = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (target == nums[mid]) {
                found = true;
                break;
            }
            if (target < nums[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if (!found) {
            return 0;
        }
        int r = 1;
        int mid0 = mid;
        int mid1 = mid;
        while (--mid0 >= 0 && nums[mid0] == target) {
            r++;
        }
        while (++mid1 < nums.length && nums[mid1] == target) {
            r++;
        }
        return r;
    }
}
