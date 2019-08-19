/*
 * @lc app=leetcode id=98 lang=java
 *
 * [98] Validate Binary Search Tree
 *
 * https://leetcode.com/problems/validate-binary-search-tree/description/
 *
 * algorithms
 * Medium (26.11%)
 * Likes:    2219
 * Dislikes: 333
 * Total Accepted:    448K
 * Total Submissions: 1.7M
 * Testcase Example:  '[2,1,3]'
 *
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * 
 * Assume a BST is defined as follows:
 * 
 * 
 * The left subtree of a node contains only nodes with keys less than the
 * node's key.
 * The right subtree of a node contains only nodes with keys greater than the
 * node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * ⁠   2
 * ⁠  / \
 * ⁠ 1   3
 * 
 * Input: [2,1,3]
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 1   4
 * / \
 * 3   6
 * 
 * Input: [5,1,4,null,null,3,6]
 * Output: false
 * Explanation: The root node's value is 5 but its right child's value is 4.
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
     * 不能比较左右节点和根val的大小，因为他是递归的，不仅要和直接parent满足关系，还得和grandparent满足关系。
     * 所以只能用上下限递归。注意node.val可能是MAX_VALUE & MIN_VALUE，所以一开始不能用最大值 最小值定义上下界，而用null表示上下界还未定义，同时形参类型变成Integer
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 80.47 % of java submissions (39.3 MB)
     * 
     * 其他方法：
     *  inorder traverse返回一个有序array，可以直接在遍历时判断值是否大于前面的
     *  iterative method，利用栈存储node/upper/lower来模拟递归
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null)   return true;
        // preorder traverse
        if ((max != null && root.val >= max) 
            || (min != null && root.val <= min))   return false;

        boolean isLeft = isValidBST(root.left, min, root.val);
        boolean isRight = isValidBST(root.right, root.val, max);
        return isLeft && isRight;
    }
}

