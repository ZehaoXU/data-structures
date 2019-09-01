/*
 * @lc app=leetcode id=312 lang=java
 *
 * [312] Burst Balloons
 *
 * https://leetcode.com/problems/burst-balloons/description/
 *
 * algorithms
 * Hard (48.14%)
 * Likes:    1513
 * Dislikes: 47
 * Total Accepted:    70.5K
 * Total Submissions: 146.1K
 * Testcase Example:  '[3,1,5,8]'
 *
 * Given n balloons, indexed from 0 to n-1. Each balloon is painted with a
 * number on it represented by array nums. You are asked to burst all the
 * balloons. If the you burst balloon i you will get nums[left] * nums[i] *
 * nums[right] coins. Here left and right are adjacent indices of i. After the
 * burst, the left and right then becomes adjacent.
 * 
 * Find the maximum coins you can collect by bursting the balloons wisely.
 * 
 * Note:
 * 
 * 
 * You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can
 * not burst them.
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 
 * 
 * Example:
 * 
 * 
 * Input: [3,1,5,8]
 * Output: 167 
 * Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  -->
 * []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 * 
 */
class Solution {
    /**
     * 很难的dp，dp[i][j] i j表示两个边界
     * 首要问题：如果从满减少到0，两边的气球难以记录；不过由0填充到满，是打爆气球的相反顺序，而且两边气球可以记录，插入一个气球后，计算在其两端插入的最大分数，为分治法提供前提
     * dp[i][j] 表示在i-j的索引中插入气球获得的最大值，转移方程 dp[i][j] = max(dp[i][j], dp[i][k]+nums[k]*nums[i]*nums[j]+dp[k][j]) 在k index插入气球能让整体获得最大值
     * 扩展数组nums 加入两个边界，避免判断边界条件，同时i j 不包含在内，即dp[i][i+1] 中没有空插入气球，所以是0。起始条件 dp[i][i+2]
     * TC O(n^3) n^2 sub problems, n time to sovle each; SC O(n^2)
     * Your runtime beats 49.49 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.5 MB)
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        if (nums.length == 0 || nums == null)  return 0;
        int n = nums.length + 2;
        int[] newNums = new int[n];
        int[][] dp = new int[n][n];
        for (int i = 0; i < n-2; i++)
            newNums[i+1] = nums[i];
        newNums[0] = newNums[n-1] = 1;
        for (int len = 2; len < n; len++) {
            for (int i = 0; i < n-len; i++) {
                for (int j = i+1; j < i+len; j++) {
                    dp[i][i+len] = 
                        Math.max(dp[i][i+len], 
                        dp[i][j] + dp[j][i+len] + newNums[i]*newNums[j]*newNums[i+len]);
                }
            }
        }
        return dp[0][n-1];
    }
}

