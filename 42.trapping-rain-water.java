/*
 * @lc app=leetcode id=42 lang=java
 *
 * [42] Trapping Rain Water
 */
class Solution {
    /**
     * 双指针，从左遍历一遍+从右遍历一遍，找比当前高(>=)的bar才能蓄水；注意两边bar一样高的水池不要左右计算两次，所以一次遍历有>=，一次遍历只有>
     * 时间复杂度 O(n); 空间复杂度 O(1)
     * Your runtime beats 98.76 % of java submissions
     * Your memory usage beats 99.94 % of java submissions (35.8 MB)
     * @param height
     * @return
     */
    public int trap(int[] height) {
        int left = 0, right = 1;
        int res = 0;
        int stone = 0;
        while (right < height.length){
            if (height[right] >= height[left]){
                res += height[left] * (right-left-1) - stone;
                left = right++;
                stone = 0;
            }
            else{
                stone += height[right];
                right++;
            }
        }

        left = height.length - 2;
        right = height.length - 1;
        stone = 0;
        while (left >= 0){
            // 避免重复计算，所以没有=
            if (height[left] > height[right]){
                res += height[right] * (right-left-1) - stone;
                stone = 0;
                right = left--;
            }
            else{
                stone += height[left];
                left--;
            }
        }

        return res;
    }
}

