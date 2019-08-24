/*
 * @lc app=leetcode id=746 lang=java
 *
 * [746] Min Cost Climbing Stairs
 *
 * https://leetcode.com/problems/min-cost-climbing-stairs/description/
 *
 * algorithms
 * Easy (47.95%)
 * Likes:    1194
 * Dislikes: 274
 * Total Accepted:    93.9K
 * Total Submissions: 195.6K
 * Testcase Example:  '[0,0,0,0]'
 *
 * 
 * On a staircase, the i-th step has some non-negative cost cost[i] assigned (0
 * indexed).
 * 
 * Once you pay the cost, you can either climb one or two steps. You need to
 * find minimum cost to reach the top of the floor, and you can either start
 * from the step with index 0, or the step with index 1.
 * 
 * 
 * Example 1:
 * 
 * Input: cost = [10, 15, 20]
 * Output: 15
 * Explanation: Cheapest is start on cost[1], pay that cost and go to the
 * top.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * Output: 6
 * Explanation: Cheapest is start on cost[0], and only step on 1s, skipping
 * cost[3].
 * 
 * 
 * 
 * Note:
 * 
 * cost will have a length in the range [2, 1000].
 * Every cost[i] will be an integer in the range [0, 999].
 * 
 * 
 */
class Solution {
    /**
     * 线性dp，每个值之和前两个值有关，且最终求解不再list里，是n+1个元素。若每个dp值表示跳到他的min cost，则下一步必须访问cost[i]。这样无法压缩空间复杂度，必须在计算dp值的时候使用cost[i]，所以dp值定义为从i跳到下1/2步的min cost
     * TC O(n); SC O(1)
     * Your runtime beats 99.84 % of java submissions
     * Your memory usage beats 87.5 % of java submissions (38.3 MB)
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int oneStepBefore = cost[1];    // init cost[1]
        int twoStepBefore = cost[0];    // init cost[0]
        for (int i = 2; i < n; i++) {
            int temp = Math.min(oneStepBefore, twoStepBefore)+cost[i];
            twoStepBefore = oneStepBefore;
            oneStepBefore = temp;
        }
        return Math.min(oneStepBefore, twoStepBefore);
    }
}

