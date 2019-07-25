import java.util.Arrays;

/*
 * @lc app=leetcode id=31 lang=java
 *
 * [31] Next Permutation
 */
class Solution {
    /**
     * 题意解读：找到排列组合中最接近它并比他大的数字 123,132,213,231,312,321,如果没有就升序排列321->123
     * 从后向前，找到第一个降序对i-1,i，然后从后向前（升序排列）寻找第一个比nums[i-1]大的数字，交换后[i,len-1]序列仍然是升序排列，只需颠倒顺序改成从前到后的升序排列即可
     * 时间复杂度 O(n) 最坏情况大概O(3n)；空间复杂度 O(1);
     * Your runtime beats 92.88 % of java submissions
     * Your memory usage beats 52.51 % of java submissions (39.9 MB)
     * 
     * 注意：从后向前找第一个降序对，说明之前遍历过的都是升序排列！swap后仍然是升序排列！
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int i = len - 1;
        
        for (; i > 0; i--){
            // 找到第一个升序对，反向寻找
            if (nums[i-1] < nums[i]){
                int j = len - 1;
                while (j >= i && nums[j] <= nums[i-1]){
                    j--;
                }
                swap(nums, i-1, j);
                break;
            }
        }
        reverse(nums, i, len - 1);
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int i, int j){
        while (i < j){
            swap(nums, i, j);
            i++;
            j--;
        }
    }
}

