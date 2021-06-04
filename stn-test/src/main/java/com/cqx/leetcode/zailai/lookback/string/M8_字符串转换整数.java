package com.cqx.leetcode.zailai.lookback.string;

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数 myAtoi(string s) 的算法如下：
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−231,  231 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 * <p>
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略 任何其他字符。
 *  
 * <p>
 * 示例 1：
 * 输入：s = "42"
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-231, 231 - 1] 内，最终结果为 42 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 */
public class M8_字符串转换整数 {

    public static int myAtoi(String s) {
        char[] chars = s.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                index++;
            } else {
                break;
            }
        }
        boolean positive = true;
        if (index < chars.length && chars[index] == '+') {
            positive = true;
            index++;
        } else if (index < chars.length && chars[index] == '-') {
            positive = false;
            index++;
        } else {
            positive = true;
        }
        if (index < chars.length && (chars[index] < '0' || chars[index] > '9')) {
            return 0;
        }
        int r = 0;
        for (int i = index; i < chars.length; i++) {
            char c = chars[i];
            if (c < '0' || c > '9') {
                break;
            }
            if (r > Integer.MAX_VALUE / 10) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            if (r == Integer.MAX_VALUE / 10 && (c - '0') > 7) {
                return positive ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            r = r * 10 + c - '0';
        }
        return positive ? r : -1 * r;
    }

    public static void main(String[] args) {
        int i = myAtoi("2147483648");
        System.out.println(i);
    }
}
