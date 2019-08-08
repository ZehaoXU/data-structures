/*
 * @lc app=leetcode id=287 lang=java
 *
 * [287] Find the Duplicate Number
 *
 * https://leetcode.com/problems/find-the-duplicate-number/description/
 *
 * algorithms
 * Medium (50.46%)
 * Likes:    2708
 * Dislikes: 309
 * Total Accepted:    207.3K
 * Total Submissions: 410.4K
 * Testcase Example:  '[1,3,4,2,2]'
 *
 * Given an array nums containing n + 1 integers where each integer is between
 * 1 and n (inclusive), prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,4,2,2]
 * Output: 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,1,3,4,2]
 * Output: 3
 * 
 * Note:
 * 
 * 
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated
 * more than once.
 * 
 * 
 */
class Solution {
    /**
     * 二分查找，range[1,n+1)，g(m)一开始比较难想，后来发现可以遍历一遍数组，记录小于等于他的count，重复数字之前的数字，count<=m，重复元素and之后的数字，count>n。故找到count>m的最小m即可
     * 时间复杂度 O(long*n) g(m)-O(n)
     * Your runtime beats 50.35 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.5 MB)
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int l = 1, r = nums.length;
        while (l < r) {
            int m = l + (r-l)/2;
            if (countOfSmaller(nums, m) > m) {
                r = m;
            }
            else {
                l = m+1;
            }
        }
        return l;
    }
    public int countOfSmaller(int[] nums, int m) {
        int count = 0;
        for (int i : nums) {
            if (i <= m) { count++; }
        }
        return count;
    }
}

