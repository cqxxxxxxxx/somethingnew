package com.cqx.leetcode.zailai.lookback.dp;


/**
 * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
 */
public class 剑指Offer46_把数字翻译成字符串 {

    /**
     * dp数组：dp[i]
     * https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/solution/mian-shi-ti-46-ba-shu-zi-fan-yi-cheng-zi-fu-chua-6/
     * <p>
     * dp[i] = dp[i - 1] + dp[i - 2]   i，i-1可以组合
     * dp[i] = dp[i − 1]               i, i-1不能组合
     *
     * @param num
     * @return
     */
    public int translateNum(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int[] dp = new int[chars.length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < chars.length + 1; i++) {
            int v = (chars[i - 2] - '0') * 10 + (chars[i - 1] - '0');
            if (v >= 10 && v <= 25) {
                dp[i] = dp[i-2] + dp[i-1];
            } else {
                dp[i] = dp[i-1];
            }
        }
        return dp[chars.length];
    }

    public static void main(String[] args) {
        剑指Offer46_把数字翻译成字符串 obj = new 剑指Offer46_把数字翻译成字符串();
        obj.translateNum(25);
    }
}
