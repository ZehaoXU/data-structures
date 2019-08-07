import sun.security.util.Length;

/*
 * @lc app=leetcode id=74 lang=java
 *
 * [74] Search a 2D Matrix
 *
 * https://leetcode.com/problems/search-a-2d-matrix/description/
 *
 * algorithms
 * Medium (35.08%)
 * Likes:    930
 * Dislikes: 110
 * Total Accepted:    238.5K
 * Total Submissions: 679.8K
 * Testcase Example:  '[[1,3,5,7],[10,11,16,20],[23,30,34,50]]\n3'
 *
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 
 * 
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the
 * previous row.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * matrix = [
 * ⁠ [1,   3,  5,  7],
 * ⁠ [10, 11, 16, 20],
 * ⁠ [23, 30, 34, 50]
 * ]
 * target = 3
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * matrix = [
 * ⁠ [1,   3,  5,  7],
 * ⁠ [10, 11, 16, 20],
 * ⁠ [23, 30, 34, 50]
 * ]
 * target = 13
 * Output: false
 * 
 */
class Solution {
    /**
     * bug free 感动
     * 二分法，由于同行元素递增，下一行都大于上一行，所以可以当作1维来做（Z字遍历其实就是递增一维数组）
     * 先找小于等于他的最大数开头的行，再在那一行找lowerbound，然后看是不是target。找行时需要注意，mid要偏向r，并且r=m-1, l=m
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.13 % of java submissions (41.8 MB)
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows == 0)  return false;
        int cols = matrix[0].length;
        if (cols == 0)  return false;

        if (target < matrix[0][0] 
            || target > matrix[rows-1][cols-1]) return false;

        int up = 0, down = rows-1;
        while (up < down) {
            int m = up + (down-up+1)/2;
            if (matrix[m][0] > target) {
                down = m - 1;
            }
            else { up = m; }
        }
        
        int l = 0, r = cols-1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (matrix[up][m] >= target) {
                r = m;
            }
            else { l = m+1; }
        }

        if (matrix[up][l] != target)    return false;
        return true;
    }
}

