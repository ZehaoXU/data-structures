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
    /**
     * dp,非常难想，基本想不到，需要背诵
     * dp[k][i]表示前i个元素，分成k组 平均数的最大和。转移公式dp[k][i] = max(dp[k-1][j]+avg(i-j)) 对于i之前的每个j寻找分成k-1类的最大平均和 以及i-j的平均数 的总和。初始条件k=1时，dp[1][i]就是前i个数的平均值
     * 小技巧：求i-j的平均数，可以维护一个前缀数组，sum[i]表示前i个数的和
     * TC O(K*n^2); SC O(K*n)   因为k层之和k-1层有关，所以可以压缩到n
     * Your runtime beats 77.44 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.7 MB)
     * @param A
     * @param K
     * @return
     */
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

