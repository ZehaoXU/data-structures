/*
 * @lc app=leetcode id=307 lang=java
 *
 * [307] Range Sum Query - Mutable
 *
 * https://leetcode.com/problems/range-sum-query-mutable/description/
 *
 * algorithms
 * Medium (29.55%)
 * Likes:    764
 * Dislikes: 62
 * Total Accepted:    76.6K
 * Total Submissions: 258.8K
 * Testcase Example:  '["NumArray","sumRange","update","sumRange"]\n[[[1,3,5]],[0,2],[1,2],[0,2]]'
 *
 * Given an integer array nums, find the sum of the elements between indices i
 * and j (i ≤ j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i
 * to val.
 * 
 * Example:
 * 
 * 
 * Given nums = [1, 3, 5]
 * 
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 
 * 
 * Note:
 * 
 * 
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is
 * distributed evenly.
 * 
 * 
 */
class NumArray {
    /**
     * 使用树状数组解决，维护并查询前序和，注意nums[i] -> sum[i+1]
     * 时间复杂度 O(logn)；空间复杂度 O(n)
     * Your runtime beats 55.96 % of java submissions
     * Your memory usage beats 100 % of java submissions (44.9 MB)
     * 
     * 其他方法：线段树
     */
    private int[] sum;
    private int N;
    private int[] _nums;

    public NumArray(int[] nums) {
        N = nums.length;
        sum = new int[N+1];
        _nums = new int[N];
        for (int i = 0; i < N; i++) {
            update(i, nums[i]);
        }
    }
    // val is delta = newVal - oldVal
    public void update(int i, int val) {
        int delta = val - _nums[i];
        _nums[i] = val;
        i++;
        while (i <= N) {
            sum[i] += delta;
            i += i & -i;
        }
    }
    
    public int sumRange(int i, int j) {
        int res = query(j+1) - query(i);
        
        return res;
    }

    public int query(int i) {
        int res = 0;
        while (i > 0) {
            res += sum[i];
            i -= i & -i;
        }
        return res;
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

