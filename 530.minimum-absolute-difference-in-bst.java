/*
 * @lc app=leetcode id=530 lang=java
 *
 * [530] Minimum Absolute Difference in BST
 *
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/description/
 *
 * algorithms
 * Easy (51.20%)
 * Likes:    576
 * Dislikes: 41
 * Total Accepted:    65.1K
 * Total Submissions: 127.1K
 * Testcase Example:  '[1,null,3,2]'
 *
 * Given a binary search tree with non-negative values, find the minimum
 * absolute difference between values of any two nodes.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠   \
 * ⁠    3
 * ⁠   /
 * ⁠  2
 * 
 * Output:
 * 1
 * 
 * Explanation:
 * The minimum absolute difference is 1, which is the difference between 2 and
 * 1 (or between 2 and 3).
 * 
 * 
 * 
 * 
 * Note: There are at least two nodes in this BST.
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
     * 找BST上元素的最小差值，一开始想比较根和左子树rightMots以及右子树leftMost，后来发现自顶向下太复杂，自底向上又不好一起表示。
     * BST时刻想到中序遍历数字有序的特点，于是中序遍历一边，临近数字相减更新res即可
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 35.36 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.6 MB)
     * @param root
     * @return
     */
    public int getMinimumDifference(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        dfs(root, list);
        return res;
    }
    private int res = Integer.MAX_VALUE;
    private void dfs(TreeNode root, List<Integer> list) {
        if (root == null)   return;
        dfs(root.left, list);
        if (list.size() != 0)
            if (root.val - list.get(list.size()-1) < res)
                res = root.val - list.get(list.size()-1);
        list.add(root.val);
        dfs(root.right, list);
        return;
    }
}

