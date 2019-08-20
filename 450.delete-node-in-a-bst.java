/*
 * @lc app=leetcode id=450 lang=java
 *
 * [450] Delete Node in a BST
 *
 * https://leetcode.com/problems/delete-node-in-a-bst/description/
 *
 * algorithms
 * Medium (40.47%)
 * Likes:    1026
 * Dislikes: 63
 * Total Accepted:    73.4K
 * Total Submissions: 181.3K
 * Testcase Example:  '[5,3,6,2,4,null,7]\n3'
 *
 * Given a root node reference of a BST and a key, delete the node with the
 * given key in the BST. Return the root node reference (possibly updated) of
 * the BST.
 * 
 * Basically, the deletion can be divided into two stages:
 * 
 * Search for a node to remove.
 * If the node is found, delete the node.
 * 
 * 
 * 
 * Note: Time complexity should be O(height of tree).
 * 
 * Example:
 * 
 * root = [5,3,6,2,4,null,7]
 * key = 3
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 3   6
 * ⁠/ \   \
 * 2   4   7
 * 
 * Given key to delete is 3. So we find the node with value 3 and delete it.
 * 
 * One valid answer is [5,4,6,2,null,null,7], shown in the following BST.
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 4   6
 * ⁠/     \
 * 2       7
 * 
 * Another valid answer is [5,2,6,null,4,null,7].
 * 
 * ⁠   5
 * ⁠  / \
 * ⁠ 2   6
 * ⁠  \   \
 * ⁠   4   7
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
     * 二叉树删除。没必要使用dummy node，因为只要用一个res接返回Tree Node即可。
     * 寻找：不断向左右子树寻找，找到root.val==val时开始进行删除操作。
     * 删除：分三种情况 叶子节点，只有一个子树，有两个子树。前两种情况小心根节点被删除（实际影响不大），最后一种情况找root的predecessor或者successor，分别是左子树最右，和右子树最左
     * 时间复杂度 O(h); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (38.2 MB)
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        TreeNode res = findAndDelete(root, key);
        return res;
    }
    private TreeNode findAndDelete(TreeNode root, int target) {
        if (root == null)   return null;
        if (target > root.val) {
            root.right = findAndDelete(root.right, target);
            return root;
        }
        if (target < root.val) {
            root.left = findAndDelete(root.left, target);
            return root;
        }
        // root.val == target
        // 1.leaf
        if (root.right == null && root.left == null) {
            root = null;
        }
        // 2.only left/right subtree
        else if (root.right == null) {
            root = root.left;
        }
        else if (root.left == null) {
            root = root.right;
        }
        // 3.have both children, find the rightmost of left subtree
        else {
            TreeNode curr = root.left;
            while (curr.right != null) {
                curr = curr.right;
            }
            int val = curr.val;
            root.val = val;
            root.left = findAndDelete(root.left, val);
        }
        return root;
    } 
}

