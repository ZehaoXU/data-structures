import java.util.Arrays;

/*
 * @lc app=leetcode id=518 lang=java
 *
 * [518] Coin Change 2
 *
 * https://leetcode.com/problems/coin-change-2/description/
 *
 * algorithms
 * Medium (43.69%)
 * Likes:    884
 * Dislikes: 37
 * Total Accepted:    53.9K
 * Total Submissions: 122.7K
 * Testcase Example:  '5\n[1,2,5]'
 *
 * You are given coins of different denominations and a total amount of money.
 * Write a function to compute the number of combinations that make up that
 * amount. You may assume that you have infinite number of each kind of
 * coin.
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: amount = 5, coins = [1, 2, 5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: amount = 10, coins = [10] 
 * Output: 1
 * 
 * 
 * 
 * 
 * Note:
 * 
 * You can assume that
 * 
 * 
 * 0 <= amount <= 5000
 * 1 <= coin <= 5000
 * the number of coins is less than 500
 * the answer is guaranteed to fit into signed 32-bit integer
 * 
 * 
 */
class Solution {
    /**
     * 无界背包问题，求的是combination，不是permutation，差别还是很大的，具体见笔记
     * 可以优化practice，dp[i][j] = dp[i][j-1] + dp[i-coins[j]][j]，可见某个状态仅和 使用j-1个coins的状态 以及 使用j个coins组成更小的amount的状态有关，所以可以压缩成一维dp：dp[i]表示本轮循环组成i的combination数，i从小到大，将子问题dp[i-coins[j]][j]解决；同时外层循环是使用的coins数从小到大，解决子问题dp[i][j-1]，每次计算dp[i] = dp[i] + dp[i-coins[j]] 实际上就是 dp[i][j] = dp[i][j-1] + dp[i-coins[j]][j]
     * TC O(mn); SC O(mn) compress to O(n)
     * Your runtime beats 13.64 % of java submissions
     * Your memory usage beats 9.3 % of java submissions (40.7 MB)
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int[][] dp = new int[amount+1][coins.length+1];
        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= amount; i++) {
            for (int j = 1; j <= coins.length; j++) {
                if (coins[j-1] > i) dp[i][j] = dp[i][j-1];
                else dp[i][j] = dp[i][j-1] + dp[i-coins[j-1]][j];
            }
        }
        return dp[amount][coins.length];
    }
}

