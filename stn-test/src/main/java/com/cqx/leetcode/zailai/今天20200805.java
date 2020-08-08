package com.cqx.leetcode.zailai;

import java.util.ArrayList;
import java.util.List;

public class 今天20200805 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    /**
     * 傻递归 不可取
     * 斐波那契数列
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (n == 3) {
            return 3;
        }
        return climbStairs(n - 2) + climbStairs(n - 1);
    }

    /**
     * 斐波那契数列 公式
     *
     * @param n
     * @return
     */
    public int climbStairs1(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib_n = Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2, n + 1);
        return (int) (fib_n / sqrt_5);
    }


    /**
     * 斐波那契数列  缓存中间结果
     *
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int v0 = 1, v1 = 2, r = 3;
        for (int i = 3; i < n + 1; i++) {
            r = v0 + v1;
            v0 = v1;
            v1 = r;
        }
        return r;
    }


    public List<String> generateParenthesis(int n) {
        ArrayList<String> strings = new ArrayList<>();
        recursion(0, 0, n, "", strings);
        return strings;
    }

    private void recursion(int left, int right, int n, String s, ArrayList<String> strings) {
        //terminator 停止递归条件
        if (left == n && right == n) {
            //add to list
            strings.add(s);
            return;
        }
        //process 中间处理
        if (left < n) {
            recursion(left + 1, right, n, s + "(", strings);
        }
        if (left > right) {
            recursion(left, right + 1, n, s + ")", strings);
        }
        //递归下去
    }

}
