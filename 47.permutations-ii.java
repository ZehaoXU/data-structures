import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
/*
 * @lc app=leetcode id=47 lang=java
 *
 * [47] Permutations II
 */
class Solution {
    /**
     * 聪明回溯法，为了防止同一数字多次使用及相同数字问题，引入int[] visited，保证递归同一层相同数字只出现一次，递归不同层不出现同一数字
     * 还可用 int[] count来计数，达到相同效果
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 73.85 % of java submissions (39.3 MB)
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        int[] visited = new int[nums.length];
        Arrays.sort(nums);
        List<Integer> permutation = new ArrayList<>();
        List<List<Integer>> permutations = new ArrayList<>();
        
        backtrack(nums, visited, permutation, permutations);
        return permutations;
    }

    public void backtrack(int[] nums, int[] visited, List<Integer> permutation, List<List<Integer>> permutations){
        if (permutation.size() == nums.length){
            permutations.add(new ArrayList<Integer>(permutation));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (visited[i] == 0){
                if (i == 0 || visited[i-1] != 0 || nums[i-1] != nums[i]){
                    visited[i] = 1;
                    permutation.add(nums[i]);
                    backtrack(nums, visited, permutation, permutations);
                    visited[i] = 0;
                    permutation.remove(permutation.size()-1);
                }
            }
        }
    }
}

