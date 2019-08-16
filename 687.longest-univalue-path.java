/*
 * @lc app=leetcode id=687 lang=java
 *
 * [687] Longest Univalue Path
 *
 * https://leetcode.com/problems/longest-univalue-path/description/
 *
 * algorithms
 * Easy (34.28%)
 * Likes:    1060
 * Dislikes: 270
 * Total Accepted:    64.7K
 * Total Submissions: 188.8K
 * Testcase Example:  '[5,4,5,1,1,5]'
 *
 * Given a binary tree, find the length of the longest path where each node in
 * the path has the same value. This path may or may not pass through the
 * root.
 * 
 * The length of path between two nodes is represented by the number of edges
 * between them.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input:
 * 
 * 
 * ⁠             5
 * ⁠            / \
 * ⁠           4   5
 * ⁠          / \   \
 * ⁠         1   1   5
 * 
 * 
 * Output: 2
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * 
 * 
 * ⁠             1
 * ⁠            / \
 * ⁠           4   5
 * ⁠          / \   \
 * ⁠         4   4   5
 * 
 * 
 * Output: 2
 * 
 * 
 * 
 * Note: The given binary tree has not more than 10000 nodes. The height of the
 * tree is not more than 1000.
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
     * using both children, only return one
     * 感觉比同类型的前两道复杂一点。返回左右连续的UniValue点数，如果有左右子树则至少返回1，需要分别判断root和左右子树是否相等，然后记录leftUnivalue和rightUnivalue，返回时+1包括root本身
     * 时间复杂度 O(n)；空间复杂度 O(h)
     * Your runtime beats 73.48 % of java submissions
     * Your memory usage beats 100 % of java submissions (41.2 MB)
     * 
     * 注意：
     *  此类问题全局变量的初始值需要注意！小心空或者只有一个节点的情况
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return path;
    } 
    int path = 0;
    public int helper(TreeNode root) {
        if (root == null)   return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int leftUnivalue = 0, rightUnivalue = 0;

        if (left!=0 && root.val==root.left.val) {
            leftUnivalue = left;
        }
        if (right!=0 && root.val==root.right.val) {
            rightUnivalue = right;
        }

        path = Math.max(path, leftUnivalue + rightUnivalue);
        return Math.max(leftUnivalue, rightUnivalue) + 1;
    }
}

