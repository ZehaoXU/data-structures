/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */
class Solution {
    /**
     * 拙劣动态规划
     * 时间复杂度 O(n^2); 空间复杂度 O(n);
     * Runtime: 345 ms, faster than 5.76% of Java online submissions
     * Memory Usage: 40.2 MB, less than 86.32% of Java online submissions
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        int[] step = new int[nums.length];
        if (nums.length == 0)   return 0;
        step[0] = 1;
        for (int i = 0; i < nums.length-1; i++){
            for (int j = 0; j <= nums[i] && i+j < nums.length; j++){
                if (step[i+j] == 0) step[i+j] = step[i] + 1;
                else step[i+j] = Math.min(step[i+j], step[i]+1);
            }
        }
        return step[nums.length-1] - 1;
    }
}

