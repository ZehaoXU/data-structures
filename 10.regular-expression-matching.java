/*
 * @lc app=leetcode id=10 lang=java
 *
 * [10] Regular Expression Matching
 *
 * https://leetcode.com/problems/regular-expression-matching/description/
 *
 * algorithms
 * Hard (25.26%)
 * Likes:    2684
 * Dislikes: 521
 * Total Accepted:    310.6K
 * Total Submissions: 1.2M
 * Testcase Example:  '"aa"\n"a"'
 *
 * Given an input string (s) and a pattern (p), implement regular expression
 * matching with support for '.' and '*'.
 * 
 * 
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * 
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Note:
 * 
 * 
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters
 * like . or *.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'.
 * Therefore, by repeating 'a' once, it becomes "aa".
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * 
 * 
 * Example 4:
 * 
 * 
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore
 * it matches "aab".
 * 
 * 
 * Example 5:
 * 
 * 
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 * 
 * 
 */
class Solution {
    /**
     * dp，转移方程很难找，情况很复杂，首先题意就很复杂难以捉摸。
     * 双指针dp table，核心就是a*可以match 0个，也可以match很多个（一个和很多个一样），大概以此为核心进行状态转移
     * DP: https://leetcode.com/problems/regular-expression-matching/discuss/191830/Java-DP-solution-beats-100-with-explanation
     * Recursive without memo: https://leetcode.com/problems/regular-expression-matching/discuss/5802/Simple-java-recursive-solution-with-two-cases
     * TC O(mn); SC O(mn)
     * Your runtime beats 96.03 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.5 MB)
     */
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length()+1][s.length()+1];
        dp[0][0] = true;
        // dp[0][i] = false
        for (int i = 2; i < p.length()+1; i++) {
            dp[i][0] = (p.charAt(i-1) == '*' && dp[i-2][0]);
        }

        for (int i = 1; i < p.length()+1; i++) {
            for (int j = 1; j < s.length()+1; j++) {
                if (p.charAt(i-1) == s.charAt(j-1) || p.charAt(i-1) == '.') {
                    dp[i][j] = dp[i-1][j-1];
                }
                // not equal
                else if (p.charAt(i-1) == '*') {
                    if (p.charAt(i-2)=='.' || p.charAt(i-2)==s.charAt(j-1)) 
                        dp[i][j] = dp[i-2][j] || dp[i][j-1];
                    else    dp[i][j] = dp[i-2][j];
                }
            }
        }
        return dp[p.length()][s.length()];
    }
}

