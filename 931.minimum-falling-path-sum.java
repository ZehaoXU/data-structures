/*
 * @lc app=leetcode id=931 lang=java
 *
 * [931] Minimum Falling Path Sum
 *
 * https://leetcode.com/problems/minimum-falling-path-sum/description/
 *
 * algorithms
 * Medium (59.19%)
 * Likes:    318
 * Dislikes: 34
 * Total Accepted:    23.3K
 * Total Submissions: 39.3K
 * Testcase Example:  '[[1,2,3],[4,5,6],[7,8,9]]'
 *
 * Given a square array of integers A, we want the minimum sum of a falling
 * path through A.
 * 
 * A falling path starts at any element in the first row, and chooses one
 * element from each row.  The next row's choice must be in a column that is
 * different from the previous row's column by at most one.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,2,3],[4,5,6],[7,8,9]]
 * Output: 12
 * Explanation: 
 * The possible falling paths are:
 * 
 * 
 * 
 * [1,4,7], [1,4,8], [1,5,7], [1,5,8], [1,5,9]
 * [2,4,7], [2,4,8], [2,5,7], [2,5,8], [2,5,9], [2,6,8], [2,6,9]
 * [3,5,7], [3,5,8], [3,5,9], [3,6,8], [3,6,9]
 * 
 * 
 * The falling path with the smallest sum is [1,4,7], so the answer is 12.
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length == A[0].length <= 100
 * -100 <= A[i][j] <= 100
 * 
 */
class Solution {
    /**
     * 2d dp, Q62同类题，比Q120简单，规定走法，需要特殊处理两端的元素，加边界反而麻烦
     * TC O(n^2); SC O(n) compressed
     * Your runtime beats 88.62 % of java submissions
     * Your memory usage beats 100 % of java submissions (38.3 MB)
     * @param A
     * @return
     */
    public int minFallingPathSum(int[][] A) {
        int n = A.length;
        int[] up = A[0];
        for (int i = 1; i < n; i++) {
            int[] curr = new int[n];
            for (int j = 0; j < n; j++) {
                if (j == 0) curr[j] = A[i][j] + Math.min(up[j], up[j+1]);
                else if (j == n-1)  curr[j] = A[i][j] + Math.min(up[j], up[j-1]);
                else curr[j] = A[i][j] + Math.min(Math.min(up[j-1], up[j]), up[j+1]);
            }
            up = curr;
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            res = Math.min(res, up[i]);
        }
        return res;
    }
}

