/*
 * @lc app=leetcode id=41 lang=java
 *
 * [41] First Missing Positive
 */
class Solution {
    /**
     * 要求O(n)，如果排序后很好解决，想到桶排序bucket sort，不能用额外空间所以必须在位完成，使用swap或者重新赋值实现
     * 桶不用无限多个，n长度的数组返回答案[1,n+1]，所以大于n的数字可以不处理，[0,n-1]的桶装[1,n]的数，最后一次遍历只要找哪个桶里装的东西不符合要求即可
     * 
     * 时间复杂度 O(n); 空间复杂度 O(1);
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 99.25 % of java submissions (34.7 MB)
     * @param nums
     * @return
     */
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length){
            if (nums[i] <= 0 || nums[i] > nums.length){
                i++;
            }
            else if (nums[i] != i+1 && nums[nums[i]-1] != nums[i]){
                swap(nums, i, nums[i]-1);
            }
            else    i++;
        }
        for (i = 0; i < nums.length; i++){
            if (nums[i] != i+1)
                return i+1;
        }
        return nums.length+1;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

