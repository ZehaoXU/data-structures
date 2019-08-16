/*
 * @lc app=leetcode id=814 lang=java
 *
 * [814] Binary Tree Pruning
 *
 * https://leetcode.com/problems/binary-tree-pruning/description/
 *
 * algorithms
 * Medium (71.68%)
 * Likes:    613
 * Dislikes: 20
 * Total Accepted:    38.9K
 * Total Submissions: 54.3K
 * Testcase Example:  '[1,null,0,0,1]'
 *
 * We are given the head node root of a binary tree, where additionally every
 * node's value is either a 0 or a 1.
 * 
 * Return the same tree where every subtree (of the given tree) not containing
 * a 1 has been removed.
 * 
 * (Recall that the subtree of a node X is X, plus every node that is a
 * descendant of X.)
 * 
 * 
 * Example 1:
 * Input: [1,null,0,0,1]
 * Output: [1,null,0,null,1]
 * ⁠
 * Explanation: 
 * Only the red nodes satisfy the property "every subtree not containing a 1".
 * The diagram on the right represents the answer.
 * 
 * 
 * 
 * 
 * 
 * Example 2:
 * Input: [1,0,1,0,0,0,1]
 * Output: [1,null,1,null,1]
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 3:
 * Input: [1,1,0,1,1,0,1,0]
 * Output: [1,1,0,1,1,null,1]
 * 
 * 
 * 
 * 
 * 
 * Note: 
 * 
 * 
 * The binary tree will have at most 100 nodes.
 * The value of each node will only be 0 or 1.
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
     * 类似于判断平衡二叉树，如果从上到下判断的话，需要nlogn；如果从下到上判断，顺便剪枝的话，只需要n
     * 两种想法：一种return boolean，上层节点据此判断是否去掉左右子树；或者return Node，节点本身判断，如果全0则本身为null返回
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.2 MB)
     * @param root
     * @return
     */
    public TreeNode pruneTree(TreeNode root) {
        isAllZero(root);
        return root;
    }
    public boolean isAllZero(TreeNode root) {
        if (root == null)   return false;
        
        if (isAllZero(root.left))   root.left = null;
        if (isAllZero(root.right))  root.right = null;
        if (root.left==null && root.right==null && root.val==0)
            return true;
        // return root.val==1 || root.left!=null || root.right != null;
        return false;
    }
}

