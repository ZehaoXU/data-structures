/*
 * @lc app=leetcode id=167 lang=java
 *
 * [167] Two Sum II - Input array is sorted
 *
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
 *
 * algorithms
 * Easy (51.06%)
 * Likes:    1020
 * Dislikes: 423
 * Total Accepted:    277.2K
 * Total Submissions: 542.5K
 * Testcase Example:  '[2,7,11,15]\n9'
 *
 * Given an array of integers that is already sorted in ascending order, find
 * two numbers such that they add up to a specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they
 * add up to the target, where index1 must be less than index2.
 * 
 * Note:
 * 
 * 
 * Your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may
 * not use the same element twice.
 * 
 * 
 * Example:
 * 
 * 
 * Input: numbers = [2,7,11,15], target = 9
 * Output: [1,2]
 * Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
 * 
 */
class Solution {
    /**
     * 常规2sum要使用hashmap，SC O(n)
     * 由于排序过了，可以使用双指针，反向向中间靠拢，根据sum和target的关系调整左右指针
     * TC O(n); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.3 MB)
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        int l = 0, r = numbers.length-1;
        int[] res = new int[2];
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                res[0] = l+1;
                res[1] = r+1;
                break;
            }
            else if (sum > target)
                r--;
            else
                l++;
        }
        return res;
    }
}

