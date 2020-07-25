package com.cqx.leetcode.zailai;

public class 今天20200723 {

    /**
     * https://leetcode-cn.com/problems/valid-palindrome/
     *
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        char[] chars = s.toLowerCase().toCharArray();
        int i = 0, j = chars.length - 1;
        while (i <= j) {
            if (!Character.isLetterOrDigit(chars[i])) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(chars[j])) {
                j--;
                continue;
            }
            if (chars[i] == chars[j]) {
                i++;
                j--;
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char a = 'a';
        System.out.println(a);
        System.out.println((int) a);
        System.out.println((int) 'b');

    }
}
