import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=847 lang=java
 *
 * [847] Shortest Path Visiting All Nodes
 *
 * https://leetcode.com/problems/shortest-path-visiting-all-nodes/description/
 *
 * algorithms
 * Hard (47.68%)
 * Likes:    325
 * Dislikes: 36
 * Total Accepted:    9.1K
 * Total Submissions: 19K
 * Testcase Example:  '[[1,2,3],[0],[0],[0]]'
 *
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is
 * given as graph.
 * 
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and
 * only if nodes i and j are connected.
 * 
 * Return the length of the shortest path that visits every node. You may start
 * and stop at any node, you may revisit nodes multiple times, and you may
 * reuse edges.
 * 
 * 
 * 
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [[1,2,3],[0],[0],[0]]
 * Output: 4
 * Explanation: One possible path is [1,0,2,0,3]
 * 
 * Example 2:
 * 
 * 
 * Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 * Output: 4
 * Explanation: One possible path is [0,1,4,2,3]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= graph.length <= 12
 * 0 <= graph[i].length < graph.length
 * 
 * 
 */
class Solution {
    /**
     * BFS，遍历的最短路径，难点是怎么表达每个状态。
     * 类似于哈密顿回路问题，用一个tuple<state, curr>来表示，state用二进制数表示哪些节点已经遍历，遍历节点k则第k位二进制=1；curr表示从哪个节点出，接下来寻找这一节点的neighbor
     * 时间复杂度 O(2^n*n)；空间复杂度 O(2^n*n)
     * Your runtime beats 98.42 % of java submissions
     * Your memory usage beats 100 % of java submissions (35.9 MB)
     * @param graph
     * @return
     */
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        boolean[][] visited = new boolean[1<<n][n];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            q.add(new int[]{1<<i, i});
            visited[1<<i][i] = true;
        }
        // BFS
        int steps = 0;
        while (!q.isEmpty()) {
            // 最终条件，q empty 或者 遍历所有节点
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] pair = q.poll();
                int cover = pair[0];
                int curr = pair[1];
                if (cover == (1<<n)-1)  return steps;
                for (int neighbor : graph[curr]) {
                    if (visited[cover | (1<<neighbor)][neighbor] == false) {
                        visited[cover | (1<<neighbor)][neighbor] = true;
                        q.add(new int[]{cover|(1<<neighbor), neighbor});
                    }
                }
            }
            steps++;
        }
        return steps;
    }
}

