package com.cqx.leetcode.zailai;

import java.util.*;

public class 今天20200709 {
    /**
     * https://leetcode-cn.com/problems/distribute-candies/description/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
     */
    class Solution {
        public int distributeCandies(int[] candies) {
            int each = candies.length / 2;
            Set set = new HashSet<>();
            for (int i = 0; i < candies.length; i++) {
                set.add(candies[i]);
            }
            if (set.size() < each) {
                return set.size();
            }
            return each;
        }
    }

    /**
     * https://leetcode-cn.com/problems/shift-2d-grid/submissions/
     */
    class Solution1 {
        public List<List<Integer>> shiftGrid(int[][] grid, int k) {
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    list.add(grid[i][j]);
                }
            }
            for (int i = 0; i < k; i++) {
                Integer last = list.pollLast();
                list.addFirst(last);
            }
            List<List<Integer>> r = new ArrayList<>();
            for (int i = 0; i < grid.length; i++) {
                List inner = new ArrayList();
                for (int j = 0; j < grid[i].length; j++) {
                    inner.add(list.pop());
                }
                r.add(inner);
            }
            return r;
        }
    }


    /**
     * https://leetcode-cn.com/problems/remove-palindromic-subsequences/
     */
    class Solution2 {
        public int removePalindromeSub(String s) {
            if (s.equals("")) {
                return 0;
            }
            int length = s.length();
            for (int i = 0; i < length; i++) {
                if (s.charAt(i) == s.charAt(length - 1 - i)) {
                    continue;
                } else {
                    return 2;
                }
            }
            return 1;
        }
    }
}
