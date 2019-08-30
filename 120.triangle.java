/*
 * @lc app=leetcode id=120 lang=java
 *
 * [120] Triangle
 *
 * https://leetcode.com/problems/triangle/description/
 *
 * algorithms
 * Medium (40.32%)
 * Likes:    1276
 * Dislikes: 146
 * Total Accepted:    196.3K
 * Total Submissions: 484.6K
 * Testcase Example:  '[[2],[3,4],[6,5,7],[4,1,8,3]]'
 *
 * Given a triangle, find the minimum path sum from top to bottom. Each step
 * you may move to adjacent numbers on the row below.
 * 
 * For example, given the following triangle
 * 
 * 
 * [
 * ⁠    [2],
 * ⁠   [3,4],
 * ⁠  [6,5,7],
 * ⁠ [4,1,8,3]
 * ]
 * 
 * 
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 * 
 * Note:
 * 
 * Bonus point if you are able to do this using only O(n) extra space, where n
 * is the total number of rows in the triangle.
 * 
 */
class Solution {
    /**
     * 2d dp，Q62同系列，从上往下走，dp[i][j]依赖两个状态 dp[i-1][j-1] and dp[i-1][j]，然后两个状态取最小就是转换方程。
     * 这里我们对于两边的元素进行了处理（两边添加边界），这样两端元素不用特判，可以同一转移。同时压缩了空间，因为每层结果只和上一层有关
     * TC O(n^2); SC O(n)
     * Your runtime beats 82.45 % of java submissions
     * Your memory usage beats 53.06 % of java submissions (37.9 MB)
     * 
     * 优化方法！！：
     *  从下往上找！好处很多，1.不用判断两端元素，有统一的转移方程；2.最后只有一个元素就是结果，而从上往下最后一层需要找最小值
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] up = new int[]{Integer.MAX_VALUE, triangle.get(0).get(0), Integer.MAX_VALUE};

        for (int i = 1; i < n; i++) {
            int[] curr = new int[i+3];
            curr[0] = Integer.MAX_VALUE;
            curr[curr.length-1] = Integer.MAX_VALUE;
            for (int j = 1; j < i+2; j++) {
                curr[j] = triangle.get(i).get(j-1) + 
                            Math.min(up[j-1], up[j]);
            }
            up = curr;
        }

        int res = Integer.MAX_VALUE;
        for (int sum : up) {
            res = Math.min(res, sum);
        }
        return res;
    }
}

