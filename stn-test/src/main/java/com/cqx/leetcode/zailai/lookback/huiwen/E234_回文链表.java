package com.cqx.leetcode.zailai.lookback.huiwen;

import java.util.LinkedList;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 */
public class E234_回文链表 {

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

//=============================================================

    /**
     * 1. 快慢指针找中间点
     * 2. 然后反转其中一半链表在进行比较
     */
    public boolean solution3(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时slow就是中间节点, 然后反转后半部链表
        ListNode rightHead = reverse(slow.next);
        ListNode leftHead = head;
        while (rightHead != null) {
            if (leftHead.val == rightHead.val) {
                leftHead = leftHead.next;
                rightHead = rightHead.next;
            } else {
                return false;
            }
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
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


//=============================================================


//=============================================================
    /**
     * 递归
     */
    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) {
                return false;
            }
            if (currentNode.val != frontPointer.val) {
                return false;
            }
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean solution2(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }


    //=============================================================


    /**
     * 错误
     * 栈不能用来验证回文，只能用来验证左右括号之类问题  错误
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        LinkedList<ListNode> stack = new LinkedList<>();
        if (head.next == null) {
            return true;
        }
        stack.push(head);
        while (head.next != null) {
            ListNode next = head.next;
            if (stack.isEmpty()) {
                stack.push(head);
            } else if (stack.peek().val == next.val) {
                stack.pop();
            } else {
                stack.push(next);
            }
            head = next;
        }
        return stack.isEmpty();
    }
}
