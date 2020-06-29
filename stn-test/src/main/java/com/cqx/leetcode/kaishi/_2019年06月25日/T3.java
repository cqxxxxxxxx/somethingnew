package com.cqx.leetcode.kaishi._2019年06月25日;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 *
 * @desc:
 * @version: 1.0.0
 * @author: cqx
 * @Date: 2019/6/25
 */
public class T3 {

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 1;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            int maxTmp = longest(s.substring(i, length));
            max = max > maxTmp ? max : maxTmp;
            if (length - i < max) {
                return max;
            }
        }
        return max;
    }

    public int longest(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int length = s.length();
        int i = 0;
        Set charSet = new HashSet();
        for (; i < length; i++) {
            if (!charSet.add(s.charAt(i))) {
                break;
            }
        }
        return charSet.size();
    }

    public static void main(String[] args) {
        T3 t3 = new T3();
        System.out.println(t3.lengthOfLongestSubstring("au"));
        System.out.println(t3.lengthOfLongestSubstring(" "));
    }
}
