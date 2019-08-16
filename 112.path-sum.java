/*
 * @lc app=leetcode id=112 lang=java
 *
 * [112] Path Sum
 *
 * https://leetcode.com/problems/path-sum/description/
 *
 * algorithms
 * Easy (38.42%)
 * Likes:    1066
 * Dislikes: 349
 * Total Accepted:    339.5K
 * Total Submissions: 883.8K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,null,1]\n22'
 *
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path
 * such that adding up all the values along the path equals the given sum.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * Given the below binary tree and sum = 22,
 * 
 * 
 * ⁠     5
 * ⁠    / \
 * ⁠   4   8
 * ⁠  /   / \
 * ⁠ 11  13  4
 * ⁠/  \      \
 * 7    2      1
 * 
 * 
 * return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
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
class SolutionRecursive {
    /**
     * 递归，因为root=null sum=0判断是false，所以不能用叶子上的null节点判断，而要在叶子节点就进行判断
     * 时间复杂度 O(n)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.1 MB)
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)   return false;
        if (root.left==null && root.right==null)
            return sum == root.val;

        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}

