import java.util.HashSet;
import java.util.Set;

/*
 * @lc app=leetcode id=139 lang=java
 *
 * [139] Word Break
 *
 * https://leetcode.com/problems/word-break/description/
 *
 * algorithms
 * Medium (36.15%)
 * Likes:    2573
 * Dislikes: 136
 * Total Accepted:    373.1K
 * Total Submissions: 1M
 * Testcase Example:  '"leetcode"\n["leet","code"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, determine if s can be segmented into a space-separated
 * sequence of one or more dictionary words.
 * 
 * Note:
 * 
 * 
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet
 * code".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple
 * pen apple".
 * Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 * 
 * 
 */
class Solution {
    /**
     * dp，每个状态都要 都要O(n)个sub state需要处理
     * 本次写法不算正规的dp，因为dp应该是处理dp[i]的结果，而本次在找从dp[i]出发的终点，更像是bfs（不断确认新的起点，并且访问）
     * 正统dp应该是 dp[i]=true if dp[j]==true and substirng(i,j) 是词汇之一，而不是向i的后面赋值
     * TC O(n^2); SC O(n + m) n是string长度，m是list长度
     * Your runtime beats 55.31 % of java submissions
     * Your memory usage beats 93.48 % of java submissions (36.3MB)
     * 
     * 其他方法：
     *  BFS，记忆递归
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0)   return false;
        boolean[] dp = new boolean[s.length()+1];
        Set<String> set = new HashSet<>(wordDict);
        dp[0] = true;   // before i can break
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (dp[i] == false) continue;
            for (int end = i+1; end <= n; end++) {
                if (set.contains(s.substring(i,end)))
                    dp[end] = true;
            }
            if (dp[n] == true)  break;
        }

        return dp[n];
    }
}

