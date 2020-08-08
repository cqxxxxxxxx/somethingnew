package com.cqx.leetcode.zailai;

import java.util.*;

public class 今天20200803 {

    public boolean isAnagram(String s, String t) {
        if ((s == null && t != null) || (t == null && s != null)) {
            return false;
        }
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            Integer integer = map.get(aChar);
            if (integer != null) {
                map.put(aChar, ++integer);
            } else {
                map.put(aChar, 1);
            }
        }
        char[] chars1 = t.toCharArray();
        for (char c : chars1) {
            Integer integer = map.get(c);
            if (integer == null) {
                return false;
            }
            if (integer < 1) {
                return false;
            }
            map.put(c, --integer);
        }
        return true;
    }


    public List<List<String>> groupAnagrams(String[] strs) {
        List<String> sorted = new ArrayList<>(strs.length);
        Map<String, List<String>> holder = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> value = holder.get(key);
            if (value == null) {
                value = new ArrayList<>();
                value.add(str);
                holder.put(key, value);
            } else {
                value.add(str);
            }
        }
        List<List<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : holder.entrySet()) {
            result.add(entry.getValue());
        }
        return result;
    }

}
