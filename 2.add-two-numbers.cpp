/*
 * @lc app=leetcode id=2 lang=cpp
 *
 * [2] Add Two Numbers
 */
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int carry = 0;
        ListNode *n = new ListNode(0), *root = n;
        
        while (l1!=nullptr || l2!=nullptr || carry){
            int v1=0, v2=0;
            if (l1!=nullptr){
                v1 = l1->val;
                l1 = l1->next;      
            };
            if (l2!=nullptr){
                v2 = l2->val;
                l2 = l2->next;
            };

            n->next = new ListNode(0);
            n->next->val = (v1 + v2 + carry) % 10;
            carry = (v1 + v2 + carry) / 10;
            n = n->next;
        };
        return root->next;
    }
};

