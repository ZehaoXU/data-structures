import java.util.Arrays;

/*
 * @lc app=leetcode id=85 lang=java
 *
 * [85] Maximal Rectangle
 *
 * https://leetcode.com/problems/maximal-rectangle/description/
 *
 * algorithms
 * Hard (34.21%)
 * Likes:    1668
 * Dislikes: 54
 * Total Accepted:    132.9K
 * Total Submissions: 386.7K
 * Testcase Example:  '[["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]'
 *
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle
 * containing only 1's and return its area.
 * 
 * Example:
 * 
 * 
 * Input:
 * [
 * ⁠ ["1","0","1","0","0"],
 * ⁠ ["1","0","1","1","1"],
 * ⁠ ["1","1","1","1","1"],
 * ⁠ ["1","0","0","1","0"]
 * ]
 * Output: 6
 * 
 * 
 */
class Solution {
    /**
     * dp，相当于多层的Q84 直方图，分层dp维护一个heights数组，每层用stack记录左右边界，TC O(mn)
     * 本次没有用stack处理每层的直方图，而是用left[i]表示i最左边大于等于height[i]的元素，right表示i最右边大于等于height[i]的元素，转移公式：left[i] = max(oldLeft[i], currLeft)，currLeft表示最前面不为0的元素，因为如果下层都是1，那么left不变，相当于高度都加了1，不过如果下层有的是0，则left就要根据0出现的位置变化。如果该位置是0，则left[i] = 0，这里不太好理解； 同理 right[i] = min(oldRight[i], currRight)
     * TC O(mn); SC O(n)
     * Your runtime beats 96.67 % of java submissions
     * Your memory usage beats 100 % of java submissions (40.9 MB)
     * 
     * 其他方法：
     *  O(m*n^2) 每层直方图然后每个i找left right的方法应该是能想到的
     *  O(m^2*n) dp[i][j]表示i行，j列结尾的最长1的个数，得到table后再遍历一遍，寻找每个i,j为右上角的最大矩形
     * @param matrix
     * @return
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)    return 0;
        int m = matrix.length, n = matrix[0].length;
        int[] height = new int[n];
        int[] left = new int[n], right = new int[n];
        Arrays.fill(right, n-1);
        int res = 0;
        for (int i = 0; i < m; i++) {
            // update height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')    height[j]++;
                else height[j] = 0;
            }
            // update left
            int currLeft = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '0') {
                    left[j] = 0;
                    currLeft = j+1;
                }
                else left[j] = Math.max(left[j], currLeft);
            }
            // update right
            int currRight = n-1;
            for (int j = n-1; j >= 0; j--) {
                if (matrix[i][j] == '0') {
                    right[j] = n-1;
                    currRight = j-1;
                }
                else right[j] = Math.min(right[j], currRight);
            }
            // maximal rect
            for (int j = 0; j < n; j++) if (height[j] > 0)
                res = Math.max(res, height[j]*(right[j] - left[j] + 1));
        }
        return res;
    }
}

