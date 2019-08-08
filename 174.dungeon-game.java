/*
 * @lc app=leetcode id=174 lang=java
 *
 * [174] Dungeon Game
 *
 * https://leetcode.com/problems/dungeon-game/description/
 *
 * algorithms
 * Hard (27.58%)
 * Likes:    757
 * Dislikes: 18
 * Total Accepted:    69.3K
 * Total Submissions: 250.7K
 * Testcase Example:  '[[-2,-3,3],[-5,-10,1],[10,30,-5]]'
 *
 * table.dungeon, .dungeon th, .dungeon td {
 * ⁠ border:3px solid black;
 * }
 * 
 * ⁠.dungeon th, .dungeon td {
 * ⁠   text-align: center;
 * ⁠   height: 70px;
 * ⁠   width: 70px;
 * }
 * 
 * The demons had captured the princess (P) and imprisoned her in the
 * bottom-right corner of a dungeon. The dungeon consists of M x N rooms laid
 * out in a 2D grid. Our valiant knight (K) was initially positioned in the
 * top-left room and must fight his way through the dungeon to rescue the
 * princess.
 * 
 * The knight has an initial health point represented by a positive integer. If
 * at any point his health point drops to 0 or below, he dies immediately.
 * 
 * Some of the rooms are guarded by demons, so the knight loses health
 * (negative integers) upon entering these rooms; other rooms are either empty
 * (0's) or contain magic orbs that increase the knight's health (positive
 * integers).
 * 
 * In order to reach the princess as quickly as possible, the knight decides to
 * move only rightward or downward in each step.
 * 
 * 
 * 
 * Write a function to determine the knight's minimum initial health so that he
 * is able to rescue the princess.
 * 
 * For example, given the dungeon below, the initial health of the knight must
 * be at least 7 if he follows the optimal path RIGHT-> RIGHT -> DOWN ->
 * DOWN.
 * 
 * 
 * 
 * 
 * -2 (K)
 * -3
 * 3
 * 
 * 
 * -5
 * -10
 * 1
 * 
 * 
 * 10
 * 30
 * -5 (P)
 * 
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The knight's health has no upper bound.
 * Any room can contain threats or power-ups, even the first room the knight
 * enters and the bottom-right room where the princess is imprisoned.
 * 
 * 
 */
class Solution {
    /**
     * DP，存在的正数是一个比较困难的情况，如果从00开始碰到正数比较难进行下去。故从右下开始，存储来到这一格的要求的最小hp值，是由hp[i+1][j] and hp[i][j+1]中的较小者 与 这一格代价计算得到。
     * hp[i][j] = min(hp[i][j+1], hp[i+1][j]) - grid[i][j] 如果这一格的value是一个很大的正数，能够cover后面所需的血量，则hp会小于零，故小于等于零时，hp取1
     * 时间复杂度 O(nm); 空间复杂度 O(nm)
     * Your runtime beats 97.16 % of java submissions
     * Your memory usage beats 58.82 % of java submissions (42 MB)
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        // value is the minimum hp before fight here
        int[][] hp = new int[m+1][n+1];
        // init
        for (int i = 0; i < m; i++) {
            hp[i][n] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < n; i++) {
            hp[m][i] = Integer.MAX_VALUE;
        }
        hp[m-1][n] = 1;
        
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                hp[i][j] = Math.max(1, Math.min(hp[i+1][j], hp[i][j+1]) - dungeon[i][j]);
            }
        }
        return hp[0][0];
    }
}

