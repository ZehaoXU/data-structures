/*
 * @lc app=leetcode id=162 lang=java
 *
 * [162] Find Peak Element
 *
 * https://leetcode.com/problems/find-peak-element/description/
 *
 * algorithms
 * Medium (41.56%)
 * Likes:    921
 * Dislikes: 1419
 * Total Accepted:    254.7K
 * Total Submissions: 612.5K
 * Testcase Example:  '[1,2,3,1]'
 *
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element
 * and return its index.
 * 
 * The array may contain multiple peaks, in that case return the index to any
 * one of the peaks is fine.
 * 
 * You may imagine that nums[-1] = nums[n] = -∞.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index
 * number 2.
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 1 or 5 
 * Explanation: Your function can return either index number 1 where the peak
 * element is 2, 
 * or index number 5 where the peak element is 6.
 * 
 * 
 * Note:
 * 
 * Your solution should be in logarithmic complexity.
 * 
 */
class Solution {
    /**
     * 二分搜索，题目中有个重要信息nums[n] != nums[n+1] 以及 nums[-1]=nums[n]=-1，说明数组曲线单调，或者不停出现折线，每个折线顶点或者递增一边的端点都是我们的可行解。画个图可以清晰的看出，由于mid偏向left一侧，只要比较nums[mid] ? nums[mid+1]，然后选取值更大的作为新边界，一直向递增的方向寻找即可
     * 想法比较巧妙，logn时间限制想到二分搜索，却没有想到在mid节点上应该怎么确定搜索方向
     * 时间复杂度 O(logn); 空间复杂度 O(1);
     * 100% 100%
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            int m = l + (r-l)/2;
            if (nums[m] > nums[m+1]) {
                r = m;
            }
            else {
                l = m + 1;
            }
        }
        return l;
    }
}

