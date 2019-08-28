/*
 * @lc app=leetcode id=494 lang=java
 *
 * [494] Target Sum
 *
 * https://leetcode.com/problems/target-sum/description/
 *
 * algorithms
 * Medium (45.62%)
 * Likes:    1563
 * Dislikes: 70
 * Total Accepted:    108.3K
 * Total Submissions: 237.1K
 * Testcase Example:  '[1,1,1,1,1]\n3'
 *
 * 
 * You are given a list of non-negative integers, a1, a2, ..., an, and a
 * target, S. Now you have 2 symbols + and -. For each integer, you should
 * choose one from + and - as its new symbol.
 * ⁠
 * 
 * Find out how many ways to assign symbols to make sum of integers equal to
 * target S.  
 * 
 * 
 * Example 1:
 * 
 * Input: nums is [1, 1, 1, 1, 1], S is 3. 
 * Output: 5
 * Explanation: 
 * 
 * -1+1+1+1+1 = 3
 * +1-1+1+1+1 = 3
 * +1+1-1+1+1 = 3
 * +1+1+1-1+1 = 3
 * +1+1+1+1-1 = 3
 * 
 * There are 5 ways to assign symbols to make the sum of nums be target 3.
 * 
 * 
 * 
 * Note:
 * 
 * The length of the given array is positive and will not exceed 20. 
 * The sum of elements in the given array will not exceed 1000.
 * Your output answer is guaranteed to be fitted in a 32-bit integer.
 * 
 * 
 */
class Solution {
    /**
     * dp，和416差不多，在array中找和是target的情况。不同的是本次不是0-1背包，而且需要计数。dp[i][j]表示前i个数求和为j的情况数，转移方程：dp[i][j] = dp[i-1][j-nums[i]] + dp[i-1][j+nums[i]]
     * 只和前一个状态有关，所以可以压缩空间。sum范围已经告诉，故可以求，确定准确范围或者直接用2001作范围
     * TC O(n*S) S:sum of nums; SC O(S)
     * Your runtime beats 83.45 % of java submissions
     * Your memory usage beats 50 % of java submissions (36.2 MB)
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (S > sum || S < -sum)  return 0;
        int[] prev = new int[2*sum+1];
        prev[sum] = 1;
        for (int num : nums) {
            int[] curr = new int[2*sum+1];
            for (int i = 0; i < prev.length; i++) if (prev[i]!=0) {
                curr[i+num] += prev[i];
                curr[i-num] += prev[i];
            }
            prev = curr;
        }
        return prev[S+sum];
    }
}

