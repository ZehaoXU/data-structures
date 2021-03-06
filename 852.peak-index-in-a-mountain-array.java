/*
 * @lc app=leetcode id=852 lang=java
 *
 * [852] Peak Index in a Mountain Array
 *
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/description/
 *
 * algorithms
 * Easy (69.99%)
 * Likes:    320
 * Dislikes: 734
 * Total Accepted:    88.2K
 * Total Submissions: 126K
 * Testcase Example:  '[0,1,0]'
 *
 * Let's call an array A a mountain if the following properties hold:
 * 
 * 
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] <
 * A[i] > A[i+1] > ... > A[A.length - 1]
 * 
 * 
 * Given an array that is definitely a mountain, return any i such that A[0] <
 * A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 * 
 * Example 1:
 * 
 * 
 * Input: [0,1,0]
 * Output: 1
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [0,2,1,0]
 * Output: 1
 * 
 * 
 * Note:
 * 
 * 
 * 3 <= A.length <= 10000
 * 0 <= A[i] <= 10^6
 * A is a mountain, as defined above.
 * 
 * 
 */
class Solution {
    /**
     * 二分搜索，Q162简单版
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (38.3 MB)
     * @param A
     * @return
     */
    public int peakIndexInMountainArray(int[] A) {
        int l = 0, r = A.length-1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (A[m] > A[m+1]) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }
}

