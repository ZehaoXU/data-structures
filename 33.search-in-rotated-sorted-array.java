/*
 * @lc app=leetcode id=33 lang=java
 *
 * [33] Search in Rotated Sorted Array
 */
class Solution {
    /**
     * 二分查找，循环不变量特别难确定，还是用左闭右开比较好理解和确定gm函数
     * 
     * 其他方法：
     *  先确定rotated index，然后分成左右两个序列，进行二分查找；rotated index也是通过二分法找到的，找到最小的数字即可
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length-1;
        while (l < r) {
            int mid = l + (r-l)/2;
            if (nums[l] <= nums[mid]) { // left is sorted
                if (target >= nums[l] && target <= nums[mid]) {
                    r = mid;
                }
                // target > nums[mid]
                else {l = mid + 1;}
            }
            else { // right is sorted
                if (target > nums[mid] && target <= nums[r]) {
                    l = mid + 1;
                }
                else {
                    r = mid;
                }
            }
        }
        if (l == nums.length || nums[l] != target)  return -1;
        return l;
    }
}

class SolutionFist {
    /**
     * 递归，类似binarySearch，不过需要判断要在哪一个分支进行下次搜索，借助总有一边是有序序列来判断
     * 时间复杂度 O(logn);  空间复杂度 O(logn)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 5.01 % of java submissions (40.8 MB)
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        return bsearch(nums, 0, nums.length-1, target);
    }

    public int bsearch(int[] nums, int left, int right, int target){
        int res;
        if (left > right)   return -1;
        int mid = (left + right) / 2;
        if (nums[mid] == target)  return mid;
        if (left == right)  return -1;
        // all sorted
        if (nums[left] < nums[right]){
            if (target <= nums[mid]){
                res = bsearch(nums, left, mid, target);
            }
            else{
                res = bsearch(nums, mid+1, right, target);
            }
        }
        // left is sorted, right is unsorted
        else if  (nums[left] < nums[mid]){
            if (target >= nums[left] && target <= nums[mid]){
                res = bsearch(nums, left, mid, target);
            }
            else{
                res = bsearch(nums, mid+1, right, target);
            }
        }
        // right is sorted, left is unsorted
        else{
            if (target <= nums[right] && target >= nums[mid+1]){
                res = bsearch(nums, mid+1, right, target);
            }
            else{
                res = bsearch(nums, left, mid, target);
            }
        }
        return res;
    }
}

