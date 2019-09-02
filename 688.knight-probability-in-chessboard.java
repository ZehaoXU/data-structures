import java.util.Arrays;

/*
 * @lc app=leetcode id=688 lang=java
 *
 * [688] Knight Probability in Chessboard
 *
 * https://leetcode.com/problems/knight-probability-in-chessboard/description/
 *
 * algorithms
 * Medium (45.29%)
 * Likes:    441
 * Dislikes: 93
 * Total Accepted:    24.8K
 * Total Submissions: 54.8K
 * Testcase Example:  '3\n2\n0\n0'
 *
 * On an NxN chessboard, a knight starts at the r-th row and c-th column and
 * attempts to make exactly K moves. The rows and columns are 0 indexed, so the
 * top-left square is (0, 0), and the bottom-right square is (N-1, N-1).
 * 
 * A chess knight has 8 possible moves it can make, as illustrated below. Each
 * move is two squares in a cardinal direction, then one square in an
 * orthogonal direction.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Each time the knight is to move, it chooses one of eight possible moves
 * uniformly at random (even if the piece would go off the chessboard) and
 * moves there.
 * 
 * The knight continues moving until it has made exactly K moves or has moved
 * off the chessboard. Return the probability that the knight remains on the
 * board after it has stopped moving.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: 3, 2, 0, 0
 * Output: 0.0625
 * Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight
 * on the board.
 * From each of those positions, there are also two moves that will keep the
 * knight on the board.
 * The total probability the knight stays on the board is 0.0625.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * N will be between 1 and 25.
 * K will be between 0 and 100.
 * The knight always initially starts on the board.
 * 
 * 
 */
class Solution {
    /**
     * dp, dp[k][i][j]表示从i j位置走k步，不走出的概率，转移公式: dp[k][i][j] = sum(dp[k-1][i-dir][j-dir]) / 8，dirs是数组，表示坐标变换公式
     * TC O(K*N^2); SC O(K*N^2) 因为之和k-1的状态有关，所以可以压缩为 O(N^2)
     * Your runtime beats 24.31 % of java submissions
     * Your memory usage beats 42.86 % of java submissions (34 MB)
     * @param N
     * @param K
     * @param r
     * @param c
     * @return
     */
    public double knightProbability(int N, int K, int r, int c) {
        int[][] dirs = {{-2,1}, {-1,2}, {1,2}, {2,1}, 
                        {2,-1}, {1,-2}, {-1,-2}, {-2,-1}};
        double[][][] dp = new double[K+1][N][N];
        for (double[] row : dp[0]) Arrays.fill(row, 1);
        for (int k = 1; k < K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int[] dir : dirs) {
                        int x = dir[0] + i;
                        int y = dir[1] + j;
                        if (x < 0 || x >= N || y < 0 || y >= N)
                            continue;
                        dp[k][i][j] += dp[k-1][x][y];
                    }
                    dp[k][i][j] /= 8;
                }
            }
        }

        for (int[] dir : dirs) {
            int x = dir[0] + r;
            int y = dir[1] + c;
            if (x < 0 || x >= N || y < 0 || y >= N) continue;
            dp[K][r][c] += 0.125 * dp[K-1][x][y];
        }
        return dp[K][r][c];
    }
}

