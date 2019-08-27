/*
 * @lc app=leetcode id=790 lang=java
 *
 * [790] Domino and Tromino Tiling
 *
 * https://leetcode.com/problems/domino-and-tromino-tiling/description/
 *
 * algorithms
 * Medium (36.54%)
 * Likes:    269
 * Dislikes: 139
 * Total Accepted:    9.4K
 * Total Submissions: 25.6K
 * Testcase Example:  '3'
 *
 * We have two types of tiles: a 2x1 domino shape, and an "L" tromino shape.
 * These shapes may be rotated.
 * 
 * 
 * XX  <- domino
 * 
 * XX  <- "L" tromino
 * X
 * 
 * 
 * Given N, how many ways are there to tile a 2 x N board? Return your answer
 * modulo 10^9 + 7.
 * 
 * (In a tiling, every square must be covered by a tile. Two tilings are
 * different if and only if there are two 4-directionally adjacent cells on the
 * board such that exactly one of the tilings has both squares occupied by a
 * tile.)
 * 
 * 
 * 
 * Example:
 * Input: 3
 * Output: 5
 * Explanation: 
 * The five different ways are listed below, different letters indicates
 * different tiles:
 * XYZ XXZ XYY XXY XYY
 * XYZ YYZ XZZ XYY XXY
 * 
 * Note:
 * 
 * 
 * N  will be in range [1, 1000].
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 挺难的，多状态线性dp，一列有好多个状态，分析清楚每个状态，以及每个状态之间的转换关系，就不难了
     * TC O(n); SC O(1) 压缩后
     * Your runtime beats 58.82 % of java submissions
     * Your memory usage beats 25 % of java submissions (33.4 MB)
     * @param N
     * @return
     */
    public int numTilings(int N) {
        int mode = 1000000007;
        // 0:filled, 1:top filled, 2:bottom filled
        long[] towStepBefore = new long[] {1,0,0};
        long[] oneStepBefore = new long[] {1,0,0};
        for (int i = 2; i <= N; i++) {
            long[] curr = new long[3];
            curr[0] = (oneStepBefore[0] + oneStepBefore[1] + oneStepBefore[2] + towStepBefore[0]) % mode;
            curr[1] = (towStepBefore[0] + oneStepBefore[2])%mode;
            curr[2] = (towStepBefore[0] + oneStepBefore[1])%mode;
            towStepBefore = oneStepBefore;
            oneStepBefore = curr;
        }
        return (int) oneStepBefore[0];
    }
}

