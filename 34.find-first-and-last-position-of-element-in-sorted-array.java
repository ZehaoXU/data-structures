/*
 * @lc app=leetcode id=34 lang=java
 *
 * [34] Find First and Last Position of Element in Sorted Array
 */
class Solution {
    /**
     * 二分查找变式，根据mid与target每次划分空间的不同，两次二分查找分别确定左右边界，第一次还可确定是否存在；注意右侧边界需要判断找到的是target还是比target大的第一个数
     * 因为mid始终包含在左半边搜索中！
     * 时间复杂度 O(logn); 空间复杂度 O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 100 % of java submissions (39 MB)
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};

        if (nums.length == 0)   return  res;
        int left = 0, right = nums.length-1;
        // find left index
        while (left < right){
            int mid = (left + right) / 2;
            if (target <= nums[mid])
                right = mid;
            else    
                left = mid + 1;
        }
        if (nums[left] == target){
            res[0] = left;
            left = 0;
            right = nums.length-1;
        }
        else    return res;

        // find right index
        while (left < right){
            int mid = (left + right) / 2;
            if (target >= nums[mid])
                left = mid + 1;
            else   
                right = mid;
        }
        if (nums[right] == target) 
            res[1] = right;
        else    res[1] = right-1;

        return res;
    }
}

