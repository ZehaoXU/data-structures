import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=207 lang=java
 *
 * [207] Course Schedule
 *
 * https://leetcode.com/problems/course-schedule/description/
 *
 * algorithms
 * Medium (38.64%)
 * Likes:    2027
 * Dislikes: 94
 * Total Accepted:    240.6K
 * Total Submissions: 621.1K
 * Testcase Example:  '2\n[[1,0]]'
 *
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have
 * to first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it
 * possible for you to finish all courses?
 * 
 * Example 1:
 * 
 * 
 * Input: 2, [[1,0]] 
 * Output: true
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0. So it is possible.
 * 
 * Example 2:
 * 
 * 
 * Input: 2, [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take. 
 * To take course 1 you should have finished course 0, and to take course 0 you
 * should
 * also have finished course 1. So it is impossible.
 * 
 * 
 * Note:
 * 
 * 
 * The input prerequisites is a graph represented by a list of edges, not
 * adjacency matrices. Read more about how a graph is represented.
 * You may assume that there are no duplicate edges in the input
 * prerequisites.
 * 
 * 
 */
class Solution {
    /**
     * 拓扑排序，用BFS遍历。拓扑排序特点是有向无环图，还需要记录一下各个节点的入度，从入度为零的节点开始除去，最后如果还剩节点说明有环，反之无环
     * 时间复杂度 O(N)；空间复杂度 O(N)
     * Your runtime beats 76.12 % of java submissions
     * Your memory usage beats 97.69 % of java submissions (43.2 MB)
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<Integer>());
        }
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int pre = pair[1];
            indegree[course]++;
            graph.get(pre).add(course);
        }
        // bfs
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)   q.add(i);
        }
        int count = 0;
        while (!q.isEmpty()) {
            count++;
            int pre = q.poll();
            for (int course : graph.get(pre)) {
                indegree[course]--;
                if (indegree[course] == 0)
                    q.add(course);
            }
        }
        return count == numCourses;
    }
}

