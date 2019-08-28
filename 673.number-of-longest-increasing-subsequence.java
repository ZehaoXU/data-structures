import java.util.Arrays;

/*
 * @lc app=leetcode id=673 lang=java
 *
 * [673] Number of Longest Increasing Subsequence
 *
 * https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
 *
 * algorithms
 * Medium (33.99%)
 * Likes:    883
 * Dislikes: 63
 * Total Accepted:    34.1K
 * Total Submissions: 100.2K
 * Testcase Example:  '[1,3,5,4,7]'
 *
 * 
 * Given an unsorted array of integers, find the number of longest increasing
 * subsequence.
 * 
 * 
 * Example 1:
 * 
 * Input: [1,3,5,4,7]
 * Output: 2
 * Explanation: The two longest increasing subsequence are [1, 3, 4, 7] and [1,
 * 3, 5, 7].
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: [2,2,2,2,2]
 * Output: 5
 * Explanation: The length of longest continuous increasing subsequence is 1,
 * and there are 5 subsequences' length is 1, so output 5.
 * 
 * 
 * 
 * Note:
 * Length of the given array will be not exceed 2000 and the answer is
 * guaranteed to be fit in 32-bit signed int.
 * 
 */
class Solution {
    /**
     * DP，不仅仅length需要dp，还需要一个count记录i位置maxLength有几种组成方法，转换方程详见程序
     * TC O(n^2); SC O(n)
     * Your runtime beats 52.67 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.9 MB)
     * @param nums
     * @return
     */
    public int findNumberOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;
        int max = 1, maxCount = 0;
        int[] size = new int[nums.length];
        int[] count = new int[nums.length];
        Arrays.fill(count, 1);
        Arrays.fill(size, 1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) if (nums[j] < nums[i]) {
                if (size[j]+1 > size[i]) {
                    size[i] = size[j]+1;
                    max = Math.max(max, size[i]);
                    // count 转换方程
                    count[i] = count[j];
                }
                else if (size[j]+1 == size[i]) {
                    // count 转换方程
                    count[i] += count[j];
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (size[i] == max)
                maxCount += count[i];
        }        

        return maxCount;
    }
}

