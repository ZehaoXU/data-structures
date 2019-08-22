/*
 * @lc app=leetcode id=215 lang=java
 *
 * [215] Kth Largest Element in an Array
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/description/
 *
 * algorithms
 * Medium (49.19%)
 * Likes:    2295
 * Dislikes: 188
 * Total Accepted:    418.1K
 * Total Submissions: 848.2K
 * Testcase Example:  '[3,2,1,5,6,4]\n2'
 *
 * Find the kth largest element in an unsorted array. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * Example 1:
 * 
 * 
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * 
 * Note: 
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 * 
 */
class Solution {
    /**
     * 无序序列找第k大的元素，想到quick select算法（quick sort），这个程序实践有点问题，实际只需要双指针，只交换一类元素即可，如pivot=nums[r]然后把小于pivot的全放到index左边，最后交换index 和 r
     * 时间复杂度 O(2n); SC O(logn)
     * Your runtime beats 5 % of java submissions
     * Your memory usage beats 90.16 % of java submissions (36.9 MB)
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int res = quickSelect(nums, k, 0, nums.length-1);
        return res;
    }
    private int quickSelect(int[] nums, int target, int l, int r) {
        int left = l, right = r, index = l+1;
        int num = nums[l];
        while (left < right) {
            if (nums[index] >= num) {
                swap(nums, index, right--);
            }
            else {
                swap(nums, index++, left++);
            }
        }
        if (left == nums.length-target) return nums[left];
        else if (left < nums.length-target)    return quickSelect(nums, target, left+1, r);
        else    return quickSelect(nums, target, l, right-1);
    }
    private void swap(int[] nums, int l, int r) {
        int temp = nums[l];
        nums[l] = nums[r];
        nums[r] = temp;
        return;
    }
}

