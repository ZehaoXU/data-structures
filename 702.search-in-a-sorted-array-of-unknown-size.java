/*
 * @lc app=leetcode id=702 lang=java
 *
 * [702] Search in a Sorted Array of Unknown Size
 *
 * https://leetcode.com/problems/search-in-a-sorted-array-of-unknown-size/description/
 *
 * algorithms
 * Medium (60.48%)
 * Likes:    166
 * Dislikes: 16
 * Total Accepted:    12.2K
 * Total Submissions: 20.1K
 * Testcase Example:  '[-1,0,3,5,9,12]\n9'
 *
 * Given an integer array sorted in ascending order, write a function to search
 * target in nums.  If target exists, then return its index, otherwise return
 * -1. However, the array size is unknown to you. You may only access the array
 * using an ArrayReader interface, where ArrayReader.get(k) returns the element
 * of the array at index k (0-indexed).
 * 
 * You may assume all integers in the array are less than 10000, and if you
 * access the array out of bounds, ArrayReader.get will return 2147483647.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: array = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in nums and its index is 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: array = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in nums so return -1
 * 
 * 
 * 
 * Note:
 * 
 * 
 * You may assume that all elements in the array are unique.
 * The value of each element in the array will be in the range [-9999, 9999].
 * 
 * 
 */
class Solution {
    /**
     * 直观想法二分法查找，但是不知道长度。题目说超过range的都是最大值，相当于始终是一个不严格增序（前面严格增，后面全是最大值），所以可以让r从1开始2的指数增加，寻找reader.get(r) > target 的最小r，那么此时r的前一个值可以作为l，缩小范围
     * TC O(1+logn); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (39.9 MB)
     * @param reader
     * @param target
     * @return
     */
    public int search(ArrayReader reader, int target) {
        int r = 1;
        while (reader.get(r) <= target) {
            r = r << 1;
        }
        int l = r >> 1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (reader.get(m) >= target)
                r = m;
            else 
                l = m+1;
        }
        if (reader.get(l) == target)    return l;
        else return -1;
    }
}

