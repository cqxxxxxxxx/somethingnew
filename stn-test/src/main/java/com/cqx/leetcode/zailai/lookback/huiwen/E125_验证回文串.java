package com.cqx.leetcode.zailai.lookback.huiwen;

/**
 * https://leetcode-cn.com/problems/valid-palindrome/
 */
public class E125_验证回文串 {

    public boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            char c0 = chars[start];
            if ((c0 >= 'a' && c0 <= 'z') || (c0 >= '0' && c0 <= '9')) {
                //ok
            } else {
                start++;
                continue;
            }

            char c1 = chars[end];
            if ((c1 >= 'a' && c1 <= 'z') || (c1 >= '0' && c1 <= '9')) {
                //ok
            } else {
                end--;
                continue;
            }
            if (c0 != c1) {
                return false;
            } else {
                start++;
                end--;
            }
        }
        return true;
    }
}
