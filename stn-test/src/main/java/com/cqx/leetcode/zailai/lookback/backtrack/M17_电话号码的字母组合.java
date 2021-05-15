package com.cqx.leetcode.zailai.lookback.backtrack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class M17_电话号码的字母组合 {

    /**
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * @param digits
     * @return
     */
    List<String> r = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        final char[] chars = digits.toCharArray();
        int start = 0;
        recursion(start, chars, "");
        return r;
    }

    private void recursion(int start, char[] chars, String tmp) {
        //termination
        if (start == chars.length) {
            r.add(tmp);
            return;
        }
        //process
        final String s = phone.get(chars[start]);
        for (int i = 0; i < s.length(); i++) {
            recursion(start + 1, chars, tmp + s.charAt(i));
        }
    }


    Map<Character, String> phone = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
}
