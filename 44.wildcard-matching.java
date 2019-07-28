/*
 * @lc app=leetcode id=44 lang=java
 *
 * [44] Wildcard Matching
 */
class Solution {
    /**
     * 动态规划，转换方程有点难写，边界条件难判断（特别是p）
     * 时间复杂度 O(mn); 空间复杂度 O(mn);
     * Your runtime beats 56.01 % of java submissions
     * Your memory usage beats 87.44 % of java submissions (38.4 MB)
     * 
     * 其他方法：
     *   还可以用DFS（回溯法）with memory
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];

        for (int i = 1; i <= s.length(); i++)
            dp[i][0] = false;
        for (int j = 0; j <= p.length(); j++){
            if (j==0 || (p.charAt(j-1) == '*' && dp[0][j-1]))
                dp[0][j] = true;
            else dp[0][j] = false;
        }
        for (int i = 1; i <= s.length(); i++){
            for (int j = 1; j <= p.length(); j++){
                if (p.charAt(j-1) == '*')
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                else if (p.charAt(j-1) == '?' || p.charAt(j-1) == s.charAt(i-1))
                    dp[i][j] = dp[i-1][j-1];
                else    dp[i][j] = false;
            }
        }
        return dp[s.length()][p.length()];
    }
}

