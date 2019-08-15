/*
 * @lc app=leetcode id=111 lang=java
 *
 * [111] Minimum Depth of Binary Tree
 *
 * https://leetcode.com/problems/minimum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (35.64%)
 * Likes:    815
 * Dislikes: 446
 * Total Accepted:    315K
 * Total Submissions: 882.6K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * return its minimum depth = 2.
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
     * 递归，和最大高度不同，这次要分情况（如果一个子树是null）
     *  if root==null, return 0;
     *  if root.right==null, return minDepth(root.left)+1;
     *  if root.left==null, return minDepth(root.right)+1;
     *  else return min(minDepthLeft, minDepthRight)+1;
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 98.44 % of java submissions (38.8 MB)
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null)   return 0;
        int l = minDepth(root.left);
        int r = minDepth(root.right);
        if (l==0 || r==0)   return Math.max(r,l)+1;
        return Math.min(l, r)+1;
    }
}

