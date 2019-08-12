import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import javafx.util.*;

/*
 * @lc app=leetcode id=542 lang=java
 *
 * [542] 01 Matrix
 *
 * https://leetcode.com/problems/01-matrix/description/
 *
 * algorithms
 * Medium (36.43%)
 * Likes:    758
 * Dislikes: 80
 * Total Accepted:    49.6K
 * Total Submissions: 136K
 * Testcase Example:  '[[0,0,0],[0,1,0],[0,0,0]]'
 *
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for
 * each cell.
 * 
 * The distance between two adjacent cells is 1.
 * 
 * 
 * 
 * Example 1: 
 * 
 * 
 * Input:
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[0,0,0]]
 * 
 * Output:
 * [[0,0,0],
 * [0,1,0],
 * [0,0,0]]
 * 
 * 
 * Example 2: 
 * 
 * 
 * Input:
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[1,1,1]]
 * 
 * Output:
 * [[0,0,0],
 * ⁠[0,1,0],
 * ⁠[1,2,1]]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 * 
 * 
 */
class Solution {
    /**
     * 用 int[] 代替pair，时间/内存都有所提升
     * 注意：int[] 不能当作map/set的key！！！！因为他是比较引用，不是比较值是否相等
     * Your runtime beats 25.85 % of java submissions
     * Your memory usage beats 91.67 % of java submissions (54.9 MB)
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        Queue<int[]> queue = new LinkedList<>();
        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    int[] pair = new int[] {i, j};
                    queue.offer(pair);
                    visited[i][j] = 1;
                }
            }
        }
        
        int[][] res = new int[m][n];
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] d : dirs) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                int[] pair = new int[]{r, c};
                if (r < 0 || r >= m || c < 0 || c >= n || 
                    visited[r][c] == 1) continue;
                queue.add(pair);
                visited[r][c] = 1;
                res[r][c] = res[cell[0]][cell[1]] + 1;
            }
        }
        
        return res;
    }
}

class SolutionSlow {
    /**
     * BFS，从0节点开始向外扩展，需要记步/分层
     * 效率极低，原因可能是使用了pair
     * 时间复杂度 O(MN)；空间复杂度 O(MN)
     * Your runtime beats 7.44 % of java submissions
     * Your memory usage beats 8.33 % of java submissions (59 MB)
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0)   return new int[0][0];
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<Pair<Integer,Integer>> q = new LinkedList<>();
        Set<Pair<Integer,Integer>> visited = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    Pair<Integer, Integer> pair = new Pair<>(i, j);
                    q.add(pair);
                    visited.add(pair);
                }
            }
        }
        int[][] res = new int[m][n];
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};
        int step = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> pos = q.poll();
                int row = pos.getKey();
                int col = pos.getValue();
                res[row][col] = step;
                for (int[] direction : directions) {
                    int newRow = row + direction[0];
                    int newCol = col + direction[1];
                    Pair<Integer, Integer> pair = new Pair<>(newRow, newCol);
                    if (newRow<0 || newRow>=m || newCol<0 || newCol>=n || visited.contains(pair))
                        continue;
                    q.add(pair);
                    visited.add(pair);
                }
            }
            step++;
        }
        return res;
    }
}

