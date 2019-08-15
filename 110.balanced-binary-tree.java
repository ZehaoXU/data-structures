/*
 * @lc app=leetcode id=110 lang=java
 *
 * [110] Balanced Binary Tree
 *
 * https://leetcode.com/problems/balanced-binary-tree/description/
 *
 * algorithms
 * Easy (41.43%)
 * Likes:    1372
 * Dislikes: 122
 * Total Accepted:    344K
 * Total Submissions: 828.8K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as:
 * 
 * 
 * a binary tree in which the depth of the two subtrees of every node never
 * differ by more than 1.
 * 
 * 
 * Example 1:
 * 
 * Given the following tree [3,9,20,null,null,15,7]:
 * 
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * Return true.
 * 
 * Example 2:
 * 
 * Given the following tree [1,2,2,3,3,null,null,4,4]:
 * 
 * 
 * ⁠      1
 * ⁠     / \
 * ⁠    2   2
 * ⁠   / \
 * ⁠  3   3
 * ⁠ / \
 * ⁠4   4
 * 
 * 
 * Return false.
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
     * 和最大高度相关。如果对每一个节点，计算左右子树高度并比较，需要O(n*logn)的时间，有大量的重复步骤；如果能自底向上一边计算左右子树高度一边比较，如果不balanced就返回-1，就能自底向上一遍完成
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 94.78 % of java submissions
     * Your memory usage beats 9.26 % of java submissions (41.7 MB)
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        int height = getHeight(root);
        return height != -1;
    }
    public int getHeight(TreeNode root) {
        if (root == null)   return 0;
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);
        if (Math.abs(leftHeight-rightHeight) > 1)
            return -1;
        if (leftHeight==-1 || rightHeight==-1)  return -1;
        return Math.max(leftHeight, rightHeight)+1;
    }
}

