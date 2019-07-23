import java.util.ArrayList;
import java.util.Arrays;

/*
 * @lc app=leetcode id=18 lang=java
 *
 * [18] 4Sum
 */
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        int len = nums.length;
        // locate first element
        for (int i = 0; i < len-3; i++){
            if (i == 0 || nums[i] != nums[i-1]){
                // locate second element
                for (int j = i+1; j < len-2; j++){
                    if (j == i+1 || nums[j] != nums[j-1]){
                        // two pointers
                        int l = j+1, r = len-1;
                        while (r > l){
                            int sum = nums[i] + nums[j] + nums[l] + nums[r];
                        
                            if (sum > target){
                                r--;
                                // while (nums[r] == nums[r+1] && r > l)   r--;
                            }
                            else if (sum < target){
                                l++;
                                // while (nums[l] == nums[l-1] && r > l)   l++;
                            }
                            else {
                                result.add(new ArrayList<Integer>(Arrays.asList(nums[i], nums[j], nums[l], nums[r])));
                                r--;
                                while (nums[r] == nums[r+1] && r > l)   r--;
                                l++;
                                while (nums[l] == nums[l-1] && r > l)   l++;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}

