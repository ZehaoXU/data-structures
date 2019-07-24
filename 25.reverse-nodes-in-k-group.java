/*
 * @lc app=leetcode id=25 lang=java
 *
 * [25] Reverse Nodes in k-Group
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
     * 双重递归，每k个链表截断成小的链表段，然后每个小链表段递归求倒转
     * 时间复杂度 O(n)  取序列遍历一遍n，倒转遍历一遍n
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 21.62 % of java submissions (38.7 MB)
     * 
     * 思考：如何倒转一个链表？顺序方法&递归方法；长链表暂时切断，分段处理；引用的具体含义
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        // 递归出口
        if (head == null)  return head;
        //  head在内需要颠倒顺序，先检查元素够不够k个
        ListNode cur = head;
        ListNode past = head;
        for (int i = 0; i < k; i++){
            past = cur;
            cur = cur.next;
            if (cur == null && i != k-1)    return head;
        }

        // 此时需要颠倒head到past这部分的链表，颠倒后past是头，head是尾
        past.next = null;
        past =head;
        head = reverse(head);
        past.next = reverseKGroup(cur, k);

        return head;
    }

    public ListNode reverse(ListNode head){
        // 递归出口
        if (head == null || head.next == null)  return head;

        ListNode newHead = reverse(head.next);
        // 此时head指向的变成了tail
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}

