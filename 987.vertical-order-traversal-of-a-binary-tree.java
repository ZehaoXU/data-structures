import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

/*
 * @lc app=leetcode id=987 lang=java
 *
 * [987] Vertical Order Traversal of a Binary Tree
 *
 * https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/description/
 *
 * algorithms
 * Medium (31.98%)
 * Likes:    167
 * Dislikes: 356
 * Total Accepted:    13.6K
 * Total Submissions: 42.5K
 * Testcase Example:  '[3,9,20,null,null,15,7]'
 *
 * Given a binary tree, return the vertical order traversal of its nodes
 * values.
 * 
 * For each node at position (X, Y), its left and right children respectively
 * will be at positions (X-1, Y-1) and (X+1, Y-1).
 * 
 * Running a vertical line from X = -infinity to X = +infinity, whenever the
 * vertical line touches some nodes, we report the values of the nodes in order
 * from top to bottom (decreasing Y coordinates).
 * 
 * If two nodes have the same position, then the value of the node that is
 * reported first is the value that is smaller.
 * 
 * Return an list of non-empty reports in order of X coordinate.  Every report
 * will have a list of values of nodes.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * 
 * 
 * 
 * Input: [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 * Explanation: 
 * Without loss of generality, we can assume the root node is at position (0,
 * 0):
 * Then, the node with value 9 occurs at position (-1, -1);
 * The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
 * The node with value 20 occurs at position (1, -1);
 * The node with value 7 occurs at position (2, -2).
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * 
 * 
 * Input: [1,2,3,4,5,6,7]
 * Output: [[4],[2],[1,5,6],[3],[7]]
 * Explanation: 
 * The node with value 5 and the node with value 6 have the same position
 * according to the given scheme.
 * However, in the report "[1,5,6]", the node value of 5 comes first since 5 is
 * smaller than 6.
 * 
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * The tree will have between 1 and 1000 nodes.
 * Each node's value will be between 0 and 1000.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * 遍历二叉树，记录x,y,val，可以用一个新建类一起存储，本次使用orderMap存储，key是pos，需要实现comparable接口，value是orderSet用来存相同位置的val
     * 最后只要按顺序遍历即可，注意相同x要存在一起
     * Your runtime beats 47.94 % of java submissions
     * Your memory usage beats 100 % of java submissions (36.3 MB)
     * @param root
     * @return
     */
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        int xmin = Integer.MAX_VALUE;
        int xmax = Integer.MIN_VALUE;
        Map<Pos, SortedSet<Integer>> map = new TreeMap<>();
        xmin = traverse(map, root, 0, 0, xmin);

        List<List<Integer>> res = new ArrayList<>();
        for (Pos pos : map.keySet()) {
            int x = pos.x;
            if (x-xmin+1 > res.size()) {
                List<Integer> temp = new ArrayList<>();
                temp.addAll(map.get(pos));
                res.add(temp);
            }
            else {
                res.get(x-xmin).addAll(map.get(pos));
            }
        }
        return res;
    }
    public int traverse(Map<Pos, SortedSet<Integer>> map, 
                            TreeNode root,
                            int x, int y, int xmin) {
        if (root == null)   return xmin;
        xmin = Math.min(xmin, x);
        Pos p = new Pos(x,y);
        if (!map.containsKey(p))    map.put(p, new TreeSet<>());
        map.get(p).add(root.val);
        xmin = Math.min(xmin, traverse(map, root.left, x-1, y+1, xmin));
        xmin = Math.min(xmin, traverse(map, root.right, x+1, y+1, xmin));
        return xmin;
    }
}

class Pos implements Comparable<Pos> {
    int x;
    int y;
    Pos(int _x, int _y) {
        x = _x;
        y = _y;
    }
    public int compareTo(Pos p) {
        if (x != p.x)
            return x-p.x;
        else
            return y-p.y;
    }
}

