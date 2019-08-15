/*
 * @lc app=leetcode id=100 lang=java
 *
 * [100] Same Tree
 *
 * https://leetcode.com/problems/same-tree/description/
 *
 * algorithms
 * Easy (50.43%)
 * Likes:    1247
 * Dislikes: 42
 * Total Accepted:    404.7K
 * Total Submissions: 801.3K
 * Testcase Example:  '[1,2,3]\n[1,2,3]'
 *
 * Given two binary trees, write a function to check if they are the same or
 * not.
 * 
 * Two binary trees are considered the same if they are structurally identical
 * and the nodes have the same value.
 * 
 * Example 1:
 * 
 * 
 * Input:     1         1
 * ⁠         / \       / \
 * ⁠        2   3     2   3
 * 
 * ⁠       [1,2,3],   [1,2,3]
 * 
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:     1         1
 * ⁠         /           \
 * ⁠        2             2
 * 
 * ⁠       [1,2],     [1,null,2]
 * 
 * Output: false
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:     1         1
 * ⁠         / \       / \
 * ⁠        2   1     1   2
 * 
 * ⁠       [1,2,1],   [1,1,2]
 * 
 * Output: false
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
     * 递归，比较val以及左右子树，注意null的情况
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.1 MB)
     * 
     * 其他方法：遍历，每一步check是否相同，BFS/前/中/后序；其中BFS和前序遍历最简单
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p==null && q!=null) return false;
        if (p!=null && q==null) return false;
        if (p==null && q==null) return true;

        if (p.val != q.val) return false;
        boolean l = isSameTree(p.left, q.left);
        boolean r = isSameTree(p.right, q.right);

        return (l&&r);
    }
}

