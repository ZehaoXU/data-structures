import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=784 lang=java
 *
 * [784] Letter Case Permutation
 */
class Solution {
    /**
     * DFS
     * 时间复杂度 O(2^k) k是字母个数
     * Your runtime beats 62.44 % of java submissions
     * Your memory usage beats 99.45 % of java submissions (37.1 MB)
     * @param S
     * @return
     */
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        res.add(S);
        dfs(0, S, res);
        return res;
    }

    public void dfs(int start, String s, List<String> res){
        if (start >= s.length())    return;
        
        for (int i = start; i < s.length(); i++){
            char c = s.charAt(i);
            if (c >= '0' && c <= '9')   continue;
            else{
                char ch = transform(c);
                String newS = s.substring(0, i) + ch + (start == s.length()-1 ? "" : s.substring(i+1));
                res.add(newS);
                dfs(i+1, newS, res);
            }
        }
    }
    
    private char transform(char c){
        if (c >= 'a' && c <= 'z')
            return Character.toUpperCase(c);
        else    return Character.toLowerCase(c);
    }
}

