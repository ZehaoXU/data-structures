import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/*
 * @lc app=leetcode id=26 lang=java
 *
 * [26] Remove Duplicates from Sorted Array
 */
class Solution {
    /**
     * 双指针，一个指针遍历数组，另一个指针标定新元素位置，同时计数
     * 时间复杂度 O(n)
     * Your runtime beats 98.01 % of java submissions
     * Your memory usage beats 99.94 % of java submissions (39.2 MB)
     * 
     * 尝试用Set解决问题，但似乎太麻烦，而且Integer/int/Set/List/Array转换出现问题，需要好好学学Collection
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)   return 0;
        int count = 0;
        for (int i = 1; i < nums.length; i++){
            if (nums[count] != nums[i]){
                nums[++count] = nums[i];
            }
        }
        return count + 1;
    }
}

