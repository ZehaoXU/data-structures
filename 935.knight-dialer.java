import java.util.*;
import java.lang.*;

/*
 * @lc app=leetcode id=935 lang=java
 *
 * [935] Knight Dialer
 *
 * https://leetcode.com/problems/knight-dialer/description/
 *
 * algorithms
 * Medium (41.82%)
 * Likes:    277
 * Dislikes: 91
 * Total Accepted:    16.5K
 * Total Submissions: 39.5K
 * Testcase Example:  '1'
 *
 * A chess knight can move as indicated in the chess diagram below:
 * 
 * .           
 * 
 * 
 * 
 * This time, we place our chess knight on any numbered key of a phone pad
 * (indicated above), and the knight makes N-1 hops.  Each hop must be from one
 * key to another numbered key.
 * 
 * Each time it lands on a key (including the initial placement of the knight),
 * it presses the number of that key, pressing N digits total.
 * 
 * How many distinct numbers can you dial in this manner?
 * 
 * Since the answer may be large, output the answer modulo 10^9 + 7.
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
 * Input: 1
 * Output: 10
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: 2
 * Output: 20
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: 3
 * Output: 46
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= N <= 5000
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * dp,和之前不同的是跳跃方式固定，不用二维table，只要一维数组，dp[i][k]表示从i出发跳k-1步有多少种不同组合。需要提前记录从i出发能达到哪些数字，本次用map记录，事实上用二维数组即可，index 表示从谁出发，
     * TC O(N); SC O(1)
     * Your runtime beats 21.66 % of java submissions
     * Your memory usage beats 6.67 % of java submissions (43.6 MB)
     * @param N
     * @return
     */
    public int knightDialer(int N) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(1, new ArrayList<>(Arrays.asList(6,8)));
        map.put(2, new ArrayList<>(Arrays.asList(7,9)));
        map.put(3, new ArrayList<>(Arrays.asList(4,8)));
        map.put(4, new ArrayList<>(Arrays.asList(3,9,0)));
        map.put(5, new ArrayList<>());
        map.put(6, new ArrayList<>(Arrays.asList(1,7,0)));
        map.put(7, new ArrayList<>(Arrays.asList(2,6)));
        map.put(8, new ArrayList<>(Arrays.asList(1,3)));
        map.put(9, new ArrayList<>(Arrays.asList(2,4)));
        map.put(0, new ArrayList<>(Arrays.asList(6,4)));

        int mode = 1000000007;
        if (N <= 0) return 0;
        int[] dp = new int[10];
        Arrays.fill(dp, 1);
        for (int k = 2; k <= N; k++) {
            int[] curr = new int[10];
            for (int i = 0; i < 10; i++) {
                for (int num : map.get(i)) {
                    curr[i]  = (curr[i] + dp[num]) % mode;
                }
            }
            dp = curr;
        }
        int res = 0;
        for (int num : dp) res = (res + num) % mode;
        return res;
    }
}

