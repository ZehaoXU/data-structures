/*
 * @lc app=leetcode id=69 lang=java
 *
 * [69] Sqrt(x)
 *
 * https://leetcode.com/problems/sqrtx/description/
 *
 * algorithms
 * Easy (31.80%)
 * Likes:    835
 * Dislikes: 1454
 * Total Accepted:    397.1K
 * Total Submissions: 1.2M
 * Testcase Example:  '4'
 *
 * Implement int sqrt(int x).
 * 
 * Compute and return the square root of x, where x is guaranteed to be a
 * non-negative integer.
 * 
 * Since the return type is an integer, the decimal digits are truncated and
 * only the integer part of the result is returned.
 * 
 * Example 1:
 * 
 * 
 * Input: 4
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 8
 * Output: 2
 * Explanation: The square root of 8 is 2.82842..., and since 
 * the decimal part is truncated, 2 is returned.
 * 
 * 
 */
class Solution {
    /**
     * 二分查找，小心溢出！故 r > x/r; l +(r-l+1)/2
     * 时间复杂度　O(logn); 空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.39 % of java submissions (33.8 MB)
     * 
     * 注意：二分法右边开/闭，退出条件，中值判断条件等
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        int l = 1, r = x;
        // []
        while (l < r) {
            int m = l + (r-l+1)/2;
            if (m > x/m) {
                r = m-1;
            }
            // 
            else {
                l = m;
            }
        }
        return l;
    }
}

