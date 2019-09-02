/*
 * @lc app=leetcode id=576 lang=java
 *
 * [576] Out of Boundary Paths
 *
 * https://leetcode.com/problems/out-of-boundary-paths/description/
 *
 * algorithms
 * Medium (32.71%)
 * Likes:    326
 * Dislikes: 111
 * Total Accepted:    19.6K
 * Total Submissions: 59.9K
 * Testcase Example:  '2\n2\n2\n0\n0'
 *
 * There is an m by n grid with a ball. Given the start coordinate (i,j) of the
 * ball, you can move the ball to adjacent cell or cross the grid boundary in
 * four directions (up, down, left, right). However, you can at most move N
 * times. Find out the number of paths to move the ball out of grid boundary.
 * The answer may be very large, return it after mod 10^9 + 7.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: m = 2, n = 2, N = 2, i = 0, j = 0
 * Output: 6
 * Explanation:
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: m = 1, n = 3, N = 3, i = 0, j = 1
 * Output: 12
 * Explanation:
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * Once you move the ball out of boundary, you cannot move it back.
 * The length and height of the grid is in range [1,50].
 * N is in range [0,50].
 * 
 * 
 */
class Solution {
    /**
     * dp,dp[k][i][j]表示从i j走k步走出边界有多少种路线，转移方程: dp[k][i][j] = dp[k-1][i-1][j] + dp[k-1][i][j-1] + 其余两个方向。因为k步之和k-1步有关，所以可以压缩空间。另外本程序内进行的初始化是不必要的
     * TC O(K*M*N); SC O(MN)
     * Your runtime beats 11.29 % of java submissions
     * Your memory usage beats 25 % of java submissions (35.6 MB)
     * 
     * 其他方法：
     *  还有一种dp是从i j开始走，记录每个点在k步走到有多少种走法，一个res记录当走出boundary时的走法数，直接返回res即可
     */
    public int findPaths(int m, int n, int N, int i, int j) {
        if (N == 0) return 0;
        // init
        int mode = 1000000007;
        int[][] dirs = {{-1,0}, {1,0}, {0,1}, {0,-1}};
        long[][] dp = new long[m][n];

        for (int k = 1; k < N; k++) {
            long[][] curr = new long[m][n];
            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    for (int[] dir : dirs) {
                        int x = c + dir[0];
                        int y = r + dir[1];
                        if (x < 0 || x >= n || y < 0 || y >= m)
                            curr[r][c]++;
                        else curr[r][c] += dp[y][x] % mode;
                    }
                    curr[i][j] %= mode;
                }
            }
            dp = curr;
        }
        long res = 0;
        for (int[] dir : dirs) {
            int x = j + dir[0];
            int y = i + dir[1];
            if (x < 0 || x >= n || y < 0 || y >= m)
                res++;
            else res += dp[y][x];
            res %= mode;
        }
        return (int) res;
    }
}

