/*
 * @lc app=leetcode id=337 lang=java
 *
 * [337] House Robber III
 *
 * https://leetcode.com/problems/house-robber-iii/description/
 *
 * algorithms
 * Medium (48.65%)
 * Likes:    1684
 * Dislikes: 35
 * Total Accepted:    112K
 * Total Submissions: 230K
 * Testcase Example:  '[3,2,3,null,3,null,1]'
 *
 * The thief has found himself a new place for his thievery again. There is
 * only one entrance to this area, called the "root." Besides the root, each
 * house has one and only one parent house. After a tour, the smart thief
 * realized that "all houses in this place forms a binary tree". It will
 * automatically contact the police if two directly-linked houses were broken
 * into on the same night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without
 * alerting the police.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,3,null,3,null,1]
 * 
 * ⁠    3
 * ⁠   / \
 * ⁠  2   3
 * ⁠   \   \ 
 * ⁠    3   1
 * 
 * Output: 7 
 * Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * 
 * Example 2:
 * 
 * 
 * Input: [3,4,5,1,3,null,1]
 * 
 * 3
 * ⁠   / \
 * ⁠  4   5
 * ⁠ / \   \ 
 * ⁠1   3   1
 * 
 * Output: 9
 * Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
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
     * 背包问题变形，动态规划，一个问题是如果常规iteration dp，坐标系很难建立，因为是数型，不是线或平面。
     * 所以想到了递归的办法，自底向上计算并返回上层动态规划表，同时更新maxVal，返回child的值有两部分：rob child的最大val，不rob child的最大val。本层是否rob及最大val计算的逻辑比较复杂，好好想想
     * 时间复杂度 O(n)
     * Your runtime beats 91.53 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.2 MB)
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        dfs(root);
        return maxVal;
    }
    private int maxVal = 0;
    // return int[2]: int[0] not take child; int[1] take child
    public int[] dfs(TreeNode root) {
        // two things: 1.return dp to parents 2.fresh maxVal
        if (root == null)   return new int[]{0,0};
        int[] lchild = dfs(root.left);
        int[] rchild = dfs(root.right);
        int notRobMax = Math.max(lchild[0], lchild[1]) 
                        + Math.max(rchild[0], rchild[1]);
        int robMax = lchild[0] + rchild[0] + root.val;
        maxVal = Math.max(Math.max(notRobMax, robMax), maxVal);
        return new int[]{notRobMax, robMax};
    }
}

