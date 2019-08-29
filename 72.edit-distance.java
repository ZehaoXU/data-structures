/*
 * @lc app=leetcode id=72 lang=java
 *
 * [72] Edit Distance
 *
 * https://leetcode.com/problems/edit-distance/description/
 *
 * algorithms
 * Hard (38.90%)
 * Likes:    2395
 * Dislikes: 35
 * Total Accepted:    191.6K
 * Total Submissions: 490.1K
 * Testcase Example:  '"horse"\n"ros"'
 *
 * Given two words word1 and word2, find the minimum number of operations
 * required to convert word1 to word2.
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * 
 * Insert a character
 * Delete a character
 * Replace a character
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation: 
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation: 
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * 
 * 
 */
class Solution {
    /**
     * dp，经典的两指针dp，二维dp table。dp[i][j] 表示word1的前i位和word2的前j位匹配，最少的edit次数。其中转移方程比较难想，后来发现短序列insert和长序列delete是一个效果，具体转移需要判断word1[i] 和word2[j] 是否相等，然后根据前三个状态dp[i-1][j-1], dp[i-1][j], dp[i][j-1]得到当前状态
     * TC O(mn); SC O(mn)
     * Your runtime beats 37.61 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.1 MB)
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < dp[0].length; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i-1][j-1]-1, 
                                Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j-1], 
                                Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

