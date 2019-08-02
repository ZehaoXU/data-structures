import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 */
class Solution {
    /**
     * bfs，由于存储了大量的中间值，产生了很多不必要的中间字符串引用，故应该差于dfs
     * 时间复杂度 O(4^n)，空间复杂度 O(2*4^n) 有res和temp两个滚动数组
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 98.79 % of java submissions (36.1 MB)
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0)   return new ArrayList<>();

        char[][] phone = new char[10][];
        phone[0] = new char[]{};
        phone[1] = new char[]{};
        phone[2] = new char[]{'a','b','c'};
        phone[3] = new char[]{'d','e','f'};
        phone[4] = new char[]{'g','h','i'};
        phone[5] = new char[]{'j','k','l'};
        phone[6] = new char[]{'m','n','o'};
        phone[7] = new char[]{'p','q','r','s'};
        phone[8] = new char[]{'t','u','v'};
        phone[9] = new char[]{'w','x','y','z'};

        List<String> res = new ArrayList<>();
        res.add("");
        List<String> temp = new ArrayList<>();

        for (char digit : digits.toCharArray()){
            for (char c : phone[digit-'0']){
                for (String s : res){
                    temp.add(s+c);
                }
            }
            res = temp;
            temp = new ArrayList<>();
        }
        return res;
    }
}

 class SolutionFirstTime {
    List<String> resultList = new ArrayList<String>();
    
    public List<String> letterCombinations(String digits) {
        if (digits.length() != 0){
            backtrack("", digits);
        }
        return resultList;
    }
    
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};

    public void backtrack(String combination, String digits){
        if (digits.length() == 0){
            resultList.add(combination);
        }

        else {
            String num = digits.substring(0, 1);
            String letters = phone.get(num);
            for (int i=0; i < letters.length(); i++){
                String letter = letters.substring(i, i+1);
                backtrack(combination + letter, digits.substring(1));
            }
        }
    }
}

