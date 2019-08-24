/*
 * @lc app=leetcode id=121 lang=java
 *
 * [121] Best Time to Buy and Sell Stock
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
 *
 * algorithms
 * Easy (47.93%)
 * Likes:    3000
 * Dislikes: 136
 * Total Accepted:    565.2K
 * Total Submissions: 1.2M
 * Testcase Example:  '[7,1,5,3,6,4]'
 *
 * Say you have an array for which the i^th element is the price of a given
 * stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (i.e., buy
 * one and sell one share of the stock), design an algorithm to find the
 * maximum profit.
 * 
 * Note that you cannot sell a stock before you buy one.
 * 
 * Example 1:
 * 
 * 
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit
 * = 6-1 = 5.
 * Not 7-1 = 6, as selling price needs to be larger than buying price.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 * 
 * 
 */
class Solution {
    /**
     * 实际上是压缩后的线性dp
     * 普通线性dp 每个index保存两个值：目前minValue，目前maxGain=max(Gain(i-1), prices-minValue)
     * 压缩后，用全局变量保存minValue 以及maxGain
     * TC O(n); SC O(1)
     * Your runtime beats 88.83 % of java submissions
     * Your memory usage beats 98.67 % of java submissions (37.6 MB)
     * 
     * 其他方法：
     *  prices[i]-prices[i-1] 组成的数组表示每天的gain，可以变成maximum subarray问题！
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length == 0 || prices == null)   return 0;
        int currMin = prices[0];
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, prices[i] - currMin);
            currMin = Math.min(currMin, prices[i]);
        }

        return res;
    }
}

