/*
 * @lc app=leetcode id=21 lang=java
 *
 * [21] Merge Two Sorted Lists
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 /**
  * 常规方法，建立一个新列表，两个列表遍历将较小的值添加至新链表尾
  * 注意链表为空的情况
  */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode pointer = dummy;

        while (l1 != null && l2 != null){
            if (l1.val > l2.val){
                pointer.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            else {
                pointer.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            pointer = pointer.next;
        }

        if (l1 == null) pointer.next = l2;
        if (l2 == null) pointer.next = l1;
        return dummy.next;
    }
}

