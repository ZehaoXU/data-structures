/*
 * @lc app=leetcode id=19 lang=java
 *
 * [19] Remove Nth Node From End of List
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        /**
         * 采用双指针，快慢指针保持n的间隔，快指针移到最后，慢指针正好到倒数第n个位置
         * 默认不含头节点，head直接指向链表第一个元素
         * 注意：返回的一定是新链表，不能是head开头的原列表，因为如果head节点被删除
         */
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode pFast = newHead, pSlow = newHead;
        int count = 0;
        while (pFast.next != null){
            if (count < n){
                count++;
                pFast =  pFast.next;
            }
            else {
                pFast = pFast.next;
                pSlow = pSlow.next;
            }
        }
        
        pSlow.next = pSlow.next.next;
        return newHead.next;
    }
}

