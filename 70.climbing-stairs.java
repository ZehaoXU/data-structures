/*
 * @lc app=leetcode id=70 lang=java
 *
 * [70] Climbing Stairs
 *
 * https://leetcode.com/problems/climbing-stairs/description/
 *
 * algorithms
 * Easy (44.79%)
 * Likes:    2545
 * Dislikes: 91
 * Total Accepted:    454.2K
 * Total Submissions: 1M
 * Testcase Example:  '2'
 *
 * You are climbing a stair case. It takes n steps to reach to the top.
 * 
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can
 * you climb to the top?
 * 
 * Note: Given n will be a positive integer.
 * 
 * Example 1:
 * 
 * 
 * Input: 2
 * Output: 2
 * Explanation: There are two ways to climb to the top.
 * 1. 1 step + 1 step
 * 2. 2 steps
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 3
 * Output: 3
 * Explanation: There are three ways to climb to the top.
 * 1. 1 step + 1 step + 1 step
 * 2. 1 step + 2 steps
 * 3. 2 steps + 1 step
 * 
 * 
 */
class Solution {
    /**
     * 线性dp，斐波那契数列，之和前两个状态有关，所以可以不用数组，用两个值来压缩空间
     * TC O(n); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.26 % of java submissions (33.1 MB)
     */
    public int climbStairs(int n) {
        int twoStep = 1;
        int oneStep = 1;
        int res = 1;
        for (int i = 2; i <= n; i++) {
              res = twoStep + oneStep;
              twoStep = oneStep;
              oneStep = res;
        }
        return res;
    }
}

