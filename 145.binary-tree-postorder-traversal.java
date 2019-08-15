import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/*
 * @lc app=leetcode id=145 lang=java
 *
 * [145] Binary Tree Postorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 *
 * algorithms
 * Hard (49.52%)
 * Likes:    1016
 * Dislikes: 52
 * Total Accepted:    279.6K
 * Total Submissions: 562.6K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the postorder traversal of its nodes' values.
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
 * Output: [3,2,1]
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
     * Morris方法，根右左遍历，然后将结果倒过来，和前序遍历一样
     * 100% 100%
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        TreeNode pre = null;
        while (root != null) {
            if (root.right != null) {
                pre = root.right;
                while (pre.left != null && pre.left != root)
                    pre = pre.left;
                if (pre.left == null) { // first time
                    pre.left = root;
                    res.addFirst(root.val);
                    root = root.right;
                }
                else {
                    pre.left = null;
                    root = root.left;
                }
            }
            else { // right == null
                res.addFirst(root.val);
                root = root.left;
            }
        }

        return res;
    }
}

class SolutionIterative {
    /**
     * 非递归后序遍历，相对较难
     * Your runtime beats 63.7 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.7 MB)
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            if (root.right == null || root.right == last) {
                res.add(root.val);
                last = root;
                root = null;
                stack.pop();
            }
            else {
                root = root.right;
            }
        }
        return res;
    }
}

class SolutionRecursive {
    /**
     * 二叉树后序遍历，递归
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.7 MB)
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> res) {
        if (root == null)   return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
        return;
    }
}

