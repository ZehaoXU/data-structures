import java.util.Arrays;

/*
 * @lc app=leetcode id=322 lang=java
 *
 * [322] Coin Change
 *
 * https://leetcode.com/problems/coin-change/description/
 *
 * algorithms
 * Medium (31.40%)
 * Likes:    2116
 * Dislikes: 82
 * Total Accepted:    231.3K
 * Total Submissions: 733K
 * Testcase Example:  '[1,2,5]\n11'
 *
 * You are given coins of different denominations and a total amount of money
 * amount. Write a function to compute the fewest number of coins that you need
 * to make up that amount. If that amount of money cannot be made up by any
 * combination of the coins, return -1.
 * 
 * Example 1:
 * 
 * 
 * Input: coins = [1, 2, 5], amount = 11
 * Output: 3 
 * Explanation: 11 = 5 + 5 + 1
 * 
 * Example 2:
 * 
 * 
 * Input: coins = [2], amount = 3
 * Output: -1
 * 
 * 
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 * 
 */
class Solution {
    /**
     * dp，每个子问题和之前C个状态有关（C是coins个数），从递归得到dp的转换方程，dp[i] 表示最小的组成i的硬币数
     * TC O(n*C); SC O(n)
     * Your runtime beats 61.31 % of java submissions
     * Your memory usage beats 98.82 % of java submissions (35.7 MB)
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) return -1;
        // dp[i] fewest number of coins to combine i
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {      
            for (int coin : coins) {
                if (i >= coin && dp[i-coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i-coin]+1);
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE ? -1 : dp[amount];
    }
}

