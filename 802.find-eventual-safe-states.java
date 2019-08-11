import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * @lc app=leetcode id=802 lang=java
 *
 * [802] Find Eventual Safe States
 *
 * https://leetcode.com/problems/find-eventual-safe-states/description/
 *
 * algorithms
 * Medium (44.73%)
 * Likes:    396
 * Dislikes: 60
 * Total Accepted:    20.1K
 * Total Submissions: 44.9K
 * Testcase Example:  '[[1,2],[2,3],[5],[0],[5],[],[]]'
 *
 * In a directed graph, we start at some node and every turn, walk along a
 * directed edge of the graph.  If we reach a node that is terminal (that is,
 * it has no outgoing directed edges), we stop.
 * 
 * Now, say our starting node is eventually safe if and only if we must
 * eventually walk to a terminal node.  More specifically, there exists a
 * natural number K so that for any choice of where to walk, we must have
 * stopped at a terminal node in less than K steps.
 * 
 * Which nodes are eventually safe?  Return them as an array in sorted order.
 * 
 * The directed graph has N nodes with labels 0, 1, ..., N-1, where N is the
 * length of graph.  The graph is given in the following form: graph[i] is a
 * list of labels j such that (i, j) is a directed edge of the graph.
 * 
 * 
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Here is a diagram of the above graph.
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * graph will have length at most 10000.
 * The number of edges in the graph will not exceed 32000.
 * Each graph[i] will be a sorted list of different integers, chosen within the
 * range [0, graph.length - 1].
 * 
 * 
 */
class Solution {
    /**
     * 寻找出度为零的节点，把他翻过来，就是寻找入度为零的节点，就是拓扑排序的遍历方法，BFS，最后结果需要排序
     * 时间复杂度 O(max(V,E)) 因为需要重新建图，复杂度取决于边和点的数量
     * Your runtime beats 31.54 % of java submissions
     * Your memory usage beats 91.67 % of java submissions (66 MB)
     * @param graph
     * @return
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        if (graph == null || graph.length == 0) return new ArrayList<>();
        int n = graph.length;
        List<List<Integer>> newGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newGraph.add(new ArrayList<>());
        }
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int point : graph[i]) {
                indegree[i]++;
                newGraph.get(point).add(i);
            }
        }
        // BFS
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0)
                q.add(i);
        }
        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int num = q.poll();
            res.add(num);
            for (int next : newGraph.get(num)) {
                if (--indegree[next] == 0)
                    q.add(next);
            }
        }
        Collections.sort(res);
        return res;
    }
}

