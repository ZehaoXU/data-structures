/*
 * @lc app=leetcode id=801 lang=java
 *
 * [801] Minimum Swaps To Make Sequences Increasing
 *
 * https://leetcode.com/problems/minimum-swaps-to-make-sequences-increasing/description/
 *
 * algorithms
 * Medium (35.50%)
 * Likes:    559
 * Dislikes: 32
 * Total Accepted:    15.8K
 * Total Submissions: 44.4K
 * Testcase Example:  '[1,3,5,4]\n[1,2,3,7]'
 *
 * We have two integer sequences A and B of the same non-zero length.
 * 
 * We are allowed to swap elements A[i] and B[i].  Note that both elements are
 * in the same index position in their respective sequences.
 * 
 * At the end of some number of swaps, A and B are both strictly increasing.
 * (A sequence is strictly increasing if and only if A[0] < A[1] < A[2] < ... <
 * A[A.length - 1].)
 * 
 * Given A and B, return the minimum number of swaps to make both sequences
 * strictly increasing.  It is guaranteed that the given input always makes it
 * possible.
 * 
 * 
 * Example:
 * Input: A = [1,3,5,4], B = [1,2,3,7]
 * Output: 1
 * Explanation: 
 * Swap A[3] and B[3].  Then the sequences are:
 * A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
 * which are both strictly increasing.
 * 
 * 
 * Note:
 * 
 * 
 * A, B are arrays with the same length, and that length will be in the range
 * [1, 1000].
 * A[i], B[i] are integer values in the range [0, 2000].
 * 
 * 
 */
class Solution {
    /**
     * 记录状态的dp，每个i有两个状态：第i位keep 还是 第i位swap。故dp[i]表示到第i位最少的交换次数，且根据i是否交换有两个状态
     * TC O(n); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 50 % of java submissions (40.1 MB)
     */
    public int minSwap(int[] A, int[] B) {
        int keepPrev = 0;
        int swapPrev = 1;
        for (int i = 1; i < A.length; i++) {
            int keepNow = 0, swapNow = 0;
            if (A[i] <= A[i-1] || B[i] <= B[i-1]) {
                keepNow = swapPrev;
                swapNow = keepPrev + 1;
            }
            else if (A[i] <= B[i-1] || B[i] <= A[i-1]) {
                keepNow = keepPrev;
                swapNow = swapPrev + 1;
            }
            // either way can work
            else {
                int temp = Math.min(keepPrev, swapPrev);
                keepNow = temp;
                swapNow = temp + 1;
            }
            keepPrev = keepNow;
            swapPrev = swapNow;
        }
        return Math.min(keepPrev, swapPrev);
    }
}

