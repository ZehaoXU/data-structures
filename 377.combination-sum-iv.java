/*
 * @lc app=leetcode id=377 lang=java
 *
 * [377] Combination Sum IV
 *
 * https://leetcode.com/problems/combination-sum-iv/description/
 *
 * algorithms
 * Medium (43.81%)
 * Likes:    890
 * Dislikes: 102
 * Total Accepted:    92.3K
 * Total Submissions: 210.6K
 * Testcase Example:  '[1,2,3]\n4'
 *
 * Given an integer array with all positive numbers and no duplicates, find the
 * number of possible combinations that add up to a positive integer target.
 * 
 * Example:
 * 
 * 
 * nums = [1, 2, 3]
 * target = 4
 * 
 * The possible combination ways are:
 * (1, 1, 1, 1)
 * (1, 1, 2)
 * (1, 2, 1)
 * (1, 3)
 * (2, 1, 1)
 * (2, 2)
 * (3, 1)
 * 
 * Note that different sequences are counted as different combinations.
 * 
 * Therefore the output is 7.
 * 
 * 
 * 
 * 
 * Follow up:
 * What if negative numbers are allowed in the given array?
 * How does it change the problem?
 * What limitation we need to add to the question to allow negative numbers?
 * 
 * Credits:
 * Special thanks to @pbrother for adding this problem and creating all test
 * cases.
 * 
 */
class Solution {
    /**
     * DP，DP[i] is number of combination to sum i
     * TC O(n*S) S is number of nums; SC O(n)
     * Your runtime beats 84.48 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.1 MB)
     * @param nums
     * @param target
     * @return
     */
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0)   return 0;
        int[] count = new int[target+1];
        count[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num)
                    count[i] += count[i-num];
            }
        }
        return count[target];
    }
}

