/*
 * @lc app=leetcode id=583 lang=java
 *
 * [583] Delete Operation for Two Strings
 *
 * https://leetcode.com/problems/delete-operation-for-two-strings/description/
 *
 * algorithms
 * Medium (45.43%)
 * Likes:    763
 * Dislikes: 20
 * Total Accepted:    35.1K
 * Total Submissions: 77.1K
 * Testcase Example:  '"sea"\n"eat"'
 *
 * 
 * Given two words word1 and word2, find the minimum number of steps required
 * to make word1 and word2 the same, where in each step you can delete one
 * character in either string.
 * 
 * 
 * Example 1:
 * 
 * Input: "sea", "eat"
 * Output: 2
 * Explanation: You need one step to make "sea" to "ea" and another step to
 * make "eat" to "ea".
 * 
 * 
 * 
 * Note:
 * 
 * The length of given words won't exceed 500.
 * Characters in given words can only be lower-case letters.
 * 
 * 
 */
class Solution {
    /**
     * dp, 实际上是最长公共子序列 longest common subsequence
     * 和Q72略有不同，72可以replace，所以不是求最长公共子序列，本题只能delete，故可以按72题思路dp[i][j] 表示first i and first j需要的最少删除次数，也可以dp[i][j]表示目前位置最长公共子序列长度，最后相减返回结果
     * TC O(mn); SC O(mn)
     * Your runtime beats 16.92 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.5 MB)
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= word2.length(); j++) {
            dp[0][j] = j;
        }
        // fill dp table
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+1;
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

