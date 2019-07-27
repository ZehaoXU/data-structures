import java.lang.*;
import java.util.ArrayList;
import java.util.List;
/*
 * @lc app=leetcode id=39 lang=java
 *
 * [39] Combination Sum
 */
class Solution {
    /**
     * 聪明回溯法
     * 存在问题：无序，不能重复[1,1,2,4] and [1,2,1,4]
     * 解决：传入start参数，树的枝叶index只能大于等于树根index；同时由于无序，不能找到就返回，必须找全！
     * Your runtime beats 99.86 % of java submissions
     * Your memory usage beats 98.89 % of java submissions (38.1 MB)
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        
        backtrack(candidates, target, combination, res, 0);
        return res;
    }

    public void backtrack(int[] nums, int target, List<Integer> combination, List<List<Integer>> res, int start){
        if (start >= nums.length)
            return;

        for (int i = start; i < nums.length; i++){
            if (target == nums[i]){
                combination.add(nums[i]);
                res.add(new ArrayList<Integer>(combination));
                combination.remove(combination.size()-1);
                // return; 这里不能返回，因为无序，后面的数也有可能
            }
            else if (nums[i] > target)
                continue;
            else {
                combination.add(nums[i]);
                backtrack(nums, target-nums[i], combination, res, i);
                combination.remove(combination.size()-1);
            }
        }
    }
}

