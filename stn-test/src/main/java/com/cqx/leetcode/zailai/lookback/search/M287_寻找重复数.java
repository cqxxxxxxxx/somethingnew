package com.cqx.leetcode.zailai.lookback.search;

import java.util.HashSet;

/**
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * <p>
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-the-duplicate-number
 */
public class M287_寻找重复数 {

    /**
     * @param nums
     * @return
     */
    public int findDuplicate0(int[] nums) {

        return -1;
    }


    /**
     * 另外一个数组，当做map用
     *
     * @param nums
     * @return
     */
    public int findDuplicate1(int[] nums) {
        int[] map = new int[nums.length];
        for (int num : nums) {
            if (map[num] > 0) {
                return num;
            }
            map[num] = num;
        }
        return -1;
    }

    /**
     * 最蠢的 set
     * 时间复杂度 O（N）
     * 空间复杂度 O（N）
     *
     * @param nums
     * @return
     */
    public int findDuplicate2(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            boolean add = set.add(num);
            if (!add) {
                return num;
            }
        }
        return -1;
    }
}
