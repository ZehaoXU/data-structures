/*
 * @lc app=leetcode id=53 lang=java
 *
 * [53] Maximum Subarray
 */
class SolutionFirst {
    /**
     * 动态规划，dp[i]表示以i结尾的和最大字串
     * 时间复杂度 O(n), 空间复杂度 O(1) (没有建立dp表，遍历一次即可)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 87.06 % of java submissions (38.4 MB)
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int maxsum = nums[0];
        int cursum = nums[0];
        for (int i = 1; i < nums.length; i++){
            cursum = cursum>0 ? cursum + nums[i] : nums[i];
            maxsum = Math.max(cursum, maxsum);
        }
        return maxsum;
    }
}

class SolutionSecond {
    /**
     * 线性dp，记录i结尾的max subarray，同步刷新最大值。由于只和前一个值有关，所以可以压缩空间
     * TC O(n); SC O(1)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 99.06 % of java submissions (37.6 MB)
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        int prevMax = nums[0];
        int res = prevMax;
        for (int i = 1; i < n; i++) {
            int currMax = nums[i];
            if (prevMax > 0)
                currMax += prevMax;
            if (currMax > res)  res = currMax;
            prevMax = currMax;
        }
        return res;
    }
}

class Solution {
    /**
     * 分治法！对于任意一个字串，返回最左边开始的maxSubVal，最右边开始的maxSubVal，以及array本身的和，供上层使用。同时更新maxValue，可能为三种情况：max是某字串最左边开始的maxSubVal，是某字串最右边的maxSubVal，是某字串中间（横跨中点）的maxSubVal。根据下层返回的结果都可以轻松计算，更新max，并且向上层继续返回
     * TC O(n) 自底向上一次; SC O(logn)
     * Your runtime beats 86.96 % of java submissions
     * Your memory usage beats 5.16 % of java submissions (41.5 MB)
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        _max = nums[0];
        divideAndConquer(nums, 0, nums.length-1);
        return _max;
    }
    private int _max;
    // return 最左起步maxSubArray，最右起步maxSubArray，array本身求和
    private int[] divideAndConquer(int[] nums, int l, int r) {
        if (l == r) return new int[]{nums[l], nums[l], nums[l]};

        int m = l + (r-l)/2;
        int[] lRes = divideAndConquer(nums, l, m);
        int[] rRes = divideAndConquer(nums, m+1, r);
        int[] res = new int[3];
        res[0] = Math.max(lRes[0], lRes[2]+rRes[0]);
        res[1] = Math.max(rRes[1], rRes[2]+lRes[1]);
        res[2] = lRes[2] + rRes[2];
        for (int num : res) 
            if (num > _max) _max = num;
        if (lRes[1] + rRes[0] > _max)   _max = lRes[1] + rRes[0];

        return res;
    }
}

