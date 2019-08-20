/*
 * @lc app=leetcode id=75 lang=java
 *
 * [75] Sort Colors
 *
 * https://leetcode.com/problems/sort-colors/description/
 *
 * algorithms
 * Medium (42.92%)
 * Likes:    1858
 * Dislikes: 167
 * Total Accepted:    345.1K
 * Total Submissions: 803.3K
 * Testcase Example:  '[2,0,2,1,1,0]'
 *
 * Given an array with n objects colored red, white or blue, sort them in-place
 * so that objects of the same color are adjacent, with the colors in the order
 * red, white and blue.
 * 
 * Here, we will use the integers 0, 1, and 2 to represent the color red,
 * white, and blue respectively.
 * 
 * Note: You are not suppose to use the library's sort function for this
 * problem.
 * 
 * Example:
 * 
 * 
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * 
 * Follow up:
 * 
 * 
 * A rather straight forward solution is a two-pass algorithm using counting
 * sort.
 * First, iterate the array counting number of 0's, 1's, and 2's, then
 * overwrite array with total number of 0's, then 1's and followed by 2's.
 * Could you come up with a one-pass algorithm using only constant space?
 * 
 * 
 */
class Solution {
    /**
     * 两个中心点分割的快速排序，也可以算三指针，0~l-1全是0，r+1~n-1全是2，这样剩下的中间的就都是1
     * l r从两边开始，i从0开始遍历到r，如果遇到0，则和 l 交换，换过来的一定是1，所以i不用留在原地再查一遍，直接++；如果遇到2，则和 r 交换，由于换过来的是什么不确定，需要i停留一步检查；如果遇到1就++
     * TC O(n); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 99.21 % of java submissions (35.2 MB)
     * @param nums
     */
    public void sortColors(int[] nums) {
        if (nums == null)   return;
        int l = 0, r = nums.length-1;
        int curr = 0;
        while (curr <= r) {
            if (nums[curr] == 0) {
                swap(nums, l, curr);
                l++;
                curr++;
            }
            else if (nums[curr] == 1) {
                curr++;
            }
            else {
                swap(nums, curr, r);
                r--;
            }
        }
    }
    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
    }
}

