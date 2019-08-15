/*
 * @lc app=leetcode id=965 lang=java
 *
 * [965] Univalued Binary Tree
 *
 * https://leetcode.com/problems/univalued-binary-tree/description/
 *
 * algorithms
 * Easy (66.94%)
 * Likes:    221
 * Dislikes: 36
 * Total Accepted:    43.5K
 * Total Submissions: 64.9K
 * Testcase Example:  '[1,1,1,1,1,null,1]'
 *
 * A binary tree is univalued if every node in the tree has the same value.
 * 
 * Return true if and only if the given tree is univalued.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1,1,1,1,1,null,1]
 * Output: true
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [2,2,2,5,2]
 * Output: false
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of nodes in the given tree will be in the range [1, 100].
 * Each node's value will be an integer in the range [0, 99].
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
     * 递归，前序遍历，比较val是否相等
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.2 MB)
     * @param root
     * @return
     */
    public boolean isUnivalTree(TreeNode root) {
        int val = root.val;
        return isUnivalTree(root, val);
    }
    public boolean isUnivalTree(TreeNode root, int val) {
        if (root == null)   return true;
        if (root.val != val)    return false;
        return isUnivalTree(root.left, val) && isUnivalTree(root.right, val);
    }
}

