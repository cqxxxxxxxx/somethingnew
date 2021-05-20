package com.cqx.leetcode.zailai.lookback.dp;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/longest-valid-parentheses/
 */
public class H32_最长有效括号 {

    /**
     * dp
     * dp[n] n位置时候 最长有效括号长度
     * dp[n] = dp[n-1] + (val[n-1] == '(' && val[n] == ')' ? 1 : 0)
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        return 1;
    }

    /**
     * 栈
     * @param s
     * @return
     */
    public int longestValidParentheses1(String s) {
        LinkedList<Integer> stack = new LinkedList<>();
        int res = 0;

        for(int i = 0; i < s.length(); i++){
            if(stack.isEmpty() || s.charAt(i) == '(' || s.charAt(stack.getLast()) == ')'){
                stack.addLast(i);
            }else{
                stack.removeLast();
                res = Math.max(res, stack.isEmpty() ? i + 1 : i - stack.getLast());
            }
        }
        return res;
    }
}
