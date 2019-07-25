/*
 * @lc app=leetcode id=27 lang=java
 *
 * [27] Remove Element
 */
class Solution {
    /**
     * 双指针法，不是同向两指针，只是首尾两指针向中间靠拢，头指针检测到val则和尾指针检测内容调换。注意检测调换后的内容，以及尾指针负责计数
     * 时间复杂度 O(n) 最差情况两指针只要一共遍历1此；空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.4 MB)
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int n = nums.length-1;
        while (i <= n){
            if (nums[i] == val){
                nums[i] = nums[n--];
            }
            else {
                i++;
            }
        }
        return n + 1;
    }
}

