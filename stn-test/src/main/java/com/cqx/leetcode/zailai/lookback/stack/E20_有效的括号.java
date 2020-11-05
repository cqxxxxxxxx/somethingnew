package com.cqx.leetcode.zailai.lookback.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class E20_有效的括号 {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        final char[] chars = s.toCharArray();
        Deque<Character> stack = new LinkedList<Character>();
        Map<Character, Character> matcher = new HashMap<>();
        matcher.put('(', ')');
        matcher.put('{', '}');
        matcher.put('[', ']');
        for (char each : chars) {
            if (each == ')' || each == '}' || each == ']') {
                if (stack.isEmpty()) {
                    return false;
                }
                Character top = stack.pop();
                if (matcher.get(top) != each) {
                    return false;
                }
            } else {
                stack.push(each);
            }
        }
        return stack.isEmpty();
    }
}
