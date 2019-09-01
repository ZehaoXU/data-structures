/*
 * @lc app=leetcode id=1039 lang=java
 *
 * [1039] Minimum Score Triangulation of Polygon
 *
 * https://leetcode.com/problems/minimum-score-triangulation-of-polygon/description/
 *
 * algorithms
 * Medium (42.88%)
 * Likes:    195
 * Dislikes: 25
 * Total Accepted:    4.2K
 * Total Submissions: 9.9K
 * Testcase Example:  '[1,2,3]'
 *
 * Given N, consider a convex N-sided polygon with vertices labelled A[0],
 * A[i], ..., A[N-1] in clockwise order.
 * 
 * Suppose you triangulate the polygon into N-2 triangles.  For each triangle,
 * the value of that triangle is the product of the labels of the vertices, and
 * the total score of the triangulation is the sum of these values over all N-2
 * triangles in the triangulation.
 * 
 * Return the smallest possible total score that you can achieve with some
 * triangulation of the polygon.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1,2,3]
 * Output: 6
 * Explanation: The polygon is already triangulated, and the score of the only
 * triangle is 6.
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: [3,7,4,5]
 * Output: 144
 * Explanation: There are two triangulations, with possible scores: 3*7*5 +
 * 4*5*7 = 245, or 3*4*5 + 3*4*7 = 144.  The minimum score is 144.
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [1,3,1,4,1,5]
 * Output: 13
 * Explanation: The minimum score triangulation has score 1*1*3 + 1*1*4 + 1*1*5
 * + 1*1*1 = 13.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 3 <= A.length <= 50
 * 1 <= A[i] <= 100
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 分治法dp，重要前提是每条边只能出现在一个三角形里，可以以此为分治前提，选定两个顶点i j，其相连的边作为三角形一边，然后在(i, j)的范围内寻找顶点k组成三角形，并将问题分割成i-k k-j的子问题。这里仅在遍历过的顶点中寻找k，因为如果i-j和后面的顶点组成三角形，会在后面的情况中被讨论，没有遗漏
     * dp[i][j] 表示[i,j]范围内的顶点的min 三角形分割，dp[i][j] = min(dp[i][j], dp[i][k] + dp[k][j] + A[i]*A[k]*A[j])
     * TC O(n^3); SC O(n^2)
     * Your runtime beats 25.7 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.3 MB)
     * @param A
     * @return
     */
    public int minScoreTriangulation(int[] A) {
        // dp[i][j] min value using vertices i-j inclusive
        int n = A.length;
        int[][] dp = new int[n][n];
        // init dp[i][i+1] = 0, dp[i][i] = 0
        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n-len; i++) {
                int j = len + i;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i+1; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], 
                        dp[i][k] + dp[k][j] + A[i]*A[j]*A[k]);
                }
            }
        }
        return dp[0][n-1];
    }
}

