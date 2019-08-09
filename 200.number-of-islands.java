/*
 * @lc app=leetcode id=200 lang=java
 *
 * [200] Number of Islands
 *
 * https://leetcode.com/problems/number-of-islands/description/
 *
 * algorithms
 * Medium (42.30%)
 * Likes:    2914
 * Dislikes: 103
 * Total Accepted:    395.4K
 * Total Submissions: 933.4K
 * Testcase Example:  '[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]'
 *
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of
 * islands. An island is surrounded by water and is formed by connecting
 * adjacent lands horizontally or vertically. You may assume all four edges of
 * the grid are all surrounded by water.
 * 
 * Example 1:
 * 
 * 
 * Input:
 * 11110
 * 11010
 * 11000
 * 00000
 * 
 * Output: 1
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * 11000
 * 11000
 * 00100
 * 00011
 * 
 * Output: 3
 * 
 */
class Solution {
    /**
     * Bug free，感动
     * 连通性问题，查总共有几个集合，UnionFind解决
     * 时间复杂度 O(N*M); 空间复杂度 O(M*N)
     * Your runtime beats 28.6 % of java submissions
     * Your memory usage beats 53.02 % of java submissions (41.7 MB)
     * 
     * 优化：
     *  count计算可以放在union里，直接修改，memory提升
     * 
     * 其他方法：
     *  DFS/ BFS
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        // gird = m*n
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;
        if (n == 0) return 0;

        UnionFind uf = new UnionFind(m*n);
        
        // build the map
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    if (j < n-1 && grid[i][j+1] == '1') {
                        uf.union(i*n+j, i*n+j+1);
                    }
                    if (i < m-1 && grid[i+1][j] == '1') {
                        uf.union(i*n+j, i*n+n+j);
                    }
                }
                else {
                    uf.counts--;
                }
            }
        }
        return uf.counts;
    }
    class UnionFind {
        int[] parents;
        int counts;
        public UnionFind(int n) {
            counts = n;
            parents  = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
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
                counts--;
            }
        }
    }
}

