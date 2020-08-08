package com.cqx.leetcode.zailai;

import java.util.Stack;

public class 今天20200728 {

    /**
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     *  
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-stack
     */
    public static class MinStack {
        private Stack<Integer> mainStack;
        private Stack<Integer> helpStack;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            mainStack = new Stack<>();
            helpStack = new Stack<>();
        }

        public void push(int x) {
            mainStack.push(x);
            if (helpStack.isEmpty()) {
                helpStack.push(x);
            } else if (x < helpStack.peek()) {
                helpStack.push(x);
            } else {
                helpStack.push(helpStack.peek());
            }
        }

        public void pop() {
            mainStack.pop();
            helpStack.pop();
        }

        public int top() {
            return mainStack.peek();
        }

        public int getMin() {
            return helpStack.peek();
        }
    }


    /**
     * 1. 暴力法，2次循环
     *
     * @param heights
     * @return
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        if (heights.length == 1) {
            return heights[0];
        }
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            int v1 = heights[i];
            if (v1 == 0) {
                continue;
            }
            int minHeight = v1;
            for (int j = i; j < heights.length; j++) {
                int v2 = heights[j];
                if (v2 < minHeight) {
                    minHeight = v2;
                }
                max = Math.max((j - i + 1) * minHeight, max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleArea(array));
    }
}
