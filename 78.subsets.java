import java.util.ArrayList;
import java.util.List;
import java.lang.*;

/*
 * @lc app=leetcode id=78 lang=java
 *
 * [78] Subsets
 */
class Solution {
    /**
     * dfs，不用大量new，效率高于bfs
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 98.16 % of java submissions (37.5 MB)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        dfs(nums, res, new ArrayList<Integer>(), 0);
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> combination, int start){
        for (int i = start; i < nums.length; i++){
            combination.add(nums[i]);
            res.add(new ArrayList<>(combination));
            dfs(nums, res, combination, i+1);
            combination.remove(combination.size()-1);
        }
    }
}

class SolutionBFS {
    /**
     * bfs，因为每个中间过程都是结果的一部分所以想到bfs; 存在大量的新arrayList开空间，所以效率比较慢
     * 时间复杂度 O(2^n); 空间复杂度 O(2*2^n)
     * Your runtime beats 45.3 % of java submissions
     * Your memory usage beats 98.48 % of java submissions (37.3 MB)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<Integer>());
        bfs(nums, res, 0);
        return res;
    }

    public void bfs(int[] nums, List<List<Integer>> res, int start){
        if (start >= nums.length)   return;
        for (; start < nums.length; start++){
            List<List<Integer>>  temp = new ArrayList<>();
            for (List<Integer> list : res){
                List<Integer> newList = new ArrayList<>(list);
                newList.add(nums[start]);
                temp.add(newList);
            }
            res.addAll(temp);
        }
    }
}

