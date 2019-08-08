import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=778 lang=java
 *
 * [778] Swim in Rising Water
 *
 * https://leetcode.com/problems/swim-in-rising-water/description/
 *
 * algorithms
 * Hard (48.37%)
 * Likes:    332
 * Dislikes: 32
 * Total Accepted:    13.7K
 * Total Submissions: 28.2K
 * Testcase Example:  '[[0,2],[1,3]]'
 *
 * On an N x N grid, each square grid[i][j] represents the elevation at that
 * point (i,j).
 * 
 * Now rain starts to fall. At time t, the depth of the water everywhere is t.
 * You can swim from a square to another 4-directionally adjacent square if and
 * only if the elevation of both squares individually are at most t. You can
 * swim infinite distance in zero time. Of course, you must stay within the
 * boundaries of the grid during your swim.
 * 
 * You start at the top left square (0, 0). What is the least time until you
 * can reach the bottom right square (N-1, N-1)?
 * 
 * Example 1:
 * 
 * 
 * Input: [[0,2],[1,3]]
 * Output: 3
 * Explanation:
 * At time 0, you are in grid location (0, 0).
 * You cannot go anywhere else because 4-directionally adjacent neighbors have
 * a higher elevation than t = 0.
 * 
 * You cannot reach point (1, 1) until time 3.
 * When the depth of water is 3, we can swim anywhere inside the grid.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * Output: 16
 * Explanation:
 * ⁠0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 * 
 * The final route is marked in bold.
 * We need to wait until time 16 so that (0, 0) and (4, 4) are connected.
 * 
 * 
 * Note:
 * 
 * 
 * 2 <= N <= 50.
 * grid[i][j] is a permutation of [0, ..., N*N - 1].
 * 
 * 
 */
class Solution {
    /**
     * BugFree！感动了
     * 用UnionFind方法，因为这题本质是个percolation，只要第一点和最后一个点连通就行，每次开放一个点，这个点和四周重新连接
     * 比较关键的一步是time时刻，新开放值为time的位置，所以你只知道值，但不知道位置，如果遍历查询效率太低。于是在开始之前建立了一个value->position(row,col)的map方便查找
     * 时间复杂度 O(N^2) 因为time上限N*N-1；空间复杂度 O(N^2)
     * Your runtime beats 78.65 % of java submissions
     * Your memory usage beats 30 % of java submissions (43 MB)
     * @param grid
     * @return
     */
    public int swimInWater(int[][] grid) {
        int N = grid.length;
        UnionFind uf = new UnionFind(N*N);
        Map<Integer,Pos> intToPosMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                intToPosMap.put(grid[i][j], new Pos(i, j));
            }
        }

        int time = 0;
        while (uf.find(grid[0][0]) != uf.find(grid[N-1][N-1])) {
            Pos pos = intToPosMap.get(time);
            int row = pos.row, col = pos.col;
            // up
            if (row-1 >= 0 && grid[row-1][col] <= time) {
                uf.union(grid[row][col], grid[row-1][col]);
            }
            // down
            if (row+1 < N && grid[row+1][col] <= time) {
                uf.union(grid[row][col], grid[row+1][col]);
            }
            // left
            if (col-1 >= 0 && grid[row][col-1] <= time) {
                uf.union(grid[row][col], grid[row][col-1]);
            }
            // right
            if (col+1 < N && grid[row][col+1] <= time) {
                uf.union(grid[row][col], grid[row][col+1]);
            }
            time++;
        }

        return --time;
    }
    class Pos {
        int row;
        int col;
        Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    class UnionFind {
        int[] parents;
        int[] weights;
        UnionFind(int n) {
            parents = new int[n];
            weights = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                weights[i] = 1;
            }
        }
        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }
        public void union(int i, int j) {
            int rooti = find(i);
            int rootj = find(j);
            if (weights[rooti] >= weights[rootj]) {
                parents[rootj] = rooti;
                weights[rooti] += weights[rootj];
            }
            else {
                parents[rooti] = rootj;
                weights[rootj] += weights[rooti];
            }
        }
    }
}

