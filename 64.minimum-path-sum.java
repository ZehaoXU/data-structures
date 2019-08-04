/*
 * @lc app=leetcode id=64 lang=java
 *
 * [64] Minimum Path Sum
 */
class Solution {
    /**
     * dp，dp[i][j] = 到达ij位置的min path
     *  dp[0][0] = grid[0][0]
     *  dp[i][0] = grid[i][0] + dp[i-1][0], dp[0][j] = gird[0][j] + dp[0][j-1]
     *  dp[i][j] = gird[i][j] + min(dp[i-1][j], dp[i][j-1])
     * 时间复杂度 O(n^2)，空间复杂度 O(n^2)
     * Your runtime beats 89.96 % of java submissions
     * Your memory usage beats 82.6 % of java submissions (42 MB)
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        if (n == 0)   return 0;
        int m = grid[0].length;
        
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++){
            dp[0][i] = dp[0][i-1] + grid[0][i];
        }
        for (int j = 1; j < n; j++){
            dp[j][0] = dp[j-1][0] + grid[j][0];
        }
        
        // fill dp table
        for (int i = 1; i < n; i++){
            for (int j = 1; j < m; j++){
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }
        
        return dp[n-1][m-1];
    }
}

