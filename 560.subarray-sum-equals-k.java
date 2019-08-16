import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=560 lang=java
 *
 * [560] Subarray Sum Equals K
 *
 * https://leetcode.com/problems/subarray-sum-equals-k/description/
 *
 * algorithms
 * Medium (42.67%)
 * Likes:    2289
 * Dislikes: 63
 * Total Accepted:    125.6K
 * Total Submissions: 294.4K
 * Testcase Example:  '[1,1,1]\n2'
 *
 * Given an array of integers and an integer k, you need to find the total
 * number of continuous subarrays whose sum equals to k.
 * 
 * Example 1:
 * 
 * Input:nums = [1,1,1], k = 2
 * Output: 2
 * 
 * 
 * 
 * Note:
 * 
 * The length of the array is in range [1, 20,000].
 * The range of numbers in the array is [-1000, 1000] and the range of the
 * integer k is [-1e7, 1e7].
 * 
 * 
 * 
 */
class Solution {
    /**
     * Running prefix sum，用map维护prefix sum和frequency的映射
     * 时间复杂度 O(n); 空间复杂度 O(n)
     * Your runtime beats 37.15 % of java submissions
     * Your memory usage beats 98.91 % of java submissions (38.6 MB)
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0, currSum = 0;
        // prefix sum -> freq
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            currSum += nums[i];
            count += map.getOrDefault(currSum-k, 0);
            map.put(currSum, map.getOrDefault(currSum, 0) + 1);
        }
        return count;
    }
}

