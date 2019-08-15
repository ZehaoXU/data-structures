import java.util.ArrayList;
import java.util.Stack;

import javax.xml.soap.Node;

/*
 * @lc app=leetcode id=589 lang=java
 *
 * [589] N-ary Tree Preorder Traversal
 *
 * https://leetcode.com/problems/n-ary-tree-preorder-traversal/description/
 *
 * algorithms
 * Easy (68.33%)
 * Likes:    276
 * Dislikes: 38
 * Total Accepted:    53.1K
 * Total Submissions: 77.6K
 * Testcase Example:  '{"$id":"1","children":[{"$id":"2","children":[{"$id":"5","children":[],"val":5},{"$id":"6","children":[],"val":6}],"val":3},{"$id":"3","children":[],"val":2},{"$id":"4","children":[],"val":4}],"val":1}'
 *
 * Given an n-ary tree, return the preorder traversal of its nodes' values.
 * 
 * For example, given a 3-ary tree:
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Return its preorder traversal as: [1,3,5,6,2,4].
 * 
 * 
 * 
 * Note:
 * 
 * Recursive solution is trivial, could you do it iteratively?
 * 
 */
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
class Solution {
    /**
     * 非递归多叉树前序遍历
     * Runtime: 4 ms, faster than 16.62% of Java online submissions
     * Memory Usage: 46.9 MB, less than 46.34% of Java online submissions
     * 
     * 更好的实现：因为先遍历根，所以只要把儿子加到stack里就行
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        
        while (!stack.empty()) {
            root = stack.pop();
            list.add(root.val);
            for (int i = root.children.size() - 1; i >= 0; i--)
                stack.add(root.children.get(i));
        }
        
        return list;
    }
}

class SolutionRecursive {
    /**
     * 多叉树前序遍历，递归
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 17.07 % of java submissions (48.6 MB)
     * @param root
     * @return
     */
    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    private void helper(Node root, List<Integer> res) {
        if (root == null)   return;
        res.add(root.val);
        for (Node child : root.children) {
            helper(child, res);
        }
        return;
    }
}

