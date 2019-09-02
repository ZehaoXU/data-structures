/*
 * @lc app=leetcode id=84 lang=java
 *
 * [84] Largest Rectangle in Histogram
 *
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 *
 * algorithms
 * Hard (31.91%)
 * Likes:    2221
 * Dislikes: 59
 * Total Accepted:    192.2K
 * Total Submissions: 599.2K
 * Testcase Example:  '[2,1,5,6,2,3]'
 *
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 * 
 * 
 * 
 * 
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 * 
 * 
 * 
 * 
 * The largest rectangle is shown in the shaded area, which has area = 10
 * unit.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: [2,1,5,6,2,3]
 * Output: 10
 * 
 * 
 */
class Solution {
    /**
     * dp，核心思想是记录当i是区间最小值时的left index & right index，表示最近的小于heights[i]的index，每个i寻找left right时间是O(n)，故总时间O(n^2)
     * 考虑通过dp记录每个的left和right，转移方程: p=i-1, while (p > 0 && heights[p] >= heights[i]) p = left[p]，可以避免一步一步O(n)时间寻找，不过具体时间复杂度也不会算，均摊大概O(1)
     * TC O(n); SC O(n)
     * Your runtime beats 99.1 % of java submissions
     * Your memory usage beats 93.18 % of java submissions (40.3 MB)
     * 
     * 其他方法：
     *  用stack来记录左右的index
     *  divide and conquer O(nlogn) 以最短的进行分割，同时确定左右边界
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        // dp[i] = heights[i] * (r-l-1), last l/r less than heights[i]
        int n = heights.length;
        int[] left = new int[n+2]; // 1-n
        int[] right = new int[n+2]; // 1-n
        // init left
        for (int i = 2; i <= n; i++) {
            int p = i-1;
            while (p > 0 && heights[p-1] >= heights[i-1])
                p = left[p];
            left[i] = p;
        }
        // init right
        right[n] = n+1;
        for (int i = n-1; i > 0; i--) { 
            int p = i+1;
            while (p < n+1 && heights[p-1] >= heights[i-1])
                p = right[p];
            right[i] = p;
        }
        // let heights[i] be the minimum
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, heights[i] * (right[i+1] - left[i+1] - 1));
        }
        return res;
    }
}

