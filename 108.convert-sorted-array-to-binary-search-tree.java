/*
 * @lc app=leetcode id=108 lang=java
 *
 * [108] Convert Sorted Array to Binary Search Tree
 *
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 *
 * algorithms
 * Easy (52.09%)
 * Likes:    1328
 * Dislikes: 138
 * Total Accepted:    286.8K
 * Total Submissions: 550K
 * Testcase Example:  '[-10,-3,0,5,9]'
 *
 * Given an array where elements are sorted in ascending order, convert it to a
 * height balanced BST.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 * Example:
 * 
 * 
 * Given the sorted array: [-10,-3,0,5,9],
 * 
 * One possible answer is: [0,-3,9,-10,null,5], which represents the following
 * height balanced BST:
 * 
 * ⁠     0
 * ⁠    / \
 * ⁠  -3   9
 * ⁠  /   /
 * ⁠-10  5
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
     * 排好序array转化成平衡BST，想到binary search方法，每次分配几乎一样多的左右子树节点，然后逐层继续分配
     * 时间复杂度 O(n); 空间复杂度 O(logn)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.7 MB)
     * 
     * 注意：
     *  二分法左闭右开，不会处理最右边的节点，所以r一定得是length，不能是length-1
     * @param nums
     * @return
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        TreeNode root = buildTree(nums, 0, nums.length);
        return root;
    }
    private TreeNode buildTree(int[] nums, int l, int r) {
        if (l == r) return null;
        int m = l + (r-l)/2;
        TreeNode root = new TreeNode(nums[m]);
        root.left = buildTree(nums, l, m);
        root.right = buildTree(nums, m+1, r);
        return root;
    }
}

