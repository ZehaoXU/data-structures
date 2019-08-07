/*
 * @lc app=leetcode id=154 lang=java
 *
 * [154] Find Minimum in Rotated Sorted Array II
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Hard (39.62%)
 * Likes:    503
 * Dislikes: 152
 * Total Accepted:    137.9K
 * Total Submissions: 348K
 * Testcase Example:  '[1,3,5]'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * The array may contain duplicates.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,5]
 * Output: 1
 * 
 * Example 2:
 * 
 * 
 * Input: [2,2,2,0,1]
 * Output: 0
 * 
 * Note:
 * 
 * 
 * This is a follow up problem to Find Minimum in Rotated Sorted Array.
 * Would allow duplicates affect the run-time complexity? How and why?
 * 
 * 
 */
class Solution {
    /**
     * 分治法，和上一问基本一样，只是这一问需要变成严格单调增加才能返回nums[l]，所以不再总是单路分治，最差情况全是重复的，需要O(n)时间
     * 时间复杂度 Average O(logn), worst O(n)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 27.96 % of java submissions (39.6 MB)
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }
    public int helper(int[] nums, int l, int r) {
        if (r - l <= 1) return Math.min(nums[l], nums[r]);
        // strict increasing
        if (nums[l] < nums[r])  return nums[l];
        int m = l + (r-l)/2;
        return Math.min(helper(nums, l, m), 
                        helper(nums, m+1, r));
    }
}

