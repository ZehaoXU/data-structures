/*
 * @lc app=leetcode id=55 lang=java
 *
 * [55] Jump Game
 */
class Solution {
    /**
     * 贪婪法，每次只要记录能跳到的最远距离即可，如果index到了最远距离，最远距离却小于n-1时，表明跳不到
     * 时间复杂度 O(n), 空间复杂度 O(1)
     * Your runtime beats 99.39 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.7 MB)
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 0) return false;
        int maxJump = 0;
        for (int i = 0; i < n-1; i++){
            maxJump = Math.max(i+nums[i], maxJump);
            if (maxJump >= n-1) return true;
            if (i == maxJump){
                return false;
            }
        }
        return true;
    }
}

 class SolutionDP {
    /**
     * 动态规划
     * 时间复杂度 O(nm), 空间复杂度 O(n)
     * Your runtime beats 17.6 % of java submissions
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;
        if (n == 0)   return false;
        boolean[] dp = new boolean[n];
        dp[0] = true;
        loop:
        for (int i = 0; i < n-1; i++){
            if (dp[i] == false) continue;
            for (int j = 1; j <= nums[i] && i+j < n; j++){
                dp[i+j] = true;
                if (i+j > n-1)  break loop;
            }
        }
        return dp[n-1];
    }
}

