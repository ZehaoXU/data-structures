/*
 * @lc app=leetcode id=221 lang=java
 *
 * [221] Maximal Square
 *
 * https://leetcode.com/problems/maximal-square/description/
 *
 * algorithms
 * Medium (33.77%)
 * Likes:    1521
 * Dislikes: 35
 * Total Accepted:    145.9K
 * Total Submissions: 430.5K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest square
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * 
 * Input: 
 * 
 * 1 0 1 0 0
 * 1 0 1 1 1
 * 1 1 1 1 1
 * 1 0 0 1 0
 * 
 * Output: 4
 * 
 */
class Solution {
    /**
     * dp，不太想得到的聪明做法，dp[i][j]表示i j为右下角最大正方形边长。转移公式：if matrix[i][j]==0, dp[i][j] = 0; else dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1，可以画图辅助理解
     * TC O(mn); SC O(mn)
     * Your runtime beats 95.34 % of java submissions
     * Your memory usage beats 100 % of java submissions (42.1 MB)
     * 
     * 其他方法：
     *  蛮力，O(n^5) 确定左上起始点n^2，确定len n，计算区域内元素和n^2，如果元素和==len^2则说明是正方形
     *  优化：O(n^3) 动态规划，维护元素和的前缀数组，O(1)时间求区域和
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        // dp[i][j] (i,j)be the right-bottom, maximal square can get
        int m = matrix.length;
        if (m == 0 || matrix[0].length == 0) return 0;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j]=='1' ? 1 : 0;
                    max = Math.max(dp[i][j], max);
                    continue;
                }
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1])) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }
        return max * max;
    }
}

