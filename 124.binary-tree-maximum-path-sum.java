/*
 * @lc app=leetcode id=124 lang=java
 *
 * [124] Binary Tree Maximum Path Sum
 *
 * https://leetcode.com/problems/binary-tree-maximum-path-sum/description/
 *
 * algorithms
 * Hard (30.62%)
 * Likes:    1842
 * Dislikes: 137
 * Total Accepted:    212K
 * Total Submissions: 692.2K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a non-empty binary tree, find the maximum path sum.
 * 
 * For this problem, a path is defined as any sequence of nodes from some
 * starting node to any node in the tree along the parent-child connections.
 * The path must contain at least one node and does not need to go through the
 * root.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3]
 * 
 * ⁠      1
 * ⁠     / \
 * ⁠    2   3
 * 
 * Output: 6
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [-10,9,20,null,null,15,7]
 * 
 * -10
 * / \
 * 9  20
 * /  \
 * 15   7
 * 
 * Output: 42
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
     * 聪明方法真聪明。都能想到大概需要两层递归，一层改变root，一层计算max(leftGain, rightGain)，这种时间复杂度 O(n^2)
     * 上述方法在计算左右子树gain的时候有大量重复的情况，于是考虑是否有像高度&balanced一样，从下到上遍历一次的方法
     * 在求左右子树gain的时候，返回 max(left,right)+root.val 就是正常的计算gain函数的递归；而 left+right+root.val 就是已root为转折点的maxPathSum。
     * 综上，maxGain函数主要负责计算左右子树gain，顺带可以从下至上计算所有的maxPathSum，只要一个全局变量不断更新，就能遍历一遍得到结果
     * 时间复杂度　O(n); 空间复杂度 O(h)
     * Your runtime beats 99.98 % of java submissions
     * Your memory usage beats 92.86 % of java submissions (40.5 MB)
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        maxGain(root);

        return maxSum;
    }

    int maxSum = Integer.MIN_VALUE;

    public int maxGain(TreeNode root) {
        if (root == null)   return 0;
        int leftSum = Math.max(maxGain(root.left), 0);
        int rightSum = Math.max(maxGain(root.right), 0);

        int newSum = root.val + leftSum + rightSum;
        maxSum = Math.max(maxSum, newSum);

        return Math.max(leftSum, rightSum) + root.val;
    }
}

class SolutionStupidMost {
    /**
     * 笨蛋方法，笨的很，简直太丑陋了
     * Your runtime beats 5.06 % of java submissions
     * Your memory usage beats 5.95 % of java submissions (44.3 MB)
     * @param root
     * @return
     */
    public int maxPathSum(TreeNode root) {
        if (root == null)   return 0;
        int rootMax = root.val + maxSubtree(root.left) + maxSubtree(root.right);
        if (root.right == null && root.left == null)
            return rootMax;
        else if (root.left == null)
            return Math.max(maxPathSum(root.right), rootMax);
        else if (root.right == null)
            return Math.max(maxPathSum(root.left), rootMax);
        else {
            int max = Math.max(rootMax, Math.max(maxPathSum(root.left), maxPathSum(root.right)));
            return max;
        }
    }
    public int maxSubtree(TreeNode root) {
        if (root == null)    return 0;
        
        int subtree = Math.max(maxSubtree(root.left), maxSubtree(root.right));
        subtree = subtree > 0 ? subtree : 0;
        int res = root.val + subtree;
        return res > 0 ? res : 0;
    }
}

