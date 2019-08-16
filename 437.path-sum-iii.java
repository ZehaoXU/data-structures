import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=437 lang=java
 *
 * [437] Path Sum III
 *
 * https://leetcode.com/problems/path-sum-iii/description/
 *
 * algorithms
 * Easy (43.45%)
 * Likes:    2077
 * Dislikes: 113
 * Total Accepted:    117.9K
 * Total Submissions: 271.3K
 * Testcase Example:  '[10,5,-3,3,2,null,11,3,-2,null,1]\n8'
 *
 * You are given a binary tree in which each node contains an integer value.
 * 
 * Find the number of paths that sum to a given value.
 * 
 * The path does not need to start or end at the root or a leaf, but it must go
 * downwards
 * (traveling only from parent nodes to child nodes).
 * 
 * The tree has no more than 1,000 nodes and the values are in the range
 * -1,000,000 to 1,000,000.
 * 
 * Example:
 * 
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * 
 * ⁠     10
 * ⁠    /  \
 * ⁠   5   -3
 * ⁠  / \    \
 * ⁠ 3   2   11
 * ⁠/ \   \
 * 3  -2   1
 * 
 * Return 3. The paths that sum to 8 are:
 * 
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3. -3 -> 11
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
     * prefix sum, 从根到叶，维护一个走过路径的prefix sum[i]，对每个节点j，寻找 prefixSum = sum[j]-target，这表明i->j路线和是target(sum[j]-sum[i]=target)，寻找i的出现次数即可
     * 用map prefixSum -> freq 来记录走过路线上的前缀和，因为二叉树不是线性的，所以在计算完该节点和他的左右孩子后，需要恢复到该节点parent的状态，方便从parent再遍历右子树/返回parent的parent
     * 时间复杂度　O(n); 空间复杂度 O(n)
     * Your runtime beats 94.41 % of java submissions
     * Your memory usage beats 86.36 % of java submissions (39.3 MB)
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        count = 0;
        map = new HashMap<>();
        map.put(0, 1);
        pathSum(root, 0, sum);
        return count;
    } 
    int count;
    Map<Integer, Integer> map;  // sum -> freq
    public void pathSum(TreeNode root, int currSum, int target) {
        if (root == null)   return;
        currSum += root.val;
        count += map.getOrDefault(currSum-target, 0);
        map.put(currSum, map.getOrDefault(currSum, 0)+1);
        pathSum(root.left, currSum, target);
        pathSum(root.right, currSum, target);
        // 返回parent前，撤回该节点对map的改动，回到parent时的map状态
        map.put(currSum, map.get(currSum)-1);
        return;
    }
}

class SolutionRecursive {
    /**
     * 递归的核心是：明白每个函数的功能，并相信他们能够完成任务，不必追求其中的细节
     * DFS递归，两层递归，一层找不同的起点，另一层找该起点有多少条路
     * 时间复杂度 O(n^2)
     * Your runtime beats 69.37 % of java submissions
     * Your memory usage beats 75 % of java submissions (40.2 MB)
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        if (root == null)   return 0;
        return pathSum(root.left, sum) + pathSum(root.right, sum) + pathSumFrom(root, sum);
    }
    public int pathSumFrom(TreeNode root, int sum) {
        if (root == null)   return 0;
        int count = 0;
        sum -= root.val;
        if (sum == 0)   count++;
        return count + pathSumFrom(root.left, sum) + pathSumFrom(root.right, sum);
    }
}

