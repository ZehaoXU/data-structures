/*
 * @lc app=leetcode id=704 lang=java
 *
 * [704] Binary Search
 *
 * https://leetcode.com/problems/binary-search/description/
 *
 * algorithms
 * Easy (48.67%)
 * Likes:    291
 * Dislikes: 32
 * Total Accepted:    62.3K
 * Total Submissions: 127.9K
 * Testcase Example:  '[-1,0,3,5,9,12]\n9'
 *
 * Given a sorted (in ascending order) integer array nums of n elements and a
 * target value, write a function to search target in nums. If target exists,
 * then return its index, otherwise return -1.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * You may assume that all elements in nums are unique.
 * n will be in the range [1, 10000].
 * The value of each element in nums will be in the range [-9999, 9999].
 * 
 * 
 */
class Solution {
    /**
     * BS，依旧需要注意右区间开闭/mid比较条件/靠近谁谁多移动
     * 时间复杂度 O(logn)；空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 86.9 % of java submissions (39.9 MB)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (nums[m] < target) {
                l = m + 1;
            }
            else {
                r = m;
            }
        }
        if (nums[l] == target)  return l;
        else return -1;
    }
}

