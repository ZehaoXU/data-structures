/*
 * @lc app=leetcode id=668 lang=java
 *
 * [668] Kth Smallest Number in Multiplication Table
 *
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/
 *
 * algorithms
 * Hard (42.47%)
 * Likes:    330
 * Dislikes: 19
 * Total Accepted:    13.2K
 * Total Submissions: 31K
 * Testcase Example:  '3\n3\n5'
 *
 * 
 * Nearly every one have used the Multiplication Table. But could you find out
 * the k-th smallest number quickly from the multiplication table?
 * 
 * 
 * 
 * Given the height m and the length n of a m * n Multiplication Table, and a
 * positive integer k, you need to return the k-th smallest number in this
 * table.
 * 
 * 
 * Example 1:
 * 
 * Input: m = 3, n = 3, k = 5
 * Output: 
 * Explanation: 
 * The Multiplication Table:
 * 1    2    3
 * 2    4    6
 * 3    6    9
 * 
 * The 5-th smallest number is 3 (1, 2, 2, 3, 3).
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: m = 2, n = 3, k = 6
 * Output: 
 * Explanation: 
 * The Multiplication Table:
 * 1    2    3
 * 2    4    6
 * 
 * The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The m and n will be in the range [1, 30000].
 * The k will be in the range [1, m * n]
 * 
 * 
 */
class Solution {
    /**
     * 二分搜索，和378很像，无法构建index和val的线性映射，故用range搜索，并从右上到左下搜索，计算g(m)
     * g(m)是table中小于等于m的元素个数，从右上到左下，避免了从头n^2遍历，只要O(n)即可
     * 时间复杂度 O(n*logn); 空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 33.33 % of java submissions (32.9 MB)
     * @param m
     * @param n
     * @param k
     * @return
     */
    public int findKthNumber(int m, int n, int k) {
        int l = 1, r = m*n;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (countLessEqual(m, n, mid) >= k) {
                r = mid;
            }
            else {
                l = mid+1;
            }
        }
        return l;
    }
    private int countLessEqual(int m, int n, int mid) {
        int row = 1, col = n;
        int count = 0;
        while (row <= m && col >= 1) {
            if (row*col <= mid) {
                count += col;
                row++;
            }
            else {
                col--;
            }
        }
        return count;
    }
}

