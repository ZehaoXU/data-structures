/*
 * @lc app=leetcode id=115 lang=java
 *
 * [115] Distinct Subsequences
 *
 * https://leetcode.com/problems/distinct-subsequences/description/
 *
 * algorithms
 * Hard (35.56%)
 * Likes:    770
 * Dislikes: 39
 * Total Accepted:    112.7K
 * Total Submissions: 316.2K
 * Testcase Example:  '"rabbbit"\n"rabbit"'
 *
 * Given a string S and a string T, count the number of distinct subsequences
 * of S which equals T.
 * 
 * A subsequence of a string is a new string which is formed from the original
 * string by deleting some (can be none) of the characters without disturbing
 * the relative positions of the remaining characters. (ie, "ACE" is a
 * subsequence of "ABCDE" while "AEC" is not).
 * 
 * Example 1:
 * 
 * 
 * Input: S = "rabbbit", T = "rabbit"
 * Output: 3
 * Explanation:
 * 
 * As shown below, there are 3 ways you can generate "rabbit" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "babgbag", T = "bag"
 * Output: 5
 * Explanation:
 * 
 * As shown below, there are 5 ways you can generate "bag" from S.
 * (The caret symbol ^ means the chosen letters)
 * 
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 * ⁠ ^  ^^
 * babgbag
 * ⁠   ^^^
 * 
 * 
 */
class Solution {
    /**
     * 双指针dp，dp[i][j]表示s的前i个有多少种匹配t前j个的方法（s中可以用或不用，t中必须全匹配），转移方程 根据s[i] t[j]是否相同分两类：如果两个字符不同，dp[i][j] = dp[i-1][j]; 如果两个字符相同，dp[i][j]=dp[i-1][j] + dp[i-1][j-1] 可以用i来匹配，也可以不用i来匹配
     * TC O(mn); SC O(mn)
     * Your runtime beats 71.13 % of java submissions
     * Your memory usage beats 88.46 % of java submissions (35.9 MB)
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        int lens = s.length(), lent = t.length();
        if (lent > lens)    return 0;
        // init
        int[][] dp = new int[lens+1][lent+1];
        for (int i = 0; i <= lens; i++)
            dp[i][0] = 1;
        // fill dp table
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent && j <= i; j++) {
                dp[i][j] = 
                    dp[i-1][j]
                    + (s.charAt(i-1)==t.charAt(j-1) ? dp[i-1][j-1] : 0);
            }
        }
        return dp[lens][lent];
    }
}

