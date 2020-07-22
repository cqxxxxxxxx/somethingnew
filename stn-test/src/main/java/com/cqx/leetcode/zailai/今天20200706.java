package com.cqx.leetcode.zailai;

import java.util.HashSet;
import java.util.Set;

public class 今天20200706 {


    /**
     * https://leetcode-cn.com/problems/number-of-1-bits/solution/wei-1de-ge-shu-by-leetcode/
     */
    public static class Solution {
        // you need to treat n as an unsigned value
        public int hammingWeight(int n) {
            int bits = 0;
            int mask = 1;
            for (int i = 0; i < 32; i++) {
                if ((n & mask) != 0) {
                    bits++;
                }
                mask <<= 1;
            }
            return bits;
        }
    }


    /**
     * https://leetcode-cn.com/problems/house-robber/
     * <p>
     * TODO 动态规划
     */
    class Solution1 {
        public int rob(int[] nums) {
            return 1;
        }
    }


    /**
     * https://leetcode-cn.com/problems/remove-linked-list-elements/description/
     * 删除链表元素
     * 哨兵节点
     * 如果删除的节点是中间的节点，则问题似乎非常简单：
     */
    public static class Solution2 {
        public class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
            }
        }

        public ListNode removeElements(ListNode head, int val) {
            ListNode sentinel = new ListNode(0);
            sentinel.next = head;

            ListNode prev = sentinel, curr = head;
            while (curr != null) {
                if (curr.val == val) prev.next = curr.next;
                else prev = curr;
                curr = curr.next;
            }
            return sentinel.next;
        }

    }

    /**
     * 翻转链表
     * https://leetcode-cn.com/problems/reverse-linked-list/
     */
    public static class Solution3 {
        public class ListNode {
            int val;
            ListNode next;

            ListNode(int x) {
                val = x;
            }
        }

        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode curr = head;
            while (curr != null) {
                ListNode nextTemp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = nextTemp;
            }
            return prev;
        }

    }


    /**
     * https://leetcode-cn.com/problems/contains-duplicate-ii/
     * 存在重复元素 II
     */
    class Solution4 {
        public boolean containsNearbyDuplicate(int[] nums, int k) {
            Set<Integer> set = new HashSet<>();
            for (int i = 0; i < nums.length; ++i) {
                if (set.contains(nums[i])) return true;
                set.add(nums[i]);
                if (set.size() > k) {
                    set.remove(nums[i - k]);
                }
            }
            return false;
        }
    }
}
