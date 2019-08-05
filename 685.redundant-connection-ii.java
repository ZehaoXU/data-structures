/*
 * @lc app=leetcode id=685 lang=java
 *
 * [685] Redundant Connection II
 *
 * https://leetcode.com/problems/redundant-connection-ii/description/
 *
 * algorithms
 * Hard (30.99%)
 * Likes:    483
 * Dislikes: 159
 * Total Accepted:    24.2K
 * Total Submissions: 78.1K
 * Testcase Example:  '[[1,2],[1,3],[2,3]]'
 *
 * 
 * In this problem, a rooted tree is a directed graph such that, there is
 * exactly one node (the root) for which all other nodes are descendants of
 * this node, plus every node has exactly one parent, except for the root node
 * which has no parents.
 * 
 * The given input is a directed graph that started as a rooted tree with N
 * nodes (with distinct values 1, 2, ..., N), with one additional directed edge
 * added.  The added edge has two different vertices chosen from 1 to N, and
 * was not an edge that already existed.
 * 
 * The resulting graph is given as a 2D-array of edges.  Each element of edges
 * is a pair [u, v] that represents a directed edge connecting nodes u and v,
 * where u is a parent of child v.
 * 
 * Return an edge that can be removed so that the resulting graph is a rooted
 * tree of N nodes.  If there are multiple answers, return the answer that
 * occurs last in the given 2D-array.
 * Example 1:
 * 
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given directed graph will be like this:
 * ⁠ 1
 * ⁠/ \
 * v   v
 * 2-->3
 * 
 * 
 * Example 2:
 * 
 * Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * Output: [4,1]
 * Explanation: The given directed graph will be like this:
 * 5  2
 * ⁠    ^    |
 * ⁠    |    v
 * ⁠    4 
 * 
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N
 * is the size of the input array.
 * 
 */
class Solution {
    /**
     * UnionFind 在有向图中的应用！
     * 两种错误条件：1.出现了环 2.非根节点入度为2（两个父亲）
     * 注意，这两种错误条件并非把先出现的移除就行。如果仅仅有环，则和上一题一样；如果有双入节点，则一定有环出现；如果两个入边都在环上，则移除后出现的；如果一个在环一个不在环，则移除线出现的
     * 这里可以更方便地判断，如果遇到双入节点，则第二条边先不添加，如果最后无环直接返回第二条边；如果最后仍然有环，则返回最先遇到的那条边
     * 时间复杂度 O(n); 空间复杂度 O(n)
     * Your runtime beats 98.13 % of java submissions
     * Your memory usage beats 88.68 % of java submissions (41.8 MB)
     * @param edges
     * @return
     */
    public int[] findRedundantDirectedConnection(int[][] edges) {
        if (edges.length == 0)  return null;

        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length+1; i++)    
            parent[i] = i;
        int[] twoParentErrorLast = new int[2];
        int[] circleError = new int[2];
        for (int[] pair : edges) {
            int p = pair[0];
            int c = pair[1];
            // child已经有parent
            if (find(c, parent) != c) { 
                twoParentErrorLast = pair;
            }
            // 成环了
            else if (find(c, parent) == find(p, parent)) {
                circleError = pair;
            }
            else {
                parent[c] = find(p, parent);
            }
        }
        if (circleError[0] == 0)    return twoParentErrorLast;
        else if (twoParentErrorLast[0] == 0) return circleError;

        int[] twoParentErrorPrev = null;
        for (int[] pairs : edges) {
            if (pairs[1] == twoParentErrorLast[1]) {
                twoParentErrorPrev = pairs;
                break;
            }
        }
        return twoParentErrorPrev;
    }

    public int find(int x, int[] parent) {
        if (x != parent[x]) {
            parent[x] = find(parent[x], parent);
        }
        return parent[x];
    }
}

