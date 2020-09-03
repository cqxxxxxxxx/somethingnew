package com.cqx.leetcode.zailai.lookback.hashmap;


import java.util.Arrays;

public class 有效的字母异位词 {
    //给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
//
// 示例 1:
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
//
//
// 示例 2:
//
// 输入: s = "rat", t = "car"
//输出: false
//
// 说明:
//你可以假设字符串只包含小写字母。
//
// 进阶:
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
// Related Topics 排序 哈希表
// 👍 244 👎 0


    class Solution {
        public boolean isAnagram(String s, String t) {
            char[] chars = s.toCharArray();
            char[] chars1 = t.toCharArray();
            Arrays.sort(chars);
            Arrays.sort(chars1);
            //todo 直接比较数组 不需要构建string后比较
            return new String(chars).equals(new String(chars1));
        }

        public boolean isAnagram1(String s, String t) {
            /**
             * todo
             * s -> char数组 -> 构建map 计算每个char出现的次数
             *
             *t -> char数组 -> 基于上面的map进行减减操作， 如果<1 或者 null则返回false
             */
            return true;
        }
    }

}
