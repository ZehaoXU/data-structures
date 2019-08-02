import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.*;

/*
 * @lc app=leetcode id=90 lang=java
 *
 * [90] Subsets II
 */
class Solution {
    /**
     * dfs, visited标记重复元素是否出现
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 96.71 % of java submissions (37.6 MB)
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int[] visited = new int[nums.length];
        Arrays.sort(nums);
        dfs(nums, res, visited, new ArrayList<>(), 0);
        return res;
    }

    public void dfs(int[] nums, List<List<Integer>> res, int[] visited, List<Integer> combination, int start){
        int i = start;
        
        while (i < nums.length){
            if (i == 0 || visited[i-1] == 1 || nums[i] != nums[i-1]){
                combination.add(nums[i]);
                res.add(new ArrayList<>(combination));
                visited[i] = 1;
                dfs(nums, res, visited, combination, i+1);
                combination.remove(combination.size()-1);
                visited[i] = 0;
            }
            i++;
        }
    }
}

