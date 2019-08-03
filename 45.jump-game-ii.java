/*
 * @lc app=leetcode id=45 lang=java
 *
 * [45] Jump Game II
 */
class Solution {
    /**
     * 贪婪法，每一步跳到 下一步能跳最远 的位置
     * 时间复杂度 O(n)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.8 MB)
     * @param nums
     * @return
     */
    public int jump(int[] nums) {
        if (nums.length == 0 || nums.length == 1)   return 0;
        int step = 0;
        int maxReach = nums[0];
        
        for (int i = 0; i < nums.length-1 && maxReach < nums.length-1; step++){
            int oldMax = maxReach;
            for (int j = i+1; j <= oldMax; j++){
                if (j+nums[j] > maxReach){
                    maxReach = j+nums[j];
                    i = j;
                }
            }
        }
        return ++step;
    }
}

class SolutionStupid {
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

