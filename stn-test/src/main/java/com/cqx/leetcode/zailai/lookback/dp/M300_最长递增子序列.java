package com.cqx.leetcode.zailai.lookback.dp;

/**
 * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
 * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
 * <p>
 * 示例 1：
 * 输入：nums = [10,9,2,5,3,7,101,18]
 * 输出：4
 * 解释：最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,0,3,2,3]
 * 输出：4
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 */
public class M300_最长递增子序列 {

    /**
     * dp[i] 表示 长度为i的最长子序列，他的最大值
     * dp[1] = nums[0];
     * 从前往后遍历nums
     * nums[i] > dp[len] => dp[len+1] = nums[i]
     * nums[i] < dp[len] => 找到dp[]中比nums[i]小的数k， dp[k+1] = nums[i] 替换掉之前大的
     * nums[i] =dp[len] => dp[len] = nums[i] 不变
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS0(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }


    /**
     * 定义dp[i] 为考虑前 ii 个元素，以第 ii 个数字结尾的最长上升子序列的长度，注意nums[i] 必须被选取。
     * dp[i]=max(dp[j])+1,其中0≤j<i且num[j]<num[i]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     * todo 还是有问题
     * dp[i] 表示0-i闭区间，最大长度
     * help[i] 表示0-1闭区间，最大长度时候，max的值
     * <p>
     * 如果nums[i] > help[i-1] => dp[i] = dp[i-1] + 1 ,help[i] = nums[i]
     * 如果nums[i] < help[i-1] && help[i-1] == nums[i-1] => dp[i] = dp[i-1], help[i] = nums[i]
     * 如果nums[i] < help[i-1] && help[i-1] != nums[i-1] => dp[i] = dp[i-1], help[i] = nums[i-1]
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        int[] help = new int[nums.length];
        dp[0] = 1;
        help[0] = nums[0];
        int max = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            if (nums[i] > help[i - 1]) {
                dp[i] = dp[i - 1] + 1;
                help[i] = nums[i];
            } else {
                dp[i] = dp[i - 1];
                if (help[i - 1] == nums[i - 1]) {
                    help[i] = nums[i];
                } else {
                    help[i] = help[i - 1];
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

}
