import java.util.Arrays;

/*
 * @lc app=leetcode id=741 lang=java
 *
 * [741] Cherry Pickup
 *
 * https://leetcode.com/problems/cherry-pickup/description/
 *
 * algorithms
 * Hard (30.55%)
 * Likes:    491
 * Dislikes: 38
 * Total Accepted:    12K
 * Total Submissions: 39.1K
 * Testcase Example:  '[[0,1,-1],[1,0,-1],[1,1,1]]'
 *
 * In a N x N grid representing a field of cherries, each cell is one of three
 * possible integers.
 * 
 * 
 * 
 * 
 * 0 means the cell is empty, so you can pass through;
 * 1 means the cell contains a cherry, that you can pick up and pass
 * through;
 * -1 means the cell contains a thorn that blocks your way.
 * 
 * 
 * 
 * 
 * Your task is to collect maximum number of cherries possible by following the
 * rules below:
 * 
 * 
 * 
 * 
 * Starting at the position (0, 0) and reaching (N-1, N-1) by moving right or
 * down through valid path cells (cells with value 0 or 1);
 * After reaching (N-1, N-1), returning to (0, 0) by moving left or up through
 * valid path cells;
 * When passing through a path cell containing a cherry, you pick it up and the
 * cell becomes an empty cell (0);
 * If there is no valid path between (0, 0) and (N-1, N-1), then no cherries
 * can be collected.
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: grid =
 * [[0, 1, -1],
 * ⁠[1, 0, -1],
 * ⁠[1, 1,  1]]
 * Output: 5
 * Explanation: 
 * The player started at (0, 0) and went down, down, right right to reach (2,
 * 2).
 * 4 cherries were picked up during this single trip, and the matrix becomes
 * [[0,1,-1],[0,0,-1],[0,0,0]].
 * Then, the player went left, up, up, left to return home, picking up one more
 * cherry.
 * The total number of cherries picked up is 5, and this is the maximum
 * possible.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * grid is an N by N 2D array, with 1 <= N <= 50.
 * Each grid[i][j] is an integer in the set {-1, 0, 1}.
 * It is guaranteed that grid[0][0] and grid[N-1][N-1] are not
 * -1.
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 太难了，快难吐了
     * 首先要明确从头走到尾和从尾走到头是等效的，所以从头到尾走两遍即可。很像二维dp，不过这种dp无法记录路线（走两遍是想第一遍把最大路径上全部标0，然后走第二遍，实际上这种难以实现）
     * 故这种走一遍走过路标0的做法不可取。考虑两个人同时从头出发，如果两个人在同一个格子，则只能采一遍樱桃，这样避免了归零问题，和二维dp很像了。dp[x1][y1][x2]记录状态，y2=x1+y1-x2 计算出，每个状态有4个sub problem，两个人分别从上/左来。注意边界情况 以及 值-1不能走的地区
     * TC O(n^3); SC O(n^3)
     * Your runtime beats 33.8 % of java submissions
     * Your memory usage beats 22.22 % of java submissions (46.9 MB)
     */
    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == -1 || grid[n-1][n-1] == -1)   return 0;
        int[][][] dp = new int[n+1][n+1][n+1];
        for (int[][] table : dp)
            for (int[] row : table)
                Arrays.fill(row, Integer.MIN_VALUE);
        dp[0][1][0] = 0;

        for (int x1 = 1; x1 <= n; x1++) {
            for (int y1 = 1; y1 <= n; y1++) {
                for (int x2 = Math.max(1, x1+y1-n); x2 <= n && x2 < x1+y1; x2++) {
                    int y2 = x1+y1-x2;
                    if (grid[x1-1][y1-1] == -1 || grid[x2-1][y2-1] == -1)   continue;

                    int curr = 0;
                    if (x1 == x2 && y1 == y2)   curr = grid[x1-1][y1-1];
                    else curr = grid[x1-1][y1-1] + grid[x2-1][y2-1];
                    int prev = Math.max(
                            Math.max(dp[x1-1][y1][x2-1], dp[x1-1][y1][x2]), 
                            Math.max(dp[x1][y1-1][x2-1], dp[x1][y1-1][x2]));
                    dp[x1][y1][x2] = curr + prev;
                }
            }
        }
        return dp[n][n][n] >= 0 ? dp[n][n][n] : 0;
    }
}

