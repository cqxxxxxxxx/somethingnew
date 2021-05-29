package com.cqx.leetcode.zailai.lookback.search;


import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * <p>
 * 示例 1：
 * 输入：nums = [1,5,11,5]
 * 输出：true
 * 解释：数组可以分割成 [1, 5, 5] 和 [11] 。
 * <p>
 * 示例 2：
 * 输入：nums = [1,2,3,5]
 * 输出：false
 * 解释：数组不能分割成两个元素和相等的子集。
 *  
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 * <p>
 * 链接：https://leetcode-cn.com/problems/partition-equal-subset-sum
 */
public class M416_分割等和子集 {

    /**
     * dp 01背包问题
     * dp[i][j] 前i个元素能否组成和为j
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int len = nums.length;
        // 题目已经说非空数组，可以不做非空判断
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特判：如果是奇数，就不符合要求
        if ((sum & 1) == 1) {
            return false;
        }
        int target = sum / 2;
        // 创建二维状态数组，行：物品索引，列：容量（包括 0）
        boolean[][] dp = new boolean[len][target + 1];

        // 先填表格第 0 行，第 1 个数只能让容积为它自己的背包恰好装满
        if (nums[0] <= target) {
            dp[0][nums[0]] = true;
        }
        // 再填表格后面几行
        for (int i = 1; i < len; i++) {
            for (int j = 0; j <= target; j++) {
                // 直接从上一行先把结果抄下来，然后再修正
                dp[i][j] = dp[i - 1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i]];
                }
            }
            // 由于状态转移方程的特殊性，提前结束，可以认为是剪枝操作
            if (dp[i][target]) {
                return true;
            }
        }
        return dp[len - 1][target];
    }


    // =============== TODO  还是超时==============
    public static boolean canPartition0(int[] nums) {
        int target = 0;
        for (int num : nums) {
            target += num;
        }
        if ((target & 1) == 1) {
            return false;
        }
        target = target / 2;
        //
        boolean[] visited = new boolean[nums.length];
        return recursion0(nums, visited, 0, target);
    }

    private static boolean recursion0(int[] nums, boolean[] visited, int curSum, int target) {
        if (curSum == target) {
            return true;
        }
        boolean[] thisRound = new boolean[100];
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i] && thisRound[nums[i]] == false) {
                curSum += nums[i];
                visited[i] = true;
                thisRound[nums[i]] = true;
                if (recursion0(nums, visited, curSum, target)) {
                    return true;
                } else {
                    curSum -= nums[i];
                    visited[i] = false;
                }
            }
        }
        return false;
    }


//================= 错误 题目理解错误 ，不需要purgesNums================

    /**
     * 回溯
     *
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int[] help = new int[100];
        for (int num : nums) {
            help[num] = help[num] + 1;
        }
        List<Integer> tmp = new ArrayList<>();
        for (int i = 0; i < help.length; i++) {
            int cnt;
            if (help[i] > 0 && (cnt = help[i] % 2) > 0) {
                tmp.add(i);
            }
        }
        Integer[] purgedNums = tmp.toArray(new Integer[]{});
        int target = 0;
        for (int num : purgedNums) {
            target += num;
        }
        if ((target & 1) == 1) {
            return false;
        }
        target = target / 2;
        //
        boolean[] visited = new boolean[nums.length];
        return recursion(purgedNums, visited, 0, target);
    }

    private static boolean recursion(Integer[] nums, boolean[] visited, int curSum, int target) {
        if (curSum == target) {
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                curSum += nums[i];
                visited[i] = true;
                if (recursion(nums, visited, curSum, target)) {
                    return true;
                } else {
                    curSum -= nums[i];
                    visited[i] = false;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        System.out.println((22 & 1));
        System.out.println(1 & 1);
        System.out.println(2 & 1);
//        System.out.println(
//                canPartition(new int[]{1, 2, 3, 5})
//        );
        System.out.println(canPartition(new int[]{1, 5, 11, 5}));
    }
}
