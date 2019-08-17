/*
 * @lc app=leetcode id=129 lang=java
 *
 * [129] Sum Root to Leaf Numbers
 *
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
 *
 * algorithms
 * Medium (43.37%)
 * Likes:    762
 * Dislikes: 29
 * Total Accepted:    196.4K
 * Total Submissions: 452.7K
 * Testcase Example:  '[1,2,3]'
 *
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path
 * could represent a number.
 * 
 * An example is the root-to-leaf path 1->2->3 which represents the number
 * 123.
 * 
 * Find the total sum of all root-to-leaf numbers.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3]
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * Output: 25
 * Explanation:
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Therefore, sum = 12 + 13 = 25.
 * 
 * Example 2:
 * 
 * 
 * Input: [4,9,0,5,1]
 * ⁠   4
 * ⁠  / \
 * ⁠ 9   0
 * / \
 * 5   1
 * Output: 1026
 * Explanation:
 * The root-to-leaf path 4->9->5 represents the number 495.
 * The root-to-leaf path 4->9->1 represents the number 491.
 * The root-to-leaf path 4->0 represents the number 40.
 * Therefore, sum = 495 + 491 + 40 = 1026.
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
     * DFS遍历，前序遍历。退出条件有两个：root==null和root是叶子节点
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.5 MB)
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    int res = 0;
    public void dfs(TreeNode root, int num) {
        if (root == null)   return;

        num = num*10 + root.val;
        if (root.left==null && root.right==null) {
            res += num;
            return;
        }
        dfs(root.left, num);
        dfs(root.right, num);
        return;
    }
}

