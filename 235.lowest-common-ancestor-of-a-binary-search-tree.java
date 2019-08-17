/*
 * @lc app=leetcode id=235 lang=java
 *
 * [235] Lowest Common Ancestor of a Binary Search Tree
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 *
 * algorithms
 * Easy (45.74%)
 * Likes:    1204
 * Dislikes: 88
 * Total Accepted:    303.4K
 * Total Submissions: 663.2K
 * Testcase Example:  '[6,2,8,0,4,7,9,null,null,3,5]\n2\n8'
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of
 * two given nodes in the BST.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both p
 * and q as descendants (where we allow a node to be a descendant of itself).”
 * 
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant
 * of itself according to the LCA definition.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the BST.
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
     * 236简单版，BST，和236最大的差别是不用backtrack！相当于二分法而不是分治法，每次只要对一边的子树进行处理即可！所以如果iterations方法连stack都不需要，直接root左右变换就行
     * 时间复杂度 O(h)；空间复杂度 O(h) 而iteration方法空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.1 % of java submissions (36.7 MB)
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) {    // p is the smaller one
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        dfs(root, p, q);
        return res;
    }
    TreeNode res = null;
    public void dfs(TreeNode root, TreeNode small, TreeNode big) {
        if (root == null)   return;
        if (root.val < small.val)   
            dfs(root.right, small, big);
        else if (root.val > big.val)
            dfs(root.left, small, big);
        else
            res = root;
        return;
    }
}

