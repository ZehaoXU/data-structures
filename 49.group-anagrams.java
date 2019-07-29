import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

/*
 * @lc app=leetcode id=49 lang=java
 *
 * [49] Group Anagrams
 */
class Solution {
    /**
     * 乱序字符串特点：组成字符和数量相同，位置不同。
     * 两种方法：based on sort || based on hash，本次使用hash方法，只有26个字母，通过计数得到一个26位的数字字符串
     * 时间复杂度 O(nK) or O(N) K是最长字符串包含字符数，N是全部字符数
     * Your runtime beats 48.14 % of java submissions
     * Your memory usage beats 38.92 % of java submissions (44.5 MB)
     * 
     * 其他方法：based on sort O(nK*logK)，实际上对于本题局限在a-z，所以排序可以用桶排序，故最终复杂度 O(nK)
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        if (strs.length == 0)   return new ArrayList<>();
        List<List<String>> res = new ArrayList<>();

        for (String s : strs){
            int[] count = new int[26];
            for(char c : s.toCharArray()){
                count[c-'a']++;
            }
            StringBuilder key = new StringBuilder();
            for (int i = 0; i < 26; i++){
                key.append(count[i]);
            }
            String key2 = key.toString();
            if (!map.containsKey(key2)){
                map.put(key2, new ArrayList<String>());
                res.add(map.get(key2));
            }
            map.get(key2).add(s);
        }

        return res;
    }
}

