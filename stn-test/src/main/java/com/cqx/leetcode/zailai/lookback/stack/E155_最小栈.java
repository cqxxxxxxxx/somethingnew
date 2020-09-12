package com.cqx.leetcode.zailai.lookback.stack;

import java.util.Stack;

public class E155_最小栈 {

    /**
     * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
     * <p>
     * push(x) —— 将元素 x 推入栈中。
     * pop() —— 删除栈顶的元素。
     * top() —— 获取栈顶元素。
     * getMin() —— 检索栈中的最小元素。
     *  
     */
    class MinStack {

        // 数据栈
        private Stack<Integer> data;
        // 辅助栈
        private Stack<Integer> helper;

        /**
         * initialize your data structure here.
         */
        public MinStack() {
            data = new Stack<>();
            helper = new Stack<>();
        }

        public void push(int x) {
            data.push(x);
            if (helper.isEmpty() || helper.peek() > x) {
                helper.push(x);
            } else {
                helper.push(helper.peek());
            }
        }

        public void pop() {
            data.pop();
            helper.pop();
        }

        public int top() {
            return data.peek();
        }

        public int getMin() {
            return helper.peek();
        }
    }
}
