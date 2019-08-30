/*
 * @lc app=leetcode id=63 lang=java
 *
 * [63] Unique Paths II
 *
 * https://leetcode.com/problems/unique-paths-ii/description/
 *
 * algorithms
 * Medium (33.62%)
 * Likes:    972
 * Dislikes: 158
 * Total Accepted:    219.5K
 * Total Submissions: 652.6K
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in
 * the diagram below).
 * 
 * The robot can only move either down or right at any point in time. The robot
 * is trying to reach the bottom-right corner of the grid (marked 'Finish' in
 * the diagram below).
 * 
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * 
 * 
 * 
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * 
 * Note: m and n will be at most 100.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * Output: 2
 * Explanation:
 * There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * 
 * 
 */
class Solution {
    /**
     * dp, 和62很像，判断一下[i,j]如果是1，dp[i][j] = 0，否则正常计算
     * 仅用一位数组记录下层结果，压缩空间
     * TC O(mn); SC O(n) compressed
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 90.77 % of java submissions (38.5 MB)
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // suppose m is smaller
        int[] down = new int[n+1];
        down[n-1] = 1;
        for (int i = m-1; i >= 0; i--) {
            int[] curr = new int[n+1];
            for (int j = n-1; j >= 0; j--) {
                if (obstacleGrid[i][j] != 1) {
                    curr[j] = curr[j+1] + down[j];
                }
            }
            down = curr;
        }
        return down[0];
    }
}

