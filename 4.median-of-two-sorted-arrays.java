/*
 * @lc app=leetcode id=4 lang=java
 *
 * [4] Median of Two Sorted Arrays
 *
 * https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 *
 * algorithms
 * Hard (26.32%)
 * Likes:    4279
 * Dislikes: 565
 * Total Accepted:    433.9K
 * Total Submissions: 1.6M
 * Testcase Example:  '[1,3]\n[2]'
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity
 * should be O(log (m+n)).
 * 
 * You may assume nums1 and nums2 cannot be both empty.
 * 
 * Example 1:
 * 
 * 
 * nums1 = [1, 3]
 * nums2 = [2]
 * 
 * The median is 2.0
 * 
 * 
 * Example 2:
 * 
 * 
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 
 * The median is (2 + 3)/2 = 2.5
 * 
 * 
 */
class Solution {
    /**
     * 好题，好难。核心是理解中位数的本质：右边数都大于左边
     * 条件:
     *  len(nums1_left + nums2_left) = len(nums1_right + nums2_right)
     *  max(nums1/nums2_left) <= min(nums1/nums2_right)
     * 但是到了程序中，g(m)只验证了 nums1[m1] >= nums2[m2]，细细想来是有道理的，不过自己写估计写不出来
     * 时间复杂度 O(logMin) Min = min(n1,n2)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 93.06 % of java submissions (45.8 MB)
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        if (n1 > n2)    return findMedianSortedArrays(nums2, nums1);

        int k = (n1+n2+1) / 2;
        int l = 0, r = n1;
        while (l < r) {
            int m1 = l + (r-l)/2;
            int m2 = k - m1;
            if (m2 > 0 && nums1[m1] >= nums2[m2-1]) {
                r = m1;
            }
            else {
                l = m1+1;
            }
        }
        int m1 = l;
        int m2 = k - l;
        int res1 = Math.max(m1>0 ? nums1[m1-1] : Integer.MIN_VALUE, 
                            m2>0 ? nums2[m2-1] : Integer.MIN_VALUE);
        if ((n1+n2)%2 != 0) return (double) res1;

        int res2 = Math.min(m1<n1 ? nums1[m1] : Integer.MAX_VALUE, 
                            m2 < n2 ? nums2[m2] : Integer.MAX_VALUE);
        
        return (res1+res2)*0.5;
    }
}

