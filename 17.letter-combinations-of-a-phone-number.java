import java.util.ArrayList;
import java.util.Map;

/*
 * @lc app=leetcode id=17 lang=java
 *
 * [17] Letter Combinations of a Phone Number
 */
class Solution {
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

