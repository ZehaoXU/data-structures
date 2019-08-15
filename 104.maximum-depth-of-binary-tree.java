/*
 * @lc app=leetcode id=104 lang=java
 *
 * [104] Maximum Depth of Binary Tree
 *
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 *
 * algorithms
 * Easy (61.43%)
 * Likes:    1481
 * Dislikes: 58
 * Total Accepted:    556.9K
 * Total Submissions: 904.2K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, find its maximum depth.
 * 
 * The maximum depth is the number of nodes along the longest path from the
 * root node down to the farthest leaf node.
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
 * return its depth = 3.
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
     * 递归，左右子树最大高度+1
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 94.62 % of java submissions (39.1 MB)
     * 
     * 其他方法：BFS Iterative level
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null)   return 0;
        int maxSubtree = Math.max(maxDepth(root.left), maxDepth(root.right));
        return maxSubtree+1;
    }
}

