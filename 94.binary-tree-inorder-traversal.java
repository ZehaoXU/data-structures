

/*
 * @lc app=leetcode id=94 lang=java
 *
 * [94] Binary Tree Inorder Traversal
 *
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 *
 * algorithms
 * Medium (57.69%)
 * Likes:    1827
 * Dislikes: 78
 * Total Accepted:    504.4K
 * Total Submissions: 871.7K
 * Testcase Example:  '[1,null,2,3]'
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.
 * 
 * Example:
 * 
 * 
 * Input: [1,null,2,3]
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  3
 * 
 * Output: [1,3,2]
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
     * Morris算法，不用栈，O(1)空间遍历树
     * 算法描述：
     *  如果left tree=null，则添加该节点，并继续搜索右子树
     *  如果left tree!=null，则寻找该节点左子树的最右节点，令root成为他的右孩子（根据遍历顺序）
     *  有两种具体实现，一种破坏树结构，右孩子连到root后，令root左孩子=null；另一种不破坏树结构，寻找两次左子树的最右节点，分右孩子=null or root两种情况讨论
     * 时间复杂度 O(n)；空间复杂度 O(n) res，额外空间O(1)
     * Runtime: 0 ms, faster than 100.00% of Java online submissions
     * Memory Usage: 34.9 MB, less than 100.00% of Java online submissions
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)   return res;
        TreeNode pre = null;
        while (root != null) {
            if (root.left != null) {
                pre = root;
                root = root.left;
                while (root.right != null && root.right != pre) {
                    root = root.right;
                }
                if (root.right == null) {
                    root.right = pre;
                    root = pre.left;
                }
                // root.right == pre
                else {
                    res.add(pre.val);
                    root = pre.right;
                }
                
            }
            else {
                res.add(root.val);
                root = root.right;
            }
        }
        return res;
    }
}

class SolutionIteration {
    /**
     * 非递归中序遍历，用栈实现
     * 时间复杂度 O(n)；空间复杂度 O(h)
     * Runtime: 1 ms, faster than 56.75% of Java submissions
     * Memory Usage: 34.8 MB, less than 100.00% of Java submission
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)   return res;
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}

class SolutionRecursive {
    /**
     * 递归中序遍历
     * 时间复杂度 O(N); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.9 MB)
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) return;
        helper(root.left, res);
        res.add(root.val);
        helper(root.right, res);
        return;
    }
}

