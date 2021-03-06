/*
 * @lc app=leetcode id=11 lang=java
 *
 * [11] Container With Most Water
 *
 * https://leetcode.com/problems/container-with-most-water/description/
 *
 * algorithms
 * Medium (44.46%)
 * Likes:    3368
 * Dislikes: 445
 * Total Accepted:    384.6K
 * Total Submissions: 855.5K
 * Testcase Example:  '[1,8,6,2,5,4,8,3,7]'
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a
 * point at coordinate (i, ai). n vertical lines are drawn such that the two
 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most
 * water.
 * 
 * Note: You may not slant the container and n is at least 2.
 * 
 * 
 * 
 * 
 * 
 * The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In
 * this case, the max area of water (blue section) the container can contain is
 * 49. 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input: [1,8,6,2,5,4,8,3,7]
 * Output: 49
 * 
 */
class Solution {
    /**
     * 双指针，每次移动短板，可以证明这样能刷新出max area
     * TC O(n); SC O(1)
     * Your runtime beats 94.38 % of java submissions
     * Your memory usage beats 96.15 % of java submissions (39.5 MB)
     */
    public int maxArea(int[] height) {
        int l = 0, r = height.length-1;
        int max = 0;
        while (l < r) {
            int curr = Math.min(height[l], height[r])*(r-l);
            if (curr > max) max = curr;
            if (height[l] < height[r])
                l++;
            else r--;
        }
        return max;
    }
}

