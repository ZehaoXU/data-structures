/*
 * @lc app=leetcode id=968 lang=java
 *
 * [968] Binary Tree Cameras
 *
 * https://leetcode.com/problems/binary-tree-cameras/description/
 *
 * algorithms
 * Hard (35.66%)
 * Likes:    314
 * Dislikes: 11
 * Total Accepted:    8.7K
 * Total Submissions: 24.3K
 * Testcase Example:  '[0,0,null,0,0]'
 *
 * Given a binary tree, we install cameras on the nodes of the tree. 
 * 
 * Each camera at a node can monitor its parent, itself, and its immediate
 * children.
 * 
 * Calculate the minimum number of cameras needed to monitor all nodes of the
 * tree.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * Input: [0,0,null,0,0]
 * Output: 1
 * Explanation: One camera is enough to monitor all nodes if placed as
 * shown.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [0,0,null,0,null,0,null,null,0]
 * Output: 2
 * Explanation: At least two cameras are needed to monitor all nodes of the
 * tree. The above image shows one of the valid configurations of camera
 * placement.
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of nodes in the given tree will be in the range [1, 1000].
 * Every node has value 0.
 * 
 * 
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
     * Greedy，from bottom to top，原则是不在叶子节点添加camera，并向上层反应child的状态，分为三种，有camera，没覆盖，覆盖了
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 88.98 % of java submissions
     * Your memory usage beats 81.82 % of java submissions (38.6 MB)
     * @param root
     * @return
     */
    public int minCameraCover(TreeNode root) {
        State rState = dfs(root);
        if (rState.equals(State.Uncoverd))
            count++;
        return count;
    }
    public enum State { Uncoverd, Coverd, Camera};

    private int count = 0;

    private State dfs(TreeNode root) {
        if (root == null)   return State.Coverd;
        State left = dfs(root.left);
        State right = dfs(root.right);

        if (left.equals(State.Uncoverd) || right.equals(State.Uncoverd)) {
            count++;
            return State.Camera;
        }
        else if (left.equals(State.Camera) || right.equals(State.Camera))
            return State.Coverd;
        else    
            return State.Uncoverd;
    }
}

