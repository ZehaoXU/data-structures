/*
 * @lc app=leetcode id=278 lang=java
 *
 * [278] First Bad Version
 *
 * https://leetcode.com/problems/first-bad-version/description/
 *
 * algorithms
 * Easy (30.68%)
 * Likes:    714
 * Dislikes: 435
 * Total Accepted:    239.5K
 * Total Submissions: 779.4K
 * Testcase Example:  '5\n4'
 *
 * You are a product manager and currently leading a team to develop a new
 * product. Unfortunately, the latest version of your product fails the quality
 * check. Since each version is developed based on the previous version, all
 * the versions after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the
 * first bad one, which causes all the following ones to be bad.
 * 
 * You are given an API bool isBadVersion(version) which will return whether
 * version is bad. Implement a function to find the first bad version. You
 * should minimize the number of calls to the API.
 * 
 * Example:
 * 
 * 
 * Given n = 5, and version = 4 is the first bad version.
 * 
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * 
 * Then 4 is the first bad version. 
 * 
 */
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    /**
     * 二分查找，有个问题是从range[1,n+1)查找，但是n+1会溢出，故直接从[1,n)查找，如果没查到就肯定是n
     * 时间复杂度 O(n); 空间复杂度 O(1)
     * Your runtime beats 99.5 % of java submissions
     * Your memory usage beats 100 % of java submissions (33.1 MB)
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while (l < r) {
            int m = l + (r-l)/2;
            if (isBadVersion(m)) {
                r = m;
            }
            else {
                l = m+1;
            }
        }
        return l;
    }
}

