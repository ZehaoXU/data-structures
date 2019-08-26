/*
 * @lc app=leetcode id=213 lang=java
 *
 * [213] House Robber II
 *
 * https://leetcode.com/problems/house-robber-ii/description/
 *
 * algorithms
 * Medium (35.52%)
 * Likes:    1000
 * Dislikes: 35
 * Total Accepted:    126.5K
 * Total Submissions: 356K
 * Testcase Example:  '[2,3,2]'
 *
 * You are a professional robber planning to rob houses along a street. Each
 * house has a certain amount of money stashed. All houses at this place are
 * arranged in a circle. That means the first house is the neighbor of the last
 * one. Meanwhile, adjacent houses have security system connected and it will
 * automatically contact the police if two adjacent houses were broken into on
 * the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of
 * each house, determine the maximum amount of money you can rob tonight
 * without alerting the police.
 * 
 * Example 1:
 * 
 * 
 * Input: [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money
 * = 2),
 * because they are adjacent houses.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money =
 * 3).
 * Total amount you can rob = 1 + 3 = 4.
 * 
 */
class Solution {
    /**
     * 用两个状态来表示，抢或不抢，空间可以压缩为O(1)，需要解决的问题就是头尾相连，解决办法是 分别计算抢第一个房子 不抢最后一个房子、不抢第一个房子的最大值 可以抢最后一个房子 的最大值，然后取大者即可
     * TC (n); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.3 MB)
     * 
     * 最佳实践：用一个函数，计算从i-j抢房子的最大val，不按抢不抢第一间房分两类，而按照抢劫范围包不包含第一间分两类
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 0 || nums==null) return 0;
        // rob the first house and not rob the last house
        int rob = nums[0];
        int notRob = 0;
        for (int i = 1; i < nums.length-1; i++) {
            int temp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(temp, notRob);
        }
        int robMax = Math.max(rob, notRob);
        // not rob the first
        rob = 0;
        notRob = 0;
        for (int i = 1; i < nums.length; i++) {
            int temp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(temp, notRob);
        }
        int notRobMax = Math.max(rob, notRob);

        return Math.max(robMax, notRobMax);
    }
}

