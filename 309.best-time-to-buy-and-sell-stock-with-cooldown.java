/*
 * @lc app=leetcode id=309 lang=java
 *
 * [309] Best Time to Buy and Sell Stock with Cooldown
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
 *
 * algorithms
 * Medium (44.60%)
 * Likes:    1521
 * Dislikes: 58
 * Total Accepted:    101.2K
 * Total Submissions: 226.6K
 * Testcase Example:  '[1,2,3,0,2]'
 *
 * Say you have an array for which the i^th element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete as many
 * transactions as you like (ie, buy one and sell one share of the stock
 * multiple times) with the following restrictions:
 * 
 * 
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1
 * day)
 * 
 * 
 * Example:
 * 
 * 
 * Input: [1,2,3,0,2]
 * Output: 3 
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * 
 */
class Solution {
    /**
     * 挺难的多状态动态规划，非常好的题
     * 三种状态：第i天rest（包括cooldown），持股（i天或之前购入），卖出（i天卖出），记录i天这三种状态的最大收入。每种状态都和之前的状态有关，画状态转移图，找转移方程
     * TC O(n); SC O(1) compressed
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 62.96 % of java submissions (37 MB)
     * 
     * 其他方法：
     *  两个状态：前i天最后一次操作是buy，前i天最后一次是sell，第i天操作可以是sell buy，也可以是rest，这样将rest操作融合了。最终返回sell[n]
     * https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75927/Share-my-thinking-process
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int sell = 0, rest = 0, hold = Integer.MIN_VALUE;
        for (int price : prices) {
            int currRest;
            currRest = Math.max(rest, sell);
            sell = hold + price;
            hold = Math.max(hold, rest - price);
            rest = currRest;
        }
        return Math.max(rest, sell);
    }
}

