/*
 * @lc app=leetcode id=543 lang=java
 *
 * [543] Diameter of Binary Tree
 *
 * https://leetcode.com/problems/diameter-of-binary-tree/description/
 *
 * algorithms
 * Easy (47.28%)
 * Likes:    1585
 * Dislikes: 98
 * Total Accepted:    148.8K
 * Total Submissions: 314.8K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * 
 * Given a binary tree, you need to compute the length of the diameter of the
 * tree. The diameter of a binary tree is the length of the longest path
 * between any two nodes in a tree. This path may or may not pass through the
 * root.
 * 
 * 
 * 
 * Example:
 * Given a binary tree 
 * 
 * ⁠         1
 * ⁠        / \
 * ⁠       2   3
 * ⁠      / \     
 * ⁠     4   5    
 * 
 * 
 * 
 * Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].
 * 
 * 
 * Note:
 * The length of path between two nodes is represented by the number of edges
 * between them.
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
     * use both children, only return one
     * 从上至下计算高度需要大量重复计算，所以考虑从下向上计算。每步需要左右子树的最大高度，故返回max(left,right)；同时有了左右子树的最大高度，也能计算以该节点为转折点的diameter
     * 时间复杂度 O(n)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 44.16 % of java submissions (38.4 MB)
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return d;
    }
    int d = 0;
    public int height(TreeNode root) {
        if (root == null)   return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        d = Math.max(d, leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight)+1;
    }
}

