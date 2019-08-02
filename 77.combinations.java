/*
 * @lc app=leetcode id=77 lang=java
 *
 * [77] Combinations
 */
class Solution {
    /**
     * dfs，combination问题
     * 通过合理剪枝，将时间从27ms减少至3ms
     * 时间复杂度 O(n!); 空间复杂度 O(n! + n) 用了一个combination来装数字
     * Your runtime beats 87.21 % of java submissions
     * Your memory usage beats 71.35 % of java submissions (39.3 MB)
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n<=0 || k<=0 || n < k)   return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        dfs(1, n, k, new ArrayList<Integer>(),res);
        return res;
    }

    public void dfs(int start, int end, int k, List<Integer> combination, List<List<Integer>> res){
        if (combination.size() == k){
            res.add(new ArrayList<>(combination));
            return;
        }
        // 下面一行语句剪枝，时间从27ms提升到3ms
        if (end - start + 1 < k - combination.size()) return;
        for (int i = start; i <= end; i++){
            combination.add(i);
            dfs(i+1,end,k,combination,res);
            combination.remove(combination.size()-1);
        }
    }
}

