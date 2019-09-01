/*
 * @lc app=leetcode id=664 lang=java
 *
 * [664] Strange Printer
 *
 * https://leetcode.com/problems/strange-printer/description/
 *
 * algorithms
 * Hard (37.26%)
 * Likes:    281
 * Dislikes: 37
 * Total Accepted:    10.1K
 * Total Submissions: 27K
 * Testcase Example:  '"aaabbb"'
 *
 * 
 * There is a strange printer with the following two special requirements:
 * 
 * 
 * The printer can only print a sequence of the same character each time.
 * At each turn, the printer can print new characters starting from and ending
 * at any places, and will cover the original existing characters.
 * 
 * 
 * 
 * 
 * 
 * Given a string consists of lower English letters only, your job is to count
 * the minimum number of turns the printer needed in order to print it.
 * 
 * 
 * Example 1:
 * 
 * Input: "aaabbb"
 * Output: 2
 * Explanation: Print "aaa" first and then print "bbb".
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: "aba"
 * Output: 2
 * Explanation: Print "aaa" first and then print "b" from the second place of
 * the string, which will cover the existing character 'a'.
 * 
 * 
 * 
 * Hint: Length of the given string will not exceed 100.
 */
class Solution {
    /**
     * 分治法dp，太难了
     * dp[i][j] 表示从i-j的打印次数，包含i j。dp[i][j]最差情况下是j-i+1次打印，目前有两个比较好的转换方程：
     *  1.dp[i][j] = min(dp[i][j], dp[i][k] + dp[k+1][j] -1) if s[i] == s[j] 两端元素相同的话可以先用两端元素全填满，所以-1
     *  2.dp[i][j] = min(dp[i][j-1]+1, dp[i][k], dp[k+1][j-1]) if s[k] == s[j], i<=k<j-1, 相当于填充k时顺便填充了j，之后不用填充j，所以没-1而是直接舍掉j
     * TC O(n^3); SC O(n^2)
     * Your runtime beats 88.16 % of java submissions
     * Your memory usage beats 100 % of java submissions (35.6 MB)
     * @param s
     * @return
     */
    public int strangePrinter(String s) {
        int n = s.length();
        if (n == 0) return 0;

        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n-len; i++) {
                dp[i][i+len] = dp[i][i+len-1] + 1;
                for (int j = i; j < i + len; j++) {
                    int temp = (s.charAt(i)==s.charAt(i+len) ? -1:0);
                    dp[i][i+len] = Math.min(dp[i][i+len], dp[i][j] + dp[j+1][i+len] + temp);
                }
            }
        }
        return dp[0][n-1];
    }
}

