/*
 * @lc app=leetcode id=979 lang=java
 *
 * [979] Distribute Coins in Binary Tree
 *
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/description/
 *
 * algorithms
 * Medium (67.78%)
 * Likes:    643
 * Dislikes: 22
 * Total Accepted:    17.6K
 * Total Submissions: 26K
 * Testcase Example:  '[3,0,0]'
 *
 * Given the root of a binary tree with N nodes, each node in the tree has
 * node.val coins, and there are N coins total.
 * 
 * In one move, we may choose two adjacent nodes and move one coin from one
 * node to another.  (The move may be from parent to child, or from child to
 * parent.)
 * 
 * Return the number of moves required to make every node have exactly one
 * coin.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * Input: [3,0,0]
 * Output: 2
 * Explanation: From the root of the tree, we move one coin to its left child,
 * and one coin to its right child.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: [0,3,0]
 * Output: 3
 * Explanation: From the left child of the root, we move two coins to the root
 * [taking two moves].  Then, we move one coin from the root of the tree to the
 * right child.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * 
 * 
 * Input: [1,0,2]
 * Output: 2
 * 
 * 
 * 
 * Example 4:
 * 
 * 
 * 
 * 
 * Input: [1,0,0,null,3]
 * Output: 4
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1<= N <= 100
 * 0 <= node.val <= N
 * 
 * 
 * 
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
     * 很难抽象问题，一定要转化成递归的问题，变成根和左右子树的关系。当且仅当子树coins和nodes个数不等时，才需要向根穿coins或者从根拿coins，故节点返回coins-nodes值，绝对值就是和根的硬币交换量。这样自底向上一次，就能不断更新交换次数
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 75.94 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.8 MB)
     * @param root
     * @return
     */
    public int distributeCoins(TreeNode root) {
        dfs(root);
        return res;
    }
    private int res = 0;
    private int dfs(TreeNode root) {
        if (root == null)   return 0;
        int leftFlow = dfs(root.left);
        int rightFlow = dfs(root.right);
        res += Math.abs(leftFlow) + Math.abs(rightFlow);

        return leftFlow+rightFlow+root.val-1;
    }
}

