package com.cqx.leetcode.zailai.lookback.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。
 * <p>
 * 找到所有在 [1, n] 范围之间没有出现在数组中的数字。
 * <p>
 * 您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [4,3,2,7,8,2,3,1]
 * <p>
 * 输出:
 * [5,6]
 * <p>
 * https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
 */
public class E448_找到所有数组中消失的数字 {
    /**
     * num里面数字都小于 n,那么每遍历一个数字x，就让对应的 index=x-1的位置+n
     * 然后再遍历一次，如果对应位置上值<=n，则说明没有遍历到，加到结果中
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers1(int[] nums) {
        List<Integer> r = new ArrayList<>();
        int n = nums.length;
        for (int val : nums) {
            //要取余运算，因为后面的值可能已经+过n了
            int x = (val - 1) % n;
            nums[x] = nums[x] + n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                r.add(i + 1);
            }
        }
        return r;
    }

//    public List<Integer> findDisappearedNumbersError(int[] nums) {
//        Arrays.sort(nums);
//        List<Integer> r = new ArrayList<>();
//        for (int i = 0; i < nums.length; i++) {
//            if (nums[i] != i + 1) {
//                r.add(i + 1);
//            }
//        }
//        return r;
//    }

    /**
     * 空间复杂度 O（N）
     * 时间 O（N）
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers0(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        List<Integer> r = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)) {
                r.add(i);
            }
        }
        return r;
    }


}
