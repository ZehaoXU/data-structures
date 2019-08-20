import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
/*
 * @lc app=leetcode id=103 lang=java
 *
 * [103] Binary Tree Zigzag Level Order Traversal
 *
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 *
 * algorithms
 * Medium (42.76%)
 * Likes:    1121
 * Dislikes: 65
 * Total Accepted:    242.1K
 * Total Submissions: 565.5K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the zigzag level order traversal of its nodes'
 * values. (ie, from left to right, then right to left for the next level and
 * alternate between).
 * 
 * 
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * 
 * ⁠   3
 * ⁠  / \
 * ⁠ 9  20
 * ⁠   /  \
 * ⁠  15   7
 * 
 * 
 * 
 * return its zigzag level order traversal as:
 * 
 * [
 * ⁠ [3],
 * ⁠ [20,9],
 * ⁠ [15,7]
 * ]
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
     * 两个栈实现真正的zigzag遍历
     * 实际上我想麻烦了，不用真正的zigzag遍历，只要常规层序遍历，在添加结果时给偶数层的层序遍历结果revert以下就行了，于是使用linkedlist作为res内层，每层交替add和addfirst即可
     * 时间复杂度 O(n); 空间复杂度 O(n)
     * Your runtime beats 94.98 % of java submissions
     * Your memory usage beats 99.04 % of java submissions (36 MB)
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Deque<TreeNode> sLR = new ArrayDeque<>();
        Deque<TreeNode> sRL = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)   return res;
        sLR.push(root);
        
        while (!sLR.isEmpty() || !sRL.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            while (!sLR.isEmpty()) {
                root = sLR.pop();
                temp.add(root.val);
                if (root.left != null)
                    sRL.push(root.left);
                if (root.right != null)
                    sRL.push(root.right);
            }
            res.add(new ArrayList<>(temp));
            temp.clear();
            while (!sRL.isEmpty()) {
                root = sRL.pop();
                temp.add(root.val);
                if (root.right != null)
                    sLR.push(root.right);
                if (root.left != null)
                    sLR.push(root.left);
            }
            if (temp.size() != 0)
                res.add(new ArrayList<>(temp));
        }

        return res;
    }
}

