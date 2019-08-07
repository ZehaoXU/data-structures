/*
 * @lc app=leetcode id=875 lang=java
 *
 * [875] Koko Eating Bananas
 *
 * https://leetcode.com/problems/koko-eating-bananas/description/
 *
 * algorithms
 * Medium (46.65%)
 * Likes:    327
 * Dislikes: 35
 * Total Accepted:    15.1K
 * Total Submissions: 32.4K
 * Testcase Example:  '[3,6,7,11]\n8'
 *
 * Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has
 * piles[i] bananas.  The guards have gone and will come back in H hours.
 * 
 * Koko can decide her bananas-per-hour eating speed of K.  Each hour, she
 * chooses some pile of bananas, and eats K bananas from that pile.  If the
 * pile has less than K bananas, she eats all of them instead, and won't eat
 * any more bananas during this hour.
 * 
 * Koko likes to eat slowly, but still wants to finish eating all the bananas
 * before the guards come back.
 * 
 * Return the minimum integer K such that she can eat all the bananas within H
 * hours.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: piles = [3,6,7,11], H = 8
 * Output: 4
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: piles = [30,11,23,4,20], H = 5
 * Output: 30
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: piles = [30,11,23,4,20], H = 6
 * Output: 23
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= piles.length <= 10^4
 * piles.length <= H <= 10^9
 * 1 <= piles[i] <= 10^9
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 二分查找，需要构建一个比较复杂的g(m)，建议写成一个函数，结构更清晰
     * 时间复杂度 O(N*logP) N是piles的数量，P是piles中最大数字
     * Your runtime beats 46.44 % of java submissions
     * Your memory usage beats 100 % of java submissions (39 MB)
     * @param piles
     * @param H
     * @return
     */
    public int minEatingSpeed(int[] piles, int H) {
        int max = 0;
        for (int i = 0; i < piles.length; i++) {
            max = Math.max(max, piles[i]);
        }
        int l = 1, r = max+1;
        while (l < r) {
            int m = l + (r-l)/2;
            // is valid
            int h = 0;
            for (int pile : piles) {
                h += (pile-1)/m + 1;
            }
            if (h <= H) {
                r = m;
            }
            else {
                l = m+1;
            }
        }

        return l;
    }
}

