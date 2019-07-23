/*
 * @lc app=leetcode id=14 lang=java
 *
 * [14] Longest Common Prefix
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++){
            // while (strs[i].indexOf(prefix) != 0){
            //     prefix = prefix.substring(0, prefix.length()-1);
            //     if (prefix.length() == 0)   return "";
            // }

           int j = 0;
            for (; j < prefix.length() && j < strs[i].length(); j++){
                if (prefix.charAt(j) != strs[i].charAt(j)){
                    break;
                }
            }
            prefix = prefix.substring(0, j);
            if (prefix.length() == 0)   return "";
        }
        return prefix;
    }
}

