import java.util.LinkedList;
import java.util.Queue;
/*
 * @lc app=leetcode id=101 lang=java
 *
 * [101] Symmetric Tree
 *
 * https://leetcode.com/problems/symmetric-tree/description/
 *
 * algorithms
 * Easy (43.96%)
 * Likes:    2505
 * Dislikes: 54
 * Total Accepted:    441.3K
 * Total Submissions: 1M
 * Testcase Example:  '[1,2,2,3,4,4,3]'
 *
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric
 * around its center).
 * 
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
 * 
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠/ \ / \
 * 3  4 4  3
 * 
 * 
 * 
 * 
 * But the following [1,2,2,null,3,null,3] is not:
 * 
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   2
 * ⁠  \   \
 * ⁠  3    3
 * 
 * 
 * 
 * 
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
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
     * Iterative, BFS遍历，一次蹦出相对位置的两个元素比较，一次添加两对元素
     * Your runtime beats 38.9 % of java submissions
     * Your memory usage beats 72.11 % of java submissions (37.9 MB)
     */
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if (root == null)   return true;
        q.add(root.left);
        q.add(root.right);
        while (!q.isEmpty()) {
            TreeNode n1 = q.poll();
            TreeNode n2 = q.poll();
            if (n1==null && n2==null)
                continue;
            if (n1 == null || n2 == null)
                return false;
            if (n1.val != n2.val)   return false;
            q.add(n1.left);
            q.add(n2.right);
            q.add(n1.right);
            q.add(n2.left);
        }
        return true;
    }
}

class SolutionRecursive {
    /**
     * 递归，注意不是相同，而是镜面
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 38.9 % of java submissions
     * Your memory usage beats 34.69 % of java submissions (39.2 MB)
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null)   return true;
        return check(root.left, root.right);
    }
    private boolean check(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        if (l.val != r.val) return false;
        return  check(l.right, r.left) && check(l.left, r.right);       
    }
}

