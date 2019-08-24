import java.util.Arrays;

/*
 * @lc app=leetcode id=303 lang=java
 *
 * [303] Range Sum Query - Immutable
 *
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 *
 * algorithms
 * Easy (39.17%)
 * Likes:    528
 * Dislikes: 832
 * Total Accepted:    150.3K
 * Total Submissions: 382.3K
 * Testcase Example:  '["NumArray","sumRange","sumRange","sumRange"]\n[[[-2,0,3,-5,2,-1]],[0,2],[2,5],[0,5]]'
 *
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * Example:
 * 
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * 
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 
 * 
 * 
 * Note:
 * 
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 * 
 * 
 */
class NumArray {
    /**
     * 求前缀和相当于，不过元素不会频繁update，用dp[i]存前i个元素的前缀和
     * TC build:O(n), get:O(1); TC O(n)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 65.85 % of java submissions (41.3 MB)
     * 
     * 更好的实现：前缀和计算可以直接nums在位完成，nums[i] += nums[i-1]
     */
    int[] dp;   // prefix sum
    public NumArray(int[] nums) {
        dp = new int[nums.length];
        if (nums.length != 0) {
            dp[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                dp[i] = dp[i-1] + nums[i];
            }
        }
    }
    
    public int sumRange(int i, int j) {
        if (i < 0)  i = 0;
        if (j >= dp.length) j = dp.length;
        return dp[j] - (i==0 ? 0 : dp[i-1]);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */

