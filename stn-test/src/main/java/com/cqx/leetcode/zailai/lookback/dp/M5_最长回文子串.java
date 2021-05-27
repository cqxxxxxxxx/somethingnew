package com.cqx.leetcode.zailai.lookback.dp;

/**
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * <p>
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * <p>
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 * <p>
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 */
public class M5_最长回文子串 {


    /**
     * dp来做 {@link M647_回文子串} 比这题难
     * dp[i][j] 定义为 s[i..j]闭区间 是否为回文串, true,false
     * <p>
     * 边界条件， (j-1)-(i+1)+1<2 => j-i < 3  这个时候不用判断，即 i,j中间只有一个元素
     * dp[i][j] = s[i] == s[j] &&  (dp[i+1][j-1] || j-i < 3)
     * <p>
     * 画图，二维矩阵填充，只要填右上半部分，然后需要先填充列。
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        int l = 0, r = 0;
        for (int j = 1; j < s.length(); j++) {
            for (int i = 0; i < j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (dp[i + 1][j - 1] || j - i < 3);
                if (dp[i][j]) {
                    if (j - i > r - l) {
                        l = i;
                        r = j;
                    }
                }
            }
        }
        return s.substring(l, r + 1);
    }

    public static void main(String[] args) {
        String babad = longestPalindrome("aaaa");
        System.out.println(babad);
    }
}
