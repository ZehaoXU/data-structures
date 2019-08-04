/*
 * @lc app=leetcode id=399 lang=java
 *
 * [399] Evaluate Division
 */
class Solution {
    Map<String, Map<String, Double>> graph = new HashMap<>();
    /**
     * 有向图有权重，dfs或者union find，真他妈难
     * 时间复杂度 O(e + e*q) e:equationsLen, q:queries.Len；空间复杂度 O(e)
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int n = values.length;
        for (int i = 0; i < n; i++){
            List<String> pair = equations.get(i);
            String first = pair.get(0);
            String second = pair.get(1);
            double val = values[i];
            graph.putIfAbsent(first, new HashMap<>());
            graph.get(first).put(second, val);
            graph.putIfAbsent(second, new HashMap<>());
            graph.get(second).put(first, 1/val);
        }
        // dfs
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String first = queries.get(i).get(0);
            String second = queries.get(i).get(1);
            if (!graph.containsKey(first) || !graph.containsKey(second)) {
                res[i] = -1;
                continue;
            }
            Set visited = new HashSet<String>();
            visited.add(first);
            res[i] = dfs(first, second, visited);
        }
        return res;
    }

    public double dfs(String first, String second, Set<String> visited) {
        if (first.equals(second)) {
            return 1.0;
        }

        for (String s : graph.get(first).keySet()) {
            if (!visited.contains(s)) {
                visited.add(s);
                double res = dfs(s, second, visited);
                if (res > 0)    return res*graph.get(first).get(s);
            }
        }
        return -1.0;
    }
}

