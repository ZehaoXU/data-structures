/*
 * @lc app=leetcode id=695 lang=java
 *
 * [695] Max Area of Island
 *
 * https://leetcode.com/problems/max-area-of-island/description/
 *
 * algorithms
 * Medium (58.11%)
 * Likes:    1186
 * Dislikes: 62
 * Total Accepted:    91.9K
 * Total Submissions: 157.8K
 * Testcase Example:  '[[1,1,0,0,0],[1,1,0,0,0],[0,0,0,1,1],[0,0,0,1,1]]'
 *
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.) You
 * may assume all four edges of the grid are surrounded by water.
 * 
 * Find the maximum area of an island in the given 2D array. (If there is no
 * island, the maximum area is 0.)
 * 
 * Example 1:
 * 
 * 
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,1,1,0,1,0,0,0,0,0,0,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,0,1,0,0],
 * ⁠[0,1,0,0,1,1,0,0,1,1,1,0,0],
 * ⁠[0,0,0,0,0,0,0,0,0,0,1,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,1,0,0,0],
 * ⁠[0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 
 * Given the above grid, return 6. Note the answer is not 11, because the
 * island must be connected 4-directionally.
 * 
 * Example 2:
 * 
 * 
 * [[0,0,0,0,0,0,0,0]]
 * Given the above grid, return 0.
 * 
 * Note: The length of each dimension in the given grid does not exceed 50.
 * 
 */
class Solution {
    /**
     * gird + connected component
     * UnionFind做，加一个max
     * Your runtime beats 53.14 % of java submissions
     * Your memory usage beats 22.22 % of java submissions (45.3 MB)
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0)   return 0;
        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0)    continue;
                if (j<n-1 && grid[i][j+1]==1) {
                    uf.union(i*n+j, i*n+j+1);
                }
                if (i<m-1 && grid[i+1][j]==1) {
                    uf.union(i*n+j, i*n+j+n);
                }
            }
        }
        return uf.max;
    }
    class UnionFind {
        int[] parents;
        int[] weights;
        int max;
        public UnionFind(int[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            parents = new int[m*n];
            weights = new int[m*n];
            max = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0)    continue;
                    parents[i*n+j] = i*n+j;
                    weights[i*n+j] = 1;
                    max = 1;
                }
            }
        }
        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }
        public void union(int i, int j) {
            int ri = find(i);
            int rj = find(j);
            if (ri != rj) {
                parents[ri] = rj;
                weights[rj] += weights[ri];
                max = Math.max(max, weights[rj]);
            }
        }
    }
}

