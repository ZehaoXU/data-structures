/*
 * @lc app=leetcode id=62 lang=java
 *
 * [62] Unique Paths
 *
 * https://leetcode.com/problems/unique-paths/description/
 *
 * algorithms
 * Medium (48.66%)
 * Likes:    1832
 * Dislikes: 126
 * Total Accepted:    322.9K
 * Total Submissions: 660.4K
 * Testcase Example:  '3\n2'
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * How many possible unique paths are there?
 * 
 * 
 * Above is a 7 x 3 grid. How many possible unique paths are there?
 * 
 * Note: m and n will be at most 100.
 * 
 * Example 1:
 * 
 * 
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the
 * bottom-right corner:
 * 1. Right -> Right -> Down
 * 2. Right -> Down -> Right
 * 3. Down -> Right -> Right
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 7, n = 3
 * Output: 28
 * 
 */
class Solution {
    /**
     * dp，二维table，每格记录从这里到左上角的unique path，dp[i][j] = dp[i-1][j]+dp[i][j-1]，多加两个边避免边界元素判断
     * 空间复杂度可压缩，因为i,j之和上一行元素 以及 本行前一个元素有关，所以只要存储上一层元素即可
     * TC O(mn); SC O(mn) -> compressed to O(min(m,n))
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.1 % of java submissions (33.3 MB)
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0)   return 0;
        int[][] dp = new int[m+1][n+1];
        dp[0][1] = 1;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m][n];
    }
}

