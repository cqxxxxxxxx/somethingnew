package com.cqx.leetcode.zailai;

import java.util.*;

import static com.cqx.leetcode.zailai.今天20200618.Match.isValid;

public class 今天20200618 {

    /**
     * https://leetcode-cn.com/problems/two-sum/
     */
    public static class TowSum {

        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap();
            int[] r = new int[2];
            for (int i = 0; i < nums.length; i++) {
                int val0 = nums[i];
                if (map.containsKey(target - val0)) {
                    r[0] = i;
                    r[1] = map.get(target - val0);
                    break;
                }
                map.put(val0, i);
            }
            return r;
        }
    }


    /**
     * https://leetcode-cn.com/problems/3sum/solution/3sumpai-xu-shuang-zhi-zhen-yi-dong-by-jyd/
     * 双指针
     */
    public static class ThreeSum {

        public static List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> r = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                int val0 = nums[i];
                if (i > 0 && nums[i - 1] == val0) {
                    continue;
                }
                int j = i + 1;
                int k = nums.length - 1;
                while (j < k) {
                    int val1 = nums[j];
                    int val2 = nums[k];
                    if (val0 + val1 + val2 == 0) {
                        List<Integer> good = new ArrayList<>(3);
                        good.add(val0);
                        good.add(val1);
                        good.add(val2);
                        r.add(good);
                        while (++j < nums.length - 1 && val1 == nums[j]) {
                        }
                    }
                    if (val0 + val1 + val2 > 0) {
                        while (--k > -1 && val2 == nums[k]) {
                        }
                    }
                    if (val0 + val1 + val2 < 0) {
                        while (++j < nums.length - 1 && val1 == nums[j]) {
                        }
                    }
                }
            }
            return r;
        }
    }


    /**
     * https://leetcode-cn.com/problems/valid-parentheses/description/?utm_source=LCUS&utm_medium=ip_redirect_q_uns&utm_campaign=transfer2china
     */
    public static class Match {

        public static boolean isValid(String s) {
            char[] chars = s.toCharArray();
            Character[] stack = new Character[chars.length];
            int j = 0;
            for (int i = 0; i < chars.length; i++) {
                char char0 = chars[i];
                if (j == 0) {
                    stack[j] = char0;
                    j++;
                } else {
                    Character left = stack[j - 1];
                    if (char0 == '}' && left == '{') {
                        stack[j] = null;
                        j--;
                    } else if (char0 == ')' && left == '(') {
                        stack[j] = null;
                        j--;
                    } else if (char0 == ']' && left == '[') {
                        stack[j] = null;
                        j--;
                    } else {
                        stack[j] = char0;
                        j++;
                    }
                }
            }
            return j == 0;
        }
    }


    /**
     * https://leetcode-cn.com/problems/merge-two-sorted-lists/
     */
    public static class MergeTowList {

        public class ListNode {
            int val;
            ListNode next;

            ListNode() {
            }

            ListNode(int val) {
                this.val = val;
            }

            ListNode(int val, ListNode next) {
                this.val = val;
                this.next = next;
            }
        }

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            }
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            int val1 = l1.val;
            int val2 = l2.val;
            if (val1 < val2) {
                ListNode tmp = l1.next;
                l1.next = mergeTwoLists(tmp, l2);
                return l1;
            } else {
                ListNode tmp = l2.next;
                l2.next = mergeTwoLists(tmp, l1);
                return l2;
            }
        }

    }


    public static void main(String[] args) {
        String a = "(]";
        System.out.println(isValid(a));
    }
}