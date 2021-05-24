package com.cqx.leetcode.zailai.lookback.dp;

/**
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * <p>
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * <p>
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 */
public class M647_回文子串 {


    /**
     * dp[i][j] 标识 i,j闭区间是否为回文子串
     * dp[i][j] = chars[i] == chars[j] && (j-i<2 || dp[i+1][j-1])
     * 当只有一个字符时，比如 a 自然是一个回文串。
     * 当有两个字符时，如果是相等的，比如 aa，也是一个回文串。
     * 当有三个及以上字符时，比如 ababa 这个字符记作串 1，把两边的 a 去掉，也就是 bab 记作串 2，可以看出只要串2是一个回文串，那么左右各多了一个 a 的串 1 必定也是回文串。所以当 s[i]==s[j] 时，自然要看 dp[i+1][j-1] 是不是一个回文串。
     *
     * @param s
     * @return
     */
    public int countSubstrings1(String s) {
        // 动态规划法
        boolean[][] dp = new boolean[s.length()][s.length()];
        int ans = 0;

        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ans++;
                }
            }
        }

        return ans;
    }


//==================== 辣鸡DP ======================

    /**
     * TODO 辣鸡
     * TODO dp[i]这种不好， 跟暴力无异， 暴力也可以当做 计算出每个以i完结的回文串数量，然后汇总一下。跟这个DP复杂度一样。
     * dp[i] i位置最大回文子串数量
     * dp[i] = dp[i-1] + calculate(i)
     * calculate(i) 为计算以i位置结尾的回文子串数量
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int[] dp = new int[s.length() + 1];
        dp[0] = 0;
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = dp[i - 1] + calculateEndWith(s, i);
        }
        return dp[s.length()];
    }

    /**
     * 字符串s中，以下标i为结尾的回文数量
     *
     * @param s
     * @param endIndex
     * @return
     */
    private static int calculateEndWith(String s, int endIndex) {
        int r = 0;
        for (int i = 0; i <= endIndex; i++) {
            int start = i;
            int end = endIndex;
            boolean match = true;
            while (start <= end) {
                if (s.charAt(start) != s.charAt(end)) {
                    match = false;
                    break;
                }
                start++;
                end--;
            }
            if (match) {
                r++;
            }
        }
        return r;
    }

    public static void main(String[] args) {
        String s = "a";
        System.out.println(s.charAt(0));
        System.out.println(calculateEndWith(s, 0));
    }
}
