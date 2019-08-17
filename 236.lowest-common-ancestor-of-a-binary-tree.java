/*
 * @lc app=leetcode id=236 lang=java
 *
 * [236] Lowest Common Ancestor of a Binary Tree
 *
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (38.85%)
 * Likes:    2202
 * Dislikes: 143
 * Total Accepted:    308K
 * Total Submissions: 792.3K
 * Testcase Example:  '[3,5,1,6,2,0,8,null,null,7,4]\n5\n1'
 *
 * Given a binary tree, find the lowest common ancestor (LCA) of two given
 * nodes in the tree.
 * 
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor
 * is defined between two nodes p and q as the lowest node in T that has both p
 * and q as descendants (where we allow a node to be a descendant of itself).”
 * 
 * Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * Output: 3
 * Explanation: The LCA of nodes 5 and 1 is 3.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * Output: 5
 * Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant
 * of itself according to the LCA definition.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the binary tree.
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
     * use both children, only return one
     * 最近的祖先满足一个条件：要么自己是q/p且左右某孩子含p/q，要么左右孩子都含有p/q；更远的祖先只有一个孩子有p/q
     * 想到两个孩子更新全局变量，返回一个孩子的题型。dfs return boolean表示该分支是否含有p q中的一个或两个；从底向上找寻的同时判断自身是否满足条件
     * 时间复杂度　O(n); 空间复杂度 O(h)
     * Your runtime beats 61.78 % of java submissions
     * Your memory usage beats 5.55 % of java submissions (34.9 MB)
     * 
     * 其他方法:
     *  DFS回溯找祖先，带/不带parents指针的iterations
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return res;
    }
    // return wether contains p/q or not
    public boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)   return false;
        boolean isMe = root.val == q.val || root.val == p.val;
        boolean isLeft = dfs(root.left, p, q);
        boolean isRight = dfs(root.right, p, q);

        if ((isMe&&isLeft) || (isMe&&isRight) || (isLeft&&isRight))
            res = root;
        return isMe || isLeft || isRight;
    }
    TreeNode res;
}

