import java.util.List;

/*
 * @lc app=leetcode id=501 lang=java
 *
 * [501] Find Mode in Binary Search Tree
 *
 * https://leetcode.com/problems/find-mode-in-binary-search-tree/description/
 *
 * algorithms
 * Easy (39.95%)
 * Likes:    609
 * Dislikes: 240
 * Total Accepted:    61.5K
 * Total Submissions: 153.9K
 * Testcase Example:  '[1,null,2,2]'
 *
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the
 * most frequently occurred element) in the given BST.
 * 
 * Assume a BST is defined as follows:
 * 
 * 
 * The left subtree of a node contains only nodes with keys less than or equal
 * to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or
 * equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * 
 * 
 * For example:
 * Given BST [1,null,2,2],
 * 
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    2
 * ⁠   /
 * ⁠  2
 * 
 * 
 * 
 * 
 * return [2].
 * 
 * Note: If a tree has more than one mode, you can return them in any order.
 * 
 * Follow up: Could you do that without using any extra space? (Assume that the
 * implicit stack space incurred due to recursion does not count).
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
     * 满足follow up O(1)额外空间
     * 为遍历找众数、最大值提供了新思路，可以用map存储值和freq，这题特点是中序遍历排序，相同数字接连出现，故可以用一个prev值记录上个元素，如果相同count++，如果不同从头来过
     * 两遍遍历，第一遍找到最大值，第二遍根据最大值添加众数
     * 时间复杂度 O(n); 空间复杂度 O(1) 如果不考虑递归
     * Your runtime beats 98.76 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.1 MB)
     * @param root
     * @return
     */
    public int[] findMode(TreeNode root) {
        _res = new ArrayList<>();
        inorder(root);
        _modeCount = _maxCount;
        _count = 0;
        inorder(root);

        int[] res = new int[_res.size()];
        for (int i = 0; i < res.length; i++) 
            res[i] = _res.get(i);
        return res;
    }
    public List<Integer> _res;
    private int _count = 0;
    private int _val = 0;
    private int _maxCount = 0;
    private int _modeCount = -1;
    private void inorder(TreeNode root) {
        if (root == null)  return;
        inorder(root.left);
        visit(root.val);
        inorder(root.right);
        return;
    }
    private void visit(int val) {
        if (val == _val) {
            _count++;
        }
        else {
            _val = val;
            _count = 1;
        }
        
        if (_count > _maxCount) {
            _maxCount = _count;
        }
        if (_count == _modeCount) {
            _res.add(_val);
        }
    }
}

