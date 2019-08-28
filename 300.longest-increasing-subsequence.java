import java.util.Arrays;

/*
 * @lc app=leetcode id=300 lang=java
 *
 * [300] Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (41.21%)
 * Likes:    2865
 * Dislikes: 68
 * Total Accepted:    251.1K
 * Total Submissions: 608.2K
 * Testcase Example:  '[10,9,2,5,3,7,101,18]'
 *
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 * 
 * Example:
 * 
 * 
 * Input: [10,9,2,5,3,7,101,18]
 * Output: 4 
 * Explanation: The longest increasing subsequence is [2,3,7,101], therefore
 * the length is 4. 
 * 
 * Note: 
 * 
 * 
 * There may be more than one LIS combination, it is only necessary for you to
 * return the length.
 * Your algorithm should run in O(n^2) complexity.
 * 
 * 
 * Follow up: Could you improve it to O(n log n) time complexity?
 * 
 */
class SolutionDP {
    /**
     * DP，dp[i]表示i结尾的最长子序列长度，状态转移关系dp[i]=max(dp[j<i] if nums[i] > nums[j])+1
     * TC O(n^2); SC O(n)
     * Your runtime beats 62.65 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.3 MB)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0 || nums == null)   return 0;
        int max = 1;
        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j]+1);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}

class Solution {
    /**
     * dp with binary search，遍历的时候维护一个dp，存放递增子序列LIS，用BS找插入位置，存一个size表示目前的最长，仅仅关注插入位置==size的，此时表示子序列边长，size++
     * TC O(nlong); SC O(n)
     * Your runtime beats 70.51 % of java submissions
     * Your memory usage beats 45 % of java submissions (36.9 MB)
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0 || nums == null)   return 0;
        int[] dp = new int[nums.length];
        int size = 0;
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int num : nums) {
            int l = 0, r = nums.length;
            while (l < r) {
                int m = l + (r-l)/2;
                if (dp[m] >= num)
                    r = m;
                else    l = m+1;
            }
            dp[l] = num;
            if (l == size)  size++;
        }
        return size;
    }
}

