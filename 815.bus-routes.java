import java.util.*;

/*
 * @lc app=leetcode id=815 lang=java
 *
 * [815] Bus Routes
 *
 * https://leetcode.com/problems/bus-routes/description/
 *
 * algorithms
 * Hard (40.45%)
 * Likes:    463
 * Dislikes: 17
 * Total Accepted:    23.4K
 * Total Submissions: 57.8K
 * Testcase Example:  '[[1,2,7],[3,6,7]]\n1\n6'
 *
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th
 * bus repeats forever. For example if routes[0] = [1, 5, 7], this means that
 * the first bus (0-th indexed) travels in the sequence
 * 1->5->7->1->5->7->1->... forever.
 * 
 * We start at bus stop S (initially not on a bus), and we want to go to bus
 * stop T. Travelling by buses only, what is the least number of buses we must
 * take to reach our destination? Return -1 if it is not possible.
 * 
 * 
 * Example:
 * Input: 
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation: 
 * The best strategy is take the first bus to the bus stop 7, then take the
 * second bus to the bus stop 6.
 * 
 * 
 * Note: 
 * 
 * 
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 * 
 * 
 */
class Solution {
    /**
     * BFS，需要仔细区别开route/bus 和 stop的区别，每个stop可以乘坐一些bus，在一步之内到达下面的stop
     * 时间复杂度 O(R*S) R numOfRoutes, S numOfStops; 空间复杂度 O(R*S+R+S)
     * Your runtime beats 18.98 % of java submissions
     * Your memory usage beats 47.06 % of java submissions (74.6 MB)
     * @param routes
     * @param S
     * @param T
     * @return
     */
    public int numBusesToDestination(int[][] routes, int S, int T) {
        int n = routes.length;
        // stop -> route
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int stop : routes[i]) {
                if (!map.containsKey(stop))
                    map.put(stop, new ArrayList<>());
                map.get(stop).add(i);
            }
        }
        // contains route/bus, because max_num of stop is unsure
        boolean[] visited = new boolean[n];
        // contain stops
        Queue<Integer> q = new LinkedList<>();
        q.add(S);
        int count = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int stop = q.poll();
                if (stop == T)  return count;
                for (Integer bus : map.get(stop)) {
                    if (visited[bus] == true)   continue;
                    visited[bus] = true;
                    for (int next : routes[bus]) {
                        q.add(next);
                    }
                }
            }
            count++;
        }
        return -1;
    }
}

