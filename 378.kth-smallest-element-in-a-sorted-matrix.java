/*
 * @lc app=leetcode id=378 lang=java
 *
 * [378] Kth Smallest Element in a Sorted Matrix
 *
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
 *
 * algorithms
 * Medium (50.19%)
 * Likes:    1318
 * Dislikes: 87
 * Total Accepted:    119.6K
 * Total Submissions: 238.1K
 * Testcase Example:  '[[1,5,9],[10,11,13],[12,13,15]]\n8'
 *
 * Given a n x n matrix where each of the rows and columns are sorted in
 * ascending order, find the kth smallest element in the matrix.
 * 
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth
 * distinct element.
 * 
 * 
 * Example:
 * 
 * matrix = [
 * ⁠  [ 1,  5,  9],
 * ⁠  [10, 11, 13],
 * ⁠  [12, 13, 15]
 * ],
 * k = 8,
 * 
 * return 13.
 * 
 * 
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ n^2.
 */
class Solution {
    /**
     * 二分搜素，对range进行二分，因为无法构建一个index->val的映射。此时构建g(m)格外重要，假设最终要找的是target，当m=target-1时，小于等于m的一定<k个；当m=target+1时，小于等于m的一定>=k个；当m=target是，小于m的个数不一定，但小于等于m的个数一定>=k个，因为target可能有重复。
     * 由此，构建了g(m) -> (小于等于m的个数 >= k) 满足g的最小m就是target，经过分析可以肯定，m一定是矩阵中元素
     * 非常神奇，用range进行搜索，最终结果肯定是矩阵里元素
     * 同时，为了计算小于等于m的元素个数，从top-right走到bottom-left，这样在O(n)时间里就能算出，不用遍历矩阵 O(n^2)
     * 
     * 时间复杂度 O(logn*n)；
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 63.09 % of java submissions (44.3 MB)
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0], r = matrix[n-1][n-1];
        while (l < r) {
            int m = l + (r-l)/2;
            if (countLessEqual(matrix, m) >= k) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }

    public int countLessEqual(int[][] matrix, int num) {
        int count = 0;
        int row = 0, col = matrix.length - 1;
        while (row <= matrix.length-1 && col >= 0) {
            if (matrix[row][col] <= num) {
                count += col+1;
                row++;
            }
            else {
                col--;
            }
        }
        return count;
    }
}

