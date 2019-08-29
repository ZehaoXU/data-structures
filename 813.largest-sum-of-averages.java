/*
 * @lc app=leetcode id=813 lang=java
 *
 * [813] Largest Sum of Averages
 *
 * https://leetcode.com/problems/largest-sum-of-averages/description/
 *
 * algorithms
 * Medium (46.19%)
 * Likes:    593
 * Dislikes: 21
 * Total Accepted:    15.3K
 * Total Submissions: 33.2K
 * Testcase Example:  '[9,1,2,3,9]\n3'
 *
 * We partition a row of numbers A into at most K adjacent (non-empty) groups,
 * then our score is the sum of the average of each group. What is the largest
 * score we can achieve?
 * 
 * Note that our partition must use every number in A, and that scores are not
 * necessarily integers.
 * 
 * 
 * Example:
 * Input: 
 * A = [9,1,2,3,9]
 * K = 3
 * Output: 20
 * Explanation: 
 * The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9
 * + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example.
 * That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 * 
 * 
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 * 
 * 
 */
class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        double[][] dp = new double[K+1][A.length];
        double[] sum = new double[A.length];
        sum[0] = A[0];
        dp[1][0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sum[i] = sum[i-1] + A[i];
            dp[1][i] = sum[i] / (i+1);
        }
        // fill dp table
        for (int k = 2; k <= K; k++) {
            for (int j = k-1; j < A.length; j++) {
                for (int i = k-2; i < j; i++) {
                    dp[k][j] = Math.max(dp[k][j], dp[k-1][i] + (sum[j]-sum[i])/(j-i));
                }
            }
        }
        return dp[K][A.length-1];
    }
}

