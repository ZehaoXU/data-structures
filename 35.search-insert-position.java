/*
 * @lc app=leetcode id=35 lang=java
 *
 * [35] Search Insert Position
 */
class Solution {
    /**
     * 非递归二分查找
     * 时间复杂度 O(logn); 空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 97.71 % of java submissions (38.5 MB)
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left < right){
            int mid = (left + right) / 2;
            if (nums[mid] == target)
                return mid;
            else if (target < nums[mid]){
                right = mid;
            }
            else{
                left = mid+1;
            }
        }
        if (nums[left] >= target)
            return left;
        else    
            return left + 1;
    }
}

class SolutionRecursive {
    /**
     * 二分法查找，递归结构，注意递归出口的判断left==right，此时只有一个最接近/等于target的元素
     * 时间复杂度 O(logn); 空间复杂度 O(logn)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 98.68 % of java submissions (38 MB)
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        return bsearch(nums, 0, nums.length-1, target);
    }

    public int bsearch(int[] nums, int left, int right, int target){
        int mid = (left + right) / 2;
        if(nums[mid] == target)
            return mid; 
        if(left == right){
            if (nums[left] >= target)
                return left;
            else
                return left + 1;
        }
        else if(target > nums[mid]){
            return bsearch(nums, mid+1, right, target);
        }
        else{
            return bsearch(nums, left, mid, target);
        }
    }
}

