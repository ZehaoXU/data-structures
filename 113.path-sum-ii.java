import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode id=113 lang=java
 *
 * [113] Path Sum II
 *
 * https://leetcode.com/problems/path-sum-ii/description/
 *
 * algorithms
 * Medium (41.73%)
 * Likes:    1006
 * Dislikes: 36
 * Total Accepted:    248.6K
 * Total Submissions: 595.7K
 * Testcase Example:  '[5,4,8,11,null,13,4,7,2,null,null,5,1]\n22'
 *
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's
 * sum equals the given sum.
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
 * ⁠/  \    / \
 * 7    2  5   1
 * 
 * 
 * Return:
 * 
 * 
 * [
 * ⁠  [5,4,11,2],
 * ⁠  [5,8,4,5]
 * ]
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
class SolutionRecursive {
    /**
     * recursive DFS，走完所有路，记录路径
     * 时间复杂度 O(2^n) 除了叶子节点外的每层都有左右两条路
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.3 MB)
     * @param root
     * @param sum
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), root, sum);
        return res;
    }
    public void dfs(List<List<Integer>> res,
                    List<Integer> route,
                    TreeNode root,
                    int sum) {
        if (root == null)   return;

        route.add(root.val);
        if (root.left==null && root.right==null) {
            if (sum != root.val)    return;
            res.add(new ArrayList<>(route));
            return;
        }
        
        if (root.left != null) {
            dfs(res, route, root.left, sum-root.val);
            route.remove(route.size()-1);
        }
        if (root.right != null) {
            dfs(res, route, root.right, sum-root.val);
            route.remove(route.size()-1);
        }
        return;
    }
}

