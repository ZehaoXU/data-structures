import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.lang.*;

/*
 * @lc app=leetcode id=40 lang=java
 *
 * [40] Combination Sum II
 */
class Solution {
    /**
     * 聪明回溯法，有界的旅行商问题，这次用count实现，熟悉HashMap操作，相比之下还是visited效率更高
     * Your runtime beats 67.51 % of java submissions
     * Your memory usage beats 53.87 % of java submissions (39.2 MB)
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : candidates){
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        int[] count = new int[map.size()];
        int[] nums = new int[map.size()];
        int i = 0;
        for (Integer key : map.keySet()){
            nums[i] = key;
            count[i++] = map.get(key);
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();

        backtrack(nums, count, combination, res, target, 0);
        return res;
    }

    public void backtrack(int[] nums, int[] count, List<Integer> combination, List<List<Integer>> res, int target, int index){
        for (int i = index; i < nums.length; i++){
            if (count[i] != 0){
                if (nums[i] == target){
                    combination.add(nums[i]);
                    res.add(new ArrayList<>(combination));
                    combination.remove(combination.size()-1);
                }
                else if (nums[i] > target)
                    continue;
                else {
                    combination.add(nums[i]);
                    count[i]--;
                    backtrack(nums, count, combination, res, target-nums[i], i);
                    combination.remove(combination.size()-1);
                    count[i]++;
                }
            }
        }
    }
}

