import java.util.Queue;
import java.util.LinkedList;

/*
 * @lc app=leetcode id=934 lang=java
 *
 * [934] Shortest Bridge
 *
 * https://leetcode.com/problems/shortest-bridge/description/
 *
 * algorithms
 * Medium (44.39%)
 * Likes:    326
 * Dislikes: 24
 * Total Accepted:    11.7K
 * Total Submissions: 26.3K
 * Testcase Example:  '[[0,1],[1,0]]'
 *
 * In a given 2D binary array A, there are two islands.  (An island is a
 * 4-directionally connected group of 1s not connected to any other 1s.)
 * 
 * Now, we may change 0s to 1s so as to connect the two islands together to
 * form 1 island.
 * 
 * Return the smallest number of 0s that must be flipped.  (It is guaranteed
 * that the answer is at least 1.)
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[0,1],[1,0]]
 * Output: 1
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 * 
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= A.length = A[0].length <= 100
 * A[i][j] == 0 or A[i][j] == 1
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    /**
     * 先找到两个群岛的集合，然后从一个集合开始BFS扩展，或者两个集合双向BFS
     * 先找到一个1，然后DFS找全部的岛，然后BFS扩展到另一个1即可
     * 时间复杂度 O(MN); 空间复杂度 O(NM)
     * Your runtime beats 92.57 % of java submissions
     * Your memory usage beats 61.54 % of java submissions (46.8 MB)
     * @param A
     * @return
     */
    public int shortestBridge(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int row = 0, col = 0;
        // find a start point
        outer: 
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0)   continue;
                row = i;
                col = j;
                break outer;
            }
        }
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[m][n];
        dfs(row, col, q, visited, A);
        // BFS
        int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int step = 0;
        boolean found = false;
        traverse:
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cell = q.poll();
                for (int[] dir : dirs) {
                    int x = cell[0]+dir[0];
                    int y = cell[1]+dir[1];
                    if (x<0 || x>=m || y<0 || y>=n || visited[x][y]==1)
                        continue;
                    if (A[x][y] == 0) {
                        q.add(new int[]{x,y});
                        visited[x][y] = 1;
                    }
                    else {
                        found = true;
                        break traverse;
                    }
                }
            }
            step++;
        }
        
        return step;
    }
    public void dfs(int row, int col, Queue<int[]> q, int[][] visited, int[][] A) {
        if (row<0 || row>=visited.length || col<0 || col>=visited[0].length || A[row][col]==0 || visited[row][col]==1)
            return;
        
        visited[row][col] = 1;
        q.add(new int[]{row, col});

        dfs(row-1, col, q, visited, A);
        dfs(row+1, col, q, visited, A);
        dfs(row, col-1, q, visited, A);
        dfs(row, col+1, q, visited, A);
    }
}

