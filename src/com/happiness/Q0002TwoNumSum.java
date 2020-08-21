package com.happiness;

import com.happiness.model.ListNode;

public class Q0002TwoNumSum {


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l2 == null && l1 != null) {
            l2 = new ListNode(0);
        } else if (l2 != null && l1 == null) {
            l1 = new ListNode(0);
        }

        int sum = l1.val + l2.val;
        ListNode node = null;
        if (sum >= 10) {
            sum = sum - 10;

            if (l1.next != null) {
                l1.next.val += 1;
            } else if (l2.next != null) {
                l2.next.val += 1;
            } else {
                l1.next = new ListNode(1);
                l2.next = new ListNode(0);
            }
        }
        node = new ListNode(sum);
        node.next = addTwoNumbers(l1.next, l2.next);
        return node;
    }


    public static void main(String[] args) {
//        输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//        输出：7 -> 0 -> 8
//        原因：342 + 465 = 807
        Q0002TwoNumSum q2 = new Q0002TwoNumSum();
        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        node2.next = node4;
        node4.next = node3;


        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node4_2 = new ListNode(4);
        node5.next = node6;
        node6.next = node4_2;

        ListNode result = q2.addTwoNumbers(node2,node5);
        System.out.println("Q0002TwoNumSum: " + result);
    }
}
