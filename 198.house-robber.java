/*
 * @lc app=leetcode id=198 lang=java
 *
 * [198] House Robber
 *
 * https://leetcode.com/problems/house-robber/description/
 *
 * algorithms
 * Easy (41.22%)
 * Likes:    2895
 * Dislikes: 91
 * Total Accepted:    356.1K
 * Total Submissions: 863.5K
 * Testcase Example:  '[1,2,3,1]'
 *
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed, the only constraint stopping
 * you from robbing each of them is that adjacent houses have security system
 * connected and it will automatically contact the police if two adjacent
 * houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money =
 * 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 * Example 2:
 * 
 * 
 * Input: [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house
 * 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 * 
 * 
 */
class Solution {
    /**
     * robber系列母题，有两种方式：
     *  1.记录i间房子的两个状态 偷res[0]/不偷res[1] 的最大收益，状态转移：dp[i][0] = nums[i] + dp[i-1][1], dp[i][1] = max(dp[i-1][0], dp[i-1][1])
     *  2.记录走到i间房子能获得的最大收益，不在乎偷和不偷，状态转移：dp[i] = max(nums[i] + dp[i-2], dp[i-1])；
     * 两种不同是，第一种记录两个状态和一个前驱，第二种记录一种状态和两个前驱
     * TC O(n); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.2 MB)
     * 
     * 最佳实践：
     *  不一定非要初始化好再建表，eg dp[0], dp[1]然后从2建表，可以给一个初始值，然后从0开始建表，保证初始值不会影响即可
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        int prev = 0;
        int curr = 0;
        for (int num : nums) {
            int temp = curr;
            curr = Math.max(curr, prev + num);
            prev = temp;
        }
        return curr;
    }
}

