package com.cqx.leetcode.zailai.lookback.hashmap;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/group-anagrams/
 */
public class 字母异位词分组 {
    //给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
//
// 示例:
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
//
// 说明：
//
//
// 所有输入均为小写字母。
// 不考虑答案输出的顺序。
//
// Related Topics 哈希表 字符串
// 👍 449 👎 0


    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> map = new HashMap<>();
            for (String str : strs) {
                char[] chars = str.toCharArray();
                Arrays.sort(chars);
                String key = new String(chars);
                List<String> list = map.get(key);
                if (list == null) {
                    list = new ArrayList<>();
                    list.add(str);
                    map.put(key, list);
                } else {
                    list.add(str);
                }
            }
            List<List<String>> r = new ArrayList<>();
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                r.add(entry.getValue());
            }
            return r;
        }
    }

}
