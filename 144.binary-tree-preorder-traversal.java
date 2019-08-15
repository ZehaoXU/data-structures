

/*
 * @lc app=leetcode id=144 lang=java
 *
 * [144] Binary Tree Preorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-preorder-traversal/description/
 *
 * algorithms
 * Medium (52.09%)
 * Likes:    871
 * Dislikes: 43
 * Total Accepted:    364.2K
 * Total Submissions: 697.4K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * 
 * Example:
 * 
 * 
 * Input: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * Output: [1,2,3]
 * 
 * 
 * Follow up: Recursive solution is trivial, could you do it iteratively?
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
     * Morris方法前序遍历
     * Runtime: 0 ms, faster than 100.00% of Java online submissions
     * Memory Usage: 34.8 MB, less than 100.00% of Java online submissions
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;
        TreeNode pre = null;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            }
            // curr.left != null
            else {
                pre = curr.left;
                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    res.add(curr.val);
                    pre.right = curr;
                    curr = curr.left;
                }
                // pre.right == curr, second time visit
                else {
                    pre.right = null;
                    curr = curr.right;
                }
            }
        }

        return res;
    }
}

class SolutionIterative {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root == null)   return res;
        stack.push(root);
        while (!stack.isEmpty()) {
            root = stack.pop();
            res.add(root.val);
            if (root.right != null)
                stack.push(root.right);
            if (root.left != null)
                stack.push(root.left);
        }
        return res;
    }
}

class SolutionRecursive {
    /**
     * 二叉树前序遍历，递归
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.6 MB)
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> res) {
        if (root == null)   return;
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
        return;
    }
}

