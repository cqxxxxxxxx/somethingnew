package com.cqx.leetcode.zailai.lookback.set;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 进阶：你可以设计并实现时间复杂度为 O(n) 的解决方案吗？
 *  
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 * <p>
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 * <p>
 * 提示：
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-consecutive-sequence
 */
public class H128_最长连续序列 {

    /**
     * 参考官方题解
     * 已知 x+1,x+2,x+3...x+y的连续序列，那么如果后面遍历的数字在这其中，那么就没有需要遍历的需求了
     * 即x只有当数组中不存在 x-1，即x的前驱数值时候，才需要对x进行遍历
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int r = 0;
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int max = 1;
                while (set.contains(++num)) {
                    max++;
                }
                r = Math.max(r, max);
            }
        }
        return r;
    }
}
