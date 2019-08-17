/*
 * @lc app=leetcode id=257 lang=java
 *
 * [257] Binary Tree Paths
 *
 * https://leetcode.com/problems/binary-tree-paths/description/
 *
 * algorithms
 * Easy (46.86%)
 * Likes:    977
 * Dislikes: 73
 * Total Accepted:    240.5K
 * Total Submissions: 513.1K
 * Testcase Example:  '[1,2,3,null,5]'
 *
 * Given a binary tree, return all root-to-leaf paths.
 * 
 * Note: A leaf is a node with no children.
 * 
 * Example:
 * 
 * 
 * Input:
 * 
 * ⁠  1
 * ⁠/   \
 * 2     3
 * ⁠\
 * ⁠ 5
 * 
 * Output: ["1->2->5", "1->3"]
 * 
 * Explanation: All root-to-leaf paths are: 1->2->5, 1->3
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
     * dfs从根到叶子遍历，因为只用一个StringBuilder增删不太容易，不确定数字是几位的，所以新建对象传递参数，这样下层对sb的操作就不会影响到上层的sb（指向不同东西）
     * 时间复杂度 O(n); 空间复杂度 O(n)
     * Your runtime beats 22.38 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.7 MB)
     * 
     * 其他方法：Iterations，用栈模拟DFS的过程
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, new StringBuilder(), res);
        return res;
    }
    public void dfs(TreeNode root, StringBuilder sb, List<String> res) {
        if (root == null)   return;

        sb.append(root.val);
        if (root.left==null && root.right==null) {
            String temp = sb.toString();
            res.add(temp);
            return;
        }
        sb.append("->");
        dfs(root.left, new StringBuilder(sb), res);
        dfs(root.right, new StringBuilder(sb), res);
        return;        
    }
}

