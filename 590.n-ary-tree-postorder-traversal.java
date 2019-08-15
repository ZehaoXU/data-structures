import java.util.Stack;


/*
 * @lc app=leetcode id=590 lang=java
 *
 * [590] N-ary Tree Postorder Traversal
 *
 * https://leetcode.com/problems/n-ary-tree-postorder-traversal/description/
 *
 * algorithms
 * Easy (68.34%)
 * Likes:    340
 * Dislikes: 44
 * Total Accepted:    48K
 * Total Submissions: 70.2K
 * Testcase Example:  '{"$id":"1","children":[{"$id":"2","children":[{"$id":"5","children":[],"val":5},{"$id":"6","children":[],"val":6}],"val":3},{"$id":"3","children":[],"val":2},{"$id":"4","children":[],"val":4}],"val":1}'
 *
 * Given an n-ary tree, return the postorder traversal of its nodes' values.
 * 
 * For example, given a 3-ary tree:
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * Return its postorder traversal as: [5,6,3,2,4,1].
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
class SolutionRecursive {
    /**
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 54.05 % of java submissions (47.1 MB)
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    public void helper(Node root, List<Integer> res) {
        if (root == null)   return;
        for (Node child : root.children) {
            helper(child, res);
        }
        res.add(root.val);
        return;
    }
}

class SolutionIterative {
    /**
     * 非递归多叉树后序遍历，真后序；感觉假后续写着方便，效率高
     * Your runtime beats 15.52 % of java submissions
     * Your memory usage beats 8.11 % of java submissions (50.3 MB)
     * @param root
     * @return
     */
    public List<Integer> postorder(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null)   return res;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        Node last = null;
        while (!stack.isEmpty()) {
            root = stack.peek();
            if (root.children.size() == 0 
                || root.children.get(root.children.size()-1) == last) {
                res.add(root.val);
                stack.pop();
                last = root;
            }
            else {  // have children and havent been visited
                for (int i = root.children.size()-1; i >= 0; i--) {
                    stack.push(root.children.get(i));
                }
            }
        }

        return res;
    }
}

