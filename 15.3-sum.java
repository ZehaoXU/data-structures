import java.util.ArrayList;
import java.util.Arrays;

/*
 * @lc app=leetcode id=15 lang=java
 *
 * [15] 3Sum
 */
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);

        for (int i = 0; i < nums.length-2; i++){
            if (i > 0){
                while (nums[i] == nums[i-1] && i < nums.length-2)   i++;
            }
            int l = i + 1, r = nums.length - 1;
            int sum = 0;

            while (l < r){
                sum = nums[i] + nums[l] + nums[r];
                if (sum > 0){
                    r--;
                    while (nums[r+1] == nums[r] && r > l)    r--;
                }
                else if (sum < 0){
                    l++;
                    while (nums[l-1] == nums[l] && r > l)    l++;
                }
                else {
                    ans.add(new ArrayList<>(Arrays.asList(nums[i], nums[l], nums[r])));
                    r--;
                    l++;
                    while (nums[l] == nums[l-1] && l < r)    l++;
                    while (nums[r] == nums[r+1] && l < r)    r--;
                }
            }
        }
        return ans;
    }
}

