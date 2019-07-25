import java.lang.reflect.Array;
import java.util.ArrayList;

import sun.security.util.Length;

/*
 * @lc app=leetcode id=32 lang=java
 *
 * [32] Longest Valid Parentheses
 */
class Solution {
    /**
     * 聪明算法，left right分别记录碰到的括号数，详见https://leetcode.com/problems/longest-valid-parentheses/solution/
     * 时间复杂度 O(n); 空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 88.65 % of java submissions (37.6 MB)
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int left = 0, right = 0;
        int maxLength = 0;

        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '(')
                left += 1;
            else    right += 1;
            if (right > left)   left = right = 0;
            else if (right == left)
                maxLength = Math.max(maxLength, right);
        }

        left = right = 0;
        for (int i = s.length()-1; i >= 0; i--){
            if (s.charAt(i) == '(')
                left += 1;
            else    right += 1;
            if (left > right)   left = right = 0;
            else if (right == left)
                maxLength = Math.max(maxLength, left);
        }
        return maxLength*2;
    }
}

class SolutionTwo {
    /**
     * 动态规划，dp[i]数组，**表示以i结尾的子串中最长有效字串长度**
     * 公式：
     *      '(' -> dp[i] = 0;
     *      ')' -> if s[i-1] = '(' -> dp[i] = dp[i-2] + 2;
     *          -> else if dp[i-1] != 0 (寻找valid字串前的 '(')
     *              -> if s[i- dp[i-1] - 1] == '('
     *              -> dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2] 
     *              (前方可能还有有效括号组，eg."()")
     * 时间复杂度 O(n); 空间复杂度 O(n)
     * Your runtime beats 74.98 % of java submissions
     * Your memory usage beats 99.06 % of java submissions (37.1 MB)
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int maxLength = 0;
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == ')' && s.charAt(i-1) == '(')
                dp[i] = i < 2 ? 2 : dp[i-2] + 2;
            else if (s.charAt(i) == ')' && dp[i-1] != 0 && i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(')
                dp[i] = dp[i-1] + 2 + (i-dp[i-1]-2 >= 0 ? dp[i-dp[i-1]-2] : 0);
            else 
                dp[i] = 0;
            
            if (dp[i] > maxLength)  maxLength = dp[i];
        }
        return maxLength;
    }
}

