import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=22 lang=java
 *
 * [22] Generate Parentheses
 */

class Solution {
    List<String> res = new ArrayList<String>();
    /**
     * 回溯法遍历所有情况，注意左括号数量必须多于右括号
     * 
     * 回溯法时间效率计算：O(b^d) b表示树的分支，d表示每支的最大深度，因为回溯必须所有节点全部遍历 exhaustive
     * 
     * 还有一种解法，递归的把所有情况表示出来
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        if (n != 0){
            backtrack("(", n-1, n);
        }
        return res;
    }

    public void backtrack(String combination, int leftNum, int rightNum){
        if (leftNum == 0){
            for (int i = 0; i < rightNum; i++){
                combination += ")";
            }
            res.add(combination);
        }
        else {
            backtrack(combination+"(", leftNum-1 , rightNum);
            if (leftNum < rightNum)
                backtrack(combination+")", leftNum, rightNum-1);
        }
    }
}

