package com.cqx.leetcode.zailai.lookback.recursion;

import java.util.ArrayList;
import java.util.List;

public class M22_括号生成 {

    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * <p>
     *  
     * <p>
     * 示例：
     * <p>
     * 输入：n = 3
     * 输出：[
     * "((()))",
     * "(()())",
     * "(())()",
     * "()(())",
     * "()()()"
     * ]
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> r = new ArrayList<>();
        recursion(n, n, "", r);
        return r;
    }

    private void recursion(int leftRemain, int rightRemain, String tmp, List<String> r) {
        //termination
        if (leftRemain == 0 && rightRemain == 0) {
            r.add(tmp);
            return;
        }
        //process
        if (leftRemain > 0) {
            recursion(leftRemain - 1, rightRemain, tmp + "(", r);
        }
        if (leftRemain < rightRemain) {
            recursion(leftRemain, rightRemain - 1, tmp + ")", r);
        }
        //dive down
    }


}
