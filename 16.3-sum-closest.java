import java.util.Arrays;

/*
 * @lc app=leetcode id=16 lang=java
 *
 * [16] 3Sum Closest
 */
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2];
        int distance = Math.abs(target - closestSum);

        for (int i = 0; i < nums.length-2; i++){
            if (i == 0 || nums[i] != nums[i-1]){
                int l = i+1, r = nums.length-1;
                while (l < r){
                    int sum = nums[i] + nums[l] + nums[r];
                    int currDistance = sum - target;
                    if (distance > Math.abs(currDistance)){
                        closestSum = sum;
                        distance = Math.abs(currDistance);
                    }

                    if (currDistance > 0){
                        r--;
                        while (nums[r] == nums[r+1] && r > l)    r--;
                    }
                    else if (currDistance < 0){
                        l++;
                        while (nums[l] == nums[l-1] && r > l)    l++;
                    }
                    else return sum;
                }
            }
        }
        return closestSum;
    }
}

