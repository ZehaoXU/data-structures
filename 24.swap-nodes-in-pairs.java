/*
 * @lc app=leetcode id=24 lang=java
 *
 * [24] Swap Nodes in Pairs
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
     * 遍历一次，调转相邻两个节点，注意退出条件（奇数节点与偶数节点不同）
     * 时间复杂度 O(N)  空间复杂度O(1)  原链表穿插
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.1 MB)
     * 其他方法：可以用递归的结构，每次只调转前两个节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode res = new ListNode(0);
        ListNode pointer = res;
        res.next = head;
        ListNode oldPointer;
        
        while (pointer.next != null && pointer.next.next != null){
            oldPointer = pointer;
            pointer = pointer.next;
            oldPointer.next = pointer.next;
            pointer.next = pointer.next.next;
            oldPointer.next.next = pointer;
        }

        return res.next;
    }
}

