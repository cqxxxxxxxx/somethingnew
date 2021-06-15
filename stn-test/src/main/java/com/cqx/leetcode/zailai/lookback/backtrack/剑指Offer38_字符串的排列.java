package com.cqx.leetcode.zailai.lookback.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * <p>
 * 示例:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 * <p>
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 */
public class 剑指Offer38_字符串的排列 {

    List<String> r = new ArrayList<>();

    public String[] permutation(String s) {
        boolean[] visited = new boolean[s.toCharArray().length];
        backtrack0(s.toCharArray(), new StringBuilder(), visited);
        return r.toArray(new String[0]);
    }

    private void backtrack0(char[] chars, StringBuilder sb, boolean[] visited) {
        if (sb.length() == chars.length) {
            r.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            backtrack0(chars, sb.append(chars[i]), visited);
            visited[i] = false;
        }
    }


    public String[] permutationRight(String s) {
        Set<String> res = new HashSet<>();
        backtrack(s.toCharArray(), "", new boolean[s.length()], res);
        return res.toArray(new String[res.size()]);
    }

    private void backtrack(char[] chars, String temp, boolean[] visited, Set<String> res) {
        //边界条件判断，当选择的字符长度等于原字符串长度的时候，说明原字符串的字符都已经选完了
        if (temp.length() == chars.length) {
            res.add(temp);
            return;
        }
        //每一个节点我们都要从头开始选
        for (int i = 0; i < chars.length; i++) {
            //已经选择过的就不能再选了
            if (visited[i])
                continue;
            //表示选择当前字符
            visited[i] = true;
            //把当前字符选择后，到树的下一层继续选
            backtrack(chars, temp + chars[i], visited, res);
            //递归往回走的时候要撤销选择
            visited[i] = false;
        }
    }

}
