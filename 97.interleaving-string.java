/*
 * @lc app=leetcode id=97 lang=java
 *
 * [97] Interleaving String
 *
 * https://leetcode.com/problems/interleaving-string/description/
 *
 * algorithms
 * Hard (28.61%)
 * Likes:    893
 * Dislikes: 48
 * Total Accepted:    119.8K
 * Total Submissions: 417.1K
 * Testcase Example:  '"aabcc"\n"dbbca"\n"aadbbcbcac"'
 *
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and
 * s2.
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * Output: false
 * 
 * 
 */
class Solution {
    /**
     * 双指针dp，第三个隐藏指针指向s3，dp[i][j]表示s1前i和s2前j能否和s3前i+j匹配上，转移方程 依赖于s1[i] s2[j] s3[i+j] 是否相等，如果想等可以往前寻找
     * TC O(mn); SC O(mn)
     * Your runtime beats 23.75 % of java submissions
     * Your memory usage beats 100 % of java submissions (34.4 MB)
     * 
     * 优化：
     *  可以将复杂的判断，写成一个赋值语句，大幅减少时间
     *  table[i][j] = (table[i-1][j] && s1[i-1] == s3[i+j-1] ) || (table[i][j-1] && s2[j-1] == s3[i+j-1])
     * 
     * 其他方法：
     *  DFS BFS都行，dfs需要memo table，bfs也需要一个set来避免重复
     * https://leetcode.com/problems/interleaving-string/discuss/31904/Summary-of-solutions-BFS-DFS-DP
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        int len1 = s1.length(), len2 = s2.length(), len3 = s3.length();
        if (len3 != len1 + len2)    return false;
        boolean[][] dp = new boolean[len1+1][len2+1];
        dp[0][0] = true;
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
        } 
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j-1] && s2.charAt(j-1) == s3.charAt(j-1);
        }
        // fill dp table
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (s1.charAt(i-1) == s3.charAt(i+j-1) &&
                    s2.charAt(j-1) == s3.charAt(i+j-1)) {
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
                else if (s1.charAt(i-1) == s3.charAt(i+j-1))
                    dp[i][j] = dp[i-1][j];
                else if (s2.charAt(j-1) == s3.charAt(i+j-1))
                    dp[i][j] = dp[i][j-1];
            }
        }
        return dp[len1][len2];
    }
}

