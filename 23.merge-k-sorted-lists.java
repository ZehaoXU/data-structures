/*
 * @lc app=leetcode id=23 lang=java
 *
 * [23] Merge k Sorted Lists
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 采用分治法，最终用两个列表合并解决问题
     * 时间复杂度 O(N*logk)
     * Your runtime beats 84.82 % of java submissions
     * Your memory usage beats 75.11 % of java submissions (39.4 MB)
     * 可以不用新建列表，而采用原列表l1 l2穿线来减少空间使用
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2){
        ListNode head = new ListNode(0);
        ListNode pointer = head;
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
        return head.next;
    }

    public ListNode divedeLists(ListNode[] lists, int left, int right){
        if (left >= right)  return right < 0 ? null : lists[left];
        else if (left + 1 == right)  return mergeTwoLists(lists[left], lists[right]);
        else {
            int mid = (left + right) / 2;
            ListNode leftList = divedeLists(lists, left, mid);
            ListNode rightList = divedeLists(lists, mid + 1, right);
            return mergeTwoLists(leftList, rightList);
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        return divedeLists(lists, 0, lists.length - 1);
    }
}

