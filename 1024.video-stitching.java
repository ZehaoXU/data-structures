import java.util.Arrays;
import java.util.Comparator;

/*
 * @lc app=leetcode id=1024 lang=java
 *
 * [1024] Video Stitching
 *
 * https://leetcode.com/problems/video-stitching/description/
 *
 * algorithms
 * Medium (46.87%)
 * Likes:    198
 * Dislikes: 16
 * Total Accepted:    10.7K
 * Total Submissions: 22.7K
 * Testcase Example:  '[[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]]\n10'
 *
 * You are given a series of video clips from a sporting event that lasted T
 * seconds.  These video clips can be overlapping with each other and have
 * varied lengths.
 * 
 * Each video clip clips[i] is an interval: it starts at time clips[i][0] and
 * ends at time clips[i][1].  We can cut these clips into segments freely: for
 * example, a clip [0, 7] can be cut into segments [0, 1] + [1, 3] + [3, 7].
 * 
 * Return the minimum number of clips needed so that we can cut the clips into
 * segments that cover the entire sporting event ([0, T]).  If the task is
 * impossible, return -1.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * Output: 3
 * Explanation: 
 * We take the clips [0,2], [8,10], [1,9]; a total of 3 clips.
 * Then, we can reconstruct the sporting event as follows:
 * We cut [1,9] into segments [1,2] + [2,8] + [8,9].
 * Now we have segments [0,2] + [2,8] + [8,10] which cover the sporting event
 * [0, 10].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: clips = [[0,1],[1,2]], T = 5
 * Output: -1
 * Explanation: 
 * We can't cover [0,5] with only [0,1] and [0,2].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: clips =
 * [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]],
 * T = 9
 * Output: 3
 * Explanation: 
 * We can take clips [0,4], [4,7], and [6,9].
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: clips = [[0,4],[2,8]], T = 5
 * Output: 2
 * Explanation: 
 * Notice you can have extra video after the event ends.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0], clips[i][1] <= 100
 * 0 <= T <= 100
 * 
 * 
 */
class SolutionGreedy {
    /**
     * greedy, 和jump game很像，求cover某一值的最远能到达的end，然后找cover end 的最远end，迭代更新，直到end >= T表示有解或者st == end表示无解。重点是提前排序，然后根据clips[i][0]只要遍历一遍即可
     * TC O(n*logn) n:clips.length; SC O(n) merge sort
     * Your runtime beats 5.79 % of java submissions
     * Your memory usage beats 50 % of java submissions (34.6 MB)
     */
    public int videoStitching(int[][] clips, int T) {
        Arrays.sort(clips, Comparator.comparing(a->a[0]));
        int res = 0;
        int st = -1, end = 0, i = 0;
        while (end < T && st != end) {
            st = end;
            while (i < clips.length && clips[i][0] <= st) {
                end = Math.max(end, clips[i][1]);
                i++;
            }
            res++;
        }
        if (end >= T)   return res;
        else return -1;
    }
}

class Solution {
    /**
     * dp，dp[i]表示[0,i]最少需要几段来cover，每个i，对于每个clip转移方程 dp[i]=min(dp[i], dp[clip_start] + 1)
     * 还有一种dp方法, dp[i][j] 表示[i, j]最少几段cover，对每个clip st和end，有几种情况：
     *  st > j or end < i, cannot cover
     *  st < i and end < j, cover left, dp[i][j] = 1 + dp[end][j]
     *  st > i and end > j, cover right, dp[i][j] = 1 + dp[i][st]
     *  st >= i and end <= j, cover mid, dp[i][j] = 1+dp[i][st]+dp[end][j]
     * TC O(T*C), 第二种dp TC O(T^2*C); SC O(T)， 第二种 O(T^2)
     * Your runtime beats 61.82 % of java submissions
     * Your memory usage beats 50 % of java submissions (34.5 MB)
     * @param clips
     * @param T
     * @return
     */
    public int videoStitching(int[][] clips, int T) {
        // dp[i] cover 0-i cost minimum clips
        int[] dp = new int[T+1];
        Arrays.fill(dp, T+2);
        dp[0] = 0;
        for (int i = 0; i <= T; i++) {
            for (int[] clip : clips) {
                int st = clip[0];
                int end = clip[1];
                if (st > i || end < i)  continue;
                dp[i] = Math.min(dp[i], 1 + dp[st]);
            }
        }
        return (dp[T] > T+1 ? -1 : dp[T]);
    }
}

