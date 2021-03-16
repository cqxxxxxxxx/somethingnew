package com.cqx.leetcode.zailai.lookback.huiwen;

/**
 * https://leetcode-cn.com/problems/remove-palindromic-subsequences/
 */
public class E1332_删除回文子序列 {

    public int removePalindromeSub(String s) {
        if (s == null || s.equals("")) {
            return 0;
        }
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(s.length() - 1 - i)) {
                continue;
            } else {
                return 2;
            }
        }
        return 1;
    }
}
