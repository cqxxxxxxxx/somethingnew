package com.cqx.leetcode.zailai.lookback.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 */
public class M739_每日温度 {

    /**
     * 单调栈
     * 维护一个单调递减栈, 栈中存放索引位置i
     * 如果栈为空则丢进去
     * 如果不为空，当前t[i]的值大于栈顶值，则不符合单调递减栈， 当前i为栈顶元素第一个遇到的比他大的，所以可以计算出结果
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        int l = temperatures.length;
        int[] r = new int[l];
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < l; i++) {
            //空 入栈
            if (stack.isEmpty()) {
                stack.push(i);
                continue;
            }
            //栈顶大于当前元素，则入栈
            if (temperatures[stack.peek()] > temperatures[i]) {
                stack.push(i);
                continue;
            }
            //栈顶对应值小于当前元素值，则pop，且pop出来的对应结果为 i - pop出来的值
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                Integer top = stack.pop();
                r[top] = i - top;
            }
            //栈顶不存在比当前值小的元素，然后把当前值入栈
            stack.push(i);
        }
        return r;
    }


    /**
     * 暴力
     * 时间复杂度 o(N^2)
     *
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int l = temperatures.length;
        int[] r = new int[l];
        for (int i = 0; i < l; i++) {
            int v0 = temperatures[i];
            for (int j = i + 1; j < l; j++) {
                if (temperatures[j] > v0) {
                    r[i] = j - i;
                    break;
                }
            }
        }
        return r;
    }
}
