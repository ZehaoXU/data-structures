import java.lang.*;
import java.util.List;
import java.util.Map;
/*
 * @lc app=leetcode id=508 lang=java
 *
 * [508] Most Frequent Subtree Sum
 *
 * https://leetcode.com/problems/most-frequent-subtree-sum/description/
 *
 * algorithms
 * Medium (55.21%)
 * Likes:    416
 * Dislikes: 82
 * Total Accepted:    52.8K
 * Total Submissions: 95.7K
 * Testcase Example:  '[5,2,-3]'
 *
 * 
 * Given the root of a tree, you are asked to find the most frequent subtree
 * sum. The subtree sum of a node is defined as the sum of all the node values
 * formed by the subtree rooted at that node (including the node itself). So
 * what is the most frequent subtree sum value? If there is a tie, return all
 * the values with the highest frequency in any order.
 * 
 * 
 * Examples 1
 * Input:
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -3
 * 
 * return [2, -3, 4], since all the values happen only once, return all of them
 * in any order.
 * 
 * 
 * Examples 2
 * Input:
 * 
 * ⁠ 5
 * ⁠/  \
 * 2   -5
 * 
 * return [2], since 2 happens twice, however -5 only occur once.
 * 
 * 
 * Note:
 * You may assume the sum of values in any subtree is in the range of 32-bit
 * signed integer.
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
     * 从上向下包含大量重复操作，故从下到上一边保存结果一边返回上层需要的参数。用两个全局变量刷新结果
     * map包含sum到freq的映射，maxFreq用来记录目前出现的最多次数，方便最后从freq映射到sum
     * 时间复杂度 O(n); 空间复杂度 O(n)
     * Your runtime beats 84.43 % of java submissions
     * Your memory usage beats 100 % of java submissions (38.7 MB)
     * @param root
     * @return
     */
    public int[] findFrequentTreeSum(TreeNode root) {
        findFreqSum(root);
        List<Integer> list = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == maxFreq)
                list.add(key);
        }
        int[] res = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            res[i] = list.get(i);

        return res;
    }
    Map<Integer, Integer> map = new HashMap<>();
    int maxFreq = 0;
    public int findFreqSum(TreeNode root) {
        if (root == null)   return 0;
        int sum = root.val;
        sum += findFreqSum(root.left);
        sum += findFreqSum(root.right);
        map.put(sum, map.getOrDefault(sum, 0)+1);
        if (map.get(sum) > maxFreq) maxFreq = map.get(sum);
        return sum;
    }
}

