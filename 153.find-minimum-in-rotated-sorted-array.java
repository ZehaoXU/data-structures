/*
 * @lc app=leetcode id=153 lang=java
 *
 * [153] Find Minimum in Rotated Sorted Array
 *
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/description/
 *
 * algorithms
 * Medium (43.34%)
 * Likes:    1111
 * Dislikes: 169
 * Total Accepted:    305.6K
 * Total Submissions: 704.9K
 * Testcase Example:  '[3,4,5,1,2]'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,4,5,1,2] 
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [4,5,6,7,0,1,2]
 * Output: 0
 * 
 * 
 */
class Solution {
    /**
     * 分治法，但是核心是一路分治！而不是两路
     * 一路分治时间复杂度logn，两路分治时间复杂度n；T(n) = T(2/n) + O(1) 和 2*T(n/2) + O(1) 的区别
     * 关键点是总有一半是排好序的，就不用继续往下递归，直接返回nums[l]即可，每层只有一边需要乡下递归。最终只剩一个元素的时候是递归出口（和有序与否判断可以合并）
     * 时间复杂度 O(logn); 空间复杂度 O(logn)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.3 MB)
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        if (nums.length==0 || nums == null) return -1;
        int min = helper(nums, 0, nums.length-1);
        return min;
    }

    public int helper(int[] nums, int l, int r) {
        if (nums[l] <= nums[r]) return nums[l];
        int m = l + (r-l)/2;
        int min = Math.min(helper(nums, l, m), helper(nums, m+1, r));
        return min;
    }
}

