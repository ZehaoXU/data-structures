import java.util.ArrayList;
import java.util.List;
import java.lang.*;

/*
 * @lc app=leetcode id=216 lang=java
 *
 * [216] Combination Sum III
 */
class Solution {
    /**
     * dfs，最后都能到k个数但不一定能加起来是n
     * 通过合理剪枝来提升效率
     * 时间复杂度 O(n!) k排列；空间复杂度 O(n!)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 6.78 % of java submissions (33.8 MB)
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        if (k<0 || n<0) return res;
        dfs(k, n, 1, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int k, int target, int start, List<Integer> combination, List<List<Integer>> res){
        if (k == 0){
            if (target == 0){
                res.add(new ArrayList<>(combination));
            }
            return;
        }

        for (int i = start; i <= 9; i++){
            if (i > target) break;
            combination.add(i);
            dfs(k-1, target-i, i+1, combination, res);
            combination.remove(combination.size()-1);
        }
    }
}

