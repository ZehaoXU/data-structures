import java.lang.*;
import java.util.*;

/*
 * @lc app=leetcode id=140 lang=java
 *
 * [140] Word Break II
 *
 * https://leetcode.com/problems/word-break-ii/description/
 *
 * algorithms
 * Hard (27.93%)
 * Likes:    1118
 * Dislikes: 261
 * Total Accepted:    171.1K
 * Total Submissions: 610.5K
 * Testcase Example:  '"catsanddog"\n["cat","cats","and","sand","dog"]'
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of
 * non-empty words, add spaces in s to construct a sentence where each word is
 * a valid dictionary word. Return all such possible sentences.
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
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 * "cats and dog",
 * "cat sand dog"
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 * "pine apple pen apple",
 * "pineapple pen apple",
 * "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 * 
 */
class SolutionDP {
    /**
     * dp，和139基本相同，此时dp存储的变成了List<String>，表示以i的前一个元素结尾的string全部的word break组合，状态转移：对于i前面的每一个j，如果j长度非零（有组合），检查j-i是否也是一个word，如果是，则对j中每一个string 添加 substring(j, i)
     * TC O(2^n) 时间复杂度分析见下面方法; SC O(n*n*2^n) dp:n, stringLenght:n, maxNumOfStrings:2^n
     * 居然TLE！
     * 
     * 其他方法：
     *  DFS+memorization
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0)   return new ArrayList<>();
        Set<String> set = new HashSet<>(wordDict);
        List<String> res = new ArrayList<>();
        ArrayList<String>[] dp = new ArrayList[s.length()+1];
        dp[0] = new ArrayList<>();
        dp[0].add("");
        for (int i = 1; i <= s.length(); i++) {
            dp[i] = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() != 0 && set.contains(s.substring(j, i))) {
                    for (String curr : dp[j]) {
                        dp[i].add(curr + (curr.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
        }
        return dp[s.length()];
    }
}


class Solution {
    /**
     * DFS，记忆递归，注意退出条件：如果每找到返回空array，如果找到最后了，返回 ""，目的是在上一次区别有没有结果
     * TC O(2^n) 因为虽然看起来是n^3，不过忽略了String组合的循环，最坏情况下aaaaaaaa，wordDict包含所有前缀，共有2^n种组合方式，势必每个都要至少处理O(1); SC O(n^2*2^n)
     * Your runtime beats 53.25 % of java submissions
     * Your memory usage beats 21.31 % of java submissions (39.7 MB)
     * @param s
     * @param wordDict
     * @return
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        map = new HashMap<>();
        set = new HashSet<>(wordDict);
        return dfs(s);
    }
    private Map<String, List<String>> map;
    private Set<String> set;
    private List<String> dfs(String s) {
        if (s.length() == 0 || s == null) {
            List<String> res = new ArrayList<>();
            res.add("");
            return res;
        }
        
        if (map.containsKey(s)) return map.get(s);

        List<String> list = new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            if (!set.contains(s.substring(0, i)))   continue;
            for (String comb : dfs(s.substring(i))) {
                list.add(s.substring(0, i) + (comb=="" ? "" : " ") + comb);
            }
        }
        map.put(s, list);
        return list;
    }
}

