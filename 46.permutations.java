import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.*;

/*
 * @lc app=leetcode id=46 lang=java
 *
 * [46] Permutations
 */
class Solution {
    /**
     * 回溯法，丑陋做法，好看做法参见Q47
     */
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        List<Integer> permutation = new ArrayList<>();
        backtrack(nums, permutation);
        return res;
    }

    public void backtrack(int[] nums, List<Integer> permutation){
        if (permutation.size() == nums.length){
            res.add(new ArrayList<Integer>(permutation));
        }
        else {
            for (int i = 0; i < nums.length; i++){
                int num = nums[i];
                if (!permutation.contains(num)){
                    permutation.add(num);
                    backtrack(nums, permutation);
                    permutation.remove(new Integer(num));
                }
            }
        }
    }
}

