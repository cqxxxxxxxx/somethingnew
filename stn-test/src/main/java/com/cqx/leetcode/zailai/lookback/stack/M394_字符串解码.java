package com.cqx.leetcode.zailai.lookback.stack;

import java.util.LinkedList;

/**
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * <p>
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 链接：https://leetcode-cn.com/problems/decode-string
 */
public class M394_字符串解码 {

    /**
     * 栈,遇到]，出栈
     *
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == ']') {
                StringBuilder sb = new StringBuilder();
                String code = "";
                while (stack.getLast() != '[') {
                    code = stack.pollLast() + code;
                }
                stack.pollLast();
                String numStr = "";
                while (!stack.isEmpty() && stack.getLast() >= '0' && stack.getLast() <= '9') {
                    numStr = stack.pollLast() + numStr;
                }
                for (int i = 0; i < Integer.valueOf(numStr); i++) {
                    sb.append(code);
                }
                for (int i = 0; i < sb.length(); i++) {
                    stack.addLast(sb.charAt(i));
                }
            } else {
                stack.addLast(aChar);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("100[leetcode]"));


        Character one = '1';
        Integer i = (int) one;
        System.out.println(i);
        System.out.println(Integer.valueOf(one - '0'));
    }
}
