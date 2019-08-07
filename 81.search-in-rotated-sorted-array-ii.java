/*
 * @lc app=leetcode id=81 lang=java
 *
 * [81] Search in Rotated Sorted Array II
 *
 * https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
 *
 * algorithms
 * Medium (32.72%)
 * Likes:    736
 * Dislikes: 339
 * Total Accepted:    181.8K
 * Total Submissions: 555.5K
 * Testcase Example:  '[2,5,6,0,0,1,2]\n0'
 *
 * Suppose an array sorted in ascending order is rotated at some pivot unknown
 * to you beforehand.
 * 
 * (i.e., [0,0,1,2,2,5,6] might become [2,5,6,0,0,1,2]).
 * 
 * You are given a target value to search. If found in the array return true,
 * otherwise return false.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,5,6,0,0,1,2], target = 0
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2,5,6,0,0,1,2], target = 3
 * Output: false
 * 
 * Follow up:
 * 
 * 
 * This is a follow up problem to Search in Rotated Sorted Array, where nums
 * may contain duplicates.
 * Would this affect the run-time complexity? How and why?
 * 
 * 
 */
class Solution {
    /**
     * 二分查找，与Q33的区别是 1 3 1 1 1这种nmid=nleft=nright的情况无法判断，故不能仅通过nmid >= nleft判断左边有序，需要严格升序才行。
     * 故增设条件，如果左边肯定排序nmid > nleft或右边肯定不排序nmid > mright，则说明左边有序；如果左边肯定不排序nmid < nleft或右边肯定排序nmid < nright，则说明右边有序；
     * 如果皆非，说明nmid=nleft=nright，则right-- / left ++直到出现一个不同的mid/left/right
     * 时间复杂度 O(logn) 最坏情况 O(n)
     * Your runtime beats 41.4 % of java submissions
     * Your memory usage beats 45.8 % of java submissions (39.6 MB)
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        if (nums.length == 0 || nums == null)   return false;
        int l = 0, r = nums.length-1;
        while (l < r) {
            int m = l + (r-l)/2;
            // left is sorted or right is unsorted
            if (nums[l] < nums[m] || nums[m] > nums[r]) {
                if (target >= nums[l] && target <= nums[m]) {
                    r = m;
                }
                else {
                    l = m+1;
                }
            }
            // right is sorted or left is unsorted
            else if (nums[m] < nums[r] || nums[m] < nums[l]) {
                if (target <= nums[r] && target > nums[m]) {
                    l = m+1;
                }
                else {
                    r = m;
                }
            }
            else {
                l++;
            }
        }
        if (nums[l] != target)  return false;
        return true;
    }
}

