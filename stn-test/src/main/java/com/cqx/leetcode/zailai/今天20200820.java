package com.cqx.leetcode.zailai;

import java.util.*;

public class 今天20200820 {

    /**
     * 169. 多数元素
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums); //O(n log(n))
        return nums[nums.length / 2];
    }


    /**
     * 17. 电话号码的字母组合
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {

        Map<Character, String> phone = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        List<String> output = new ArrayList<>();


        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        recursion("", digits, 0, phone, output);
        return output;
    }

    public void recursion(String s, String digits, int index, Map<Character, String> phone, List<String> output) {
        //terminator
        if (index == digits.length()) {
            output.add(s);
            return;
        }
        //process
        String s1 = phone.get(digits.charAt(index));
        index++;
        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            recursion(s + c, digits, index, phone, output);
        }
    }

}
