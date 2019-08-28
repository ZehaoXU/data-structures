/*
 * @lc app=leetcode id=416 lang=java
 *
 * [416] Partition Equal Subset Sum
 *
 * https://leetcode.com/problems/partition-equal-subset-sum/description/
 *
 * algorithms
 * Medium (41.30%)
 * Likes:    1415
 * Dislikes: 40
 * Total Accepted:    102.9K
 * Total Submissions: 248.5K
 * Testcase Example:  '[1,5,11,5]'
 *
 * Given a non-empty array containing only positive integers, find if the array
 * can be partitioned into two subsets such that the sum of elements in both
 * subsets is equal.
 * 
 * Note:
 * 
 * 
 * Each of the array element will not exceed 100.
 * The array size will not exceed 200.
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1, 5, 11, 5]
 * 
 * Output: true
 * 
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1, 2, 3, 5]
 * 
 * Output: false
 * 
 * Explanation: The array cannot be partitioned into equal sum subsets.
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * dp, 抽象问题：dp[i]表示经历前i个数，总共能能组成的全部sum；转移公式：dp[i] = dp[i-1](not using nums[i]) + dp[i-1]+nums[i](using nums[i])
     * 隐藏的一个关键点是，只要dp[nums.length]中有totalSum/2即可。注意提前判断sum是奇数还是偶数
     * TC O(n*S) S:sum of nums; SC O(n) compressed 只和前一个状态有关
     * Your runtime beats 76.13 % of java submissions
     * Your memory usage beats 90.48 % of java submissions (37.4 MB)
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        if (sum % 2 != 0 || nums.length < 2)   return false;
        int target = sum / 2;
        // dp[i] is all the sum using nums[0-i]
        boolean[] prev = new boolean[sum+1];
        prev[0] = true;
        for (int i = 0; i < nums.length; i++) {
            boolean[] curr = prev.clone();
            for (int j = 0; j <= sum; j++) if (prev[j]==true) {
                curr[nums[i] + j] = true;
            }
            if (curr[target])   return true;
            prev = curr;
        }
        return false;
    }
}

