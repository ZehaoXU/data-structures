import java.util.Arrays;

/*
 * @lc app=leetcode id=740 lang=java
 *
 * [740] Delete and Earn
 *
 * https://leetcode.com/problems/delete-and-earn/description/
 *
 * algorithms
 * Medium (46.45%)
 * Likes:    650
 * Dislikes: 56
 * Total Accepted:    24.4K
 * Total Submissions: 52.6K
 * Testcase Example:  '[3,4,2]'
 *
 * Given an array nums of integers, you can perform operations on the array.
 * 
 * In each operation, you pick any nums[i] and delete it to earn nums[i]
 * points. After, you must delete every element equal to nums[i] - 1 or nums[i]
 * + 1.
 * 
 * You start with 0 points. Return the maximum number of points you can earn by
 * applying such operations.
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [3, 4, 2]
 * Output: 6
 * Explanation: 
 * Delete 4 to earn 4 points, consequently 3 is also deleted.
 * Then, delete 2 to earn 2 points. 6 total points are earned.
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [2, 2, 3, 3, 3, 4]
 * Output: 9
 * Explanation: 
 * Delete 3 to earn 3 points, deleting both 2's and the 4.
 * Then, delete 3 again to earn 3 points, and 3 again to earn 3 points.
 * 9 total points are earned.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The length of nums is at most 20000.
 * Each element nums[i] is an integer in the range [1, 10000].
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 排序后，类似house robber的题目，需判断newNum和oldNum是否相等？是否加一关系？
     * TC O(n*logn) sorting; SC O(n) merge sort
     * Your runtime beats 78.62 % of java submissions
     * Your memory usage beats 50 % of java submissions (39.1 MB)
     * 
     * 其他方法：也是dp，不过不用排序。因为题目给了数字范围，所以可以建一个比他大的数组，index表示数字，其中存放count或者相同数字和，最后和house robber完全相同，遍历数组即可
     *  相同类型方法还可用tree map，记录数字和count且自动排序
     * @param nums
     * @return
     */
    public int deleteAndEarn(int[] nums) {
        if (nums.length == 0 || nums == null)   return 0;
        Arrays.sort(nums);
        int prevNum = nums[0];
        int include = 0, exclude = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == prevNum) {
                include += nums[i];
                continue;
            }
            if (nums[i] == prevNum+1) {
                int temp = include;
                include = exclude + nums[i];
                exclude = Math.max(temp, exclude);
                prevNum = nums[i];
            }
            else {
                exclude = Math.max(include, exclude);
                include = exclude + nums[i];
                prevNum = nums[i];
            }
        }
        return Math.max(include, exclude);
    }
}

