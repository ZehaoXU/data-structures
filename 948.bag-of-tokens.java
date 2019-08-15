/*
 * @lc app=leetcode id=948 lang=java
 *
 * [948] Bag of Tokens
 *
 * https://leetcode.com/problems/bag-of-tokens/description/
 *
 * algorithms
 * Medium (39.55%)
 * Likes:    106
 * Dislikes: 94
 * Total Accepted:    7.1K
 * Total Submissions: 18K
 * Testcase Example:  '[100]\n50'
 *
 * You have an initial power P, an initial score of 0 points, and a bag of
 * tokens.
 * 
 * Each token can be used at most once, has a value token[i], and has
 * potentially two ways to use it.
 * 
 * 
 * If we have at least token[i] power, we may play the token face up, losing
 * token[i] power, and gaining 1 point.
 * If we have at least 1 point, we may play the token face down, gaining
 * token[i] power, and losing 1 point.
 * 
 * 
 * Return the largest number of points we can have after playing any number of
 * tokens.
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
 * Input: tokens = [100], P = 50
 * Output: 0
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: tokens = [100,200], P = 150
 * Output: 1
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: tokens = [100,200,300,400], P = 200
 * Output: 2
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * tokens.length <= 1000
 * 0 <= tokens[i] < 10000
 * 0 <= P < 10000
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 题意比较难理解，理解好了就没那么难。greedy+two pointer，排序，从最小的元素开始获得points，从最大的元素开始获得power
     * 时间复杂度 O(n*logn)
     * Your runtime beats 99.77 % of java submissions
     * Your memory usage beats 100 % of java submissions (39.4 MB)
     * @param tokens
     * @param P
     * @return
     */
    public int bagOfTokensScore(int[] tokens, int P) {
        if (tokens == null || tokens.length == 0)    return 0;
        Arrays.sort(tokens);
        int points = 0;
        int l = 0, r = tokens.length-1;
        int res = 0;
        while (l<=r) {
            if (P >= tokens[l]) {
                P -= tokens[l];
                points++;
                res = Math.max(points, res);
                l++;
            }
            else if (points > 0) {
                P += tokens[r];
                points--;
                r--;
            }
            else    break;
        }
        return res;
    }
}

