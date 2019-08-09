import java.util.*;
import java.lang.*;

/*
 * @lc app=leetcode id=133 lang=java
 *
 * [133] Clone Graph
 *
 * https://leetcode.com/problems/clone-graph/description/
 *
 * algorithms
 * Medium (27.54%)
 * Likes:    882
 * Dislikes: 933
 * Total Accepted:    231.6K
 * Total Submissions: 838.3K
 * Testcase Example:  '{"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}'
 *
 * Given a reference of a node in a connected undirected graph, return a deep
 * copy (clone) of the graph. Each node in the graph contains a val (int) and a
 * list (List[Node]) of its neighbors.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * 
 * 
 * Input:
 * 
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 * 
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no
 * self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q
 * must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned
 * graph.
 * 
 * 
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {}

    public Node(int _val,List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};
*/
class Solution {
    /**
     * DFS
     * Your runtime beats 50.25 % of java submissions
     * Your memory usage beats 97.65 % of java submissions (32.5 MB)
     */
    public Node cloneGraph(Node node) {
        if (node == null)   return null;
        dfs(node);
        return map.get(node.val);
    }

    Map<Integer, Node> map = new HashMap<>();

    public void dfs(Node node) {
        if (map.containsKey(node.val))  return;

        Node cur = new Node(node.val, new ArrayList<Node>());
        map.put(node.val, cur);
        for (Node neighbor : node.neighbors) {
            dfs(neighbor);
            map.get(node.val).neighbors.add(map.get(neighbor.val));
        }
        return;
    }
}

class SolutionBFS {
    /**
     * 图的第一题，非常难理解感觉
     * BFS，用queue和map实现，思想很简单，感觉问题就是java变量生命周期的问题，必须将循环内生成的node存在循环外面。于是map既负责看是否遍历到，也负责存储生成的node
     * Your runtime beats 50.25 % of java submissions
     * Your memory usage beats 97.65 % of java submissions (33.1 MB)
     * @param node
     * @return
     */
    public Node cloneGraph(Node node) {
        if (node == null)   return null;

        LinkedList<Node> queue = new LinkedList<>();
        Map<Integer, Node> map = new HashMap<>();
        queue.add(node);
        map.put(node.val, new Node(node.val, new ArrayList<Node>()));

        while (!queue.isEmpty()){
            Node n = queue.remove();
            for (Node neighbor : n.neighbors) {
                if (!map.containsKey(neighbor.val)) {
                    queue.add(neighbor);
                    map.put(neighbor.val, new Node(neighbor.val, new ArrayList<Node>()));
                }
                map.get(n.val).neighbors.add(map.get(neighbor.val));
            }
        }

        return map.get(node.val);
    }
}

