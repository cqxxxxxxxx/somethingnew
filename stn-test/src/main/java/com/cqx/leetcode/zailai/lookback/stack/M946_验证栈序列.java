package com.cqx.leetcode.zailai.lookback.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/validate-stack-sequences/
 */
public class M946_验证栈序列 {

    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        LinkedList<Integer> stack = new LinkedList<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }


}
