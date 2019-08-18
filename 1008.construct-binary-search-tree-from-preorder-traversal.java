import java.util.ArrayDeque;
import java.util.Deque;

/*
 * @lc app=leetcode id=1008 lang=java
 *
 * [1008] Construct Binary Search Tree from Preorder Traversal
 *
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/description/
 *
 * algorithms
 * Medium (73.27%)
 * Likes:    296
 * Dislikes: 13
 * Total Accepted:    20.1K
 * Total Submissions: 27.5K
 * Testcase Example:  '[8,5,1,7,10,12]'
 *
 * Return the root node of a binary search tree that matches the given preorder
 * traversal.
 * 
 * (Recall that a binary search tree is a binary tree where for every node, any
 * descendant of node.left has a value < node.val, and any descendant of
 * node.right has a value > node.val.  Also recall that a preorder traversal
 * displays the value of the node first, then traverses node.left, then
 * traverses node.right.)
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [8,5,1,7,10,12]
 * Output: [8,5,10,1,7,null,12]
 * 
 * 
 * 
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= preorder.length <= 100
 * The values of preorder are distinct.
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 前序简历BST，用了成员变量index，还可以用deque，不断把第一个元素remove
     * 用上下界限定是否在这棵子树上
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (35.8 MB)
     * @param preorder
     * @return
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        TreeNode root = bstFromPreorder(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return root;
    }
    private int index = 0;
    public TreeNode bstFromPreorder(int[] preorder, int min, int max) {
        if (index >= preorder.length)    return null;
        int val = preorder[index];
        if (val > max || val < min) return null;

        index++;
        TreeNode root = new TreeNode(val);
        root.left = bstFromPreorder(preorder, min, val);
        root.right = bstFromPreorder(preorder, val, max);
        return root;
    }
}

