import java.util.LinkedList;
import java.util.Queue;
/*
 * @lc app=leetcode id=449 lang=java
 *
 * [449] Serialize and Deserialize BST
 *
 * https://leetcode.com/problems/serialize-and-deserialize-bst/description/
 *
 * algorithms
 * Medium (48.21%)
 * Likes:    737
 * Dislikes: 54
 * Total Accepted:    66.4K
 * Total Submissions: 137.5K
 * Testcase Example:  '[2,1,3]'
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary search tree. There
 * is no restriction on how your serialization/deserialization algorithm should
 * work. You just need to ensure that a binary search tree can be serialized to
 * a string and this string can be deserialized to the original tree
 * structure.
 * 
 * The encoded string should be as compact as possible.
 * 
 * Note: Do not use class member/global/static variables to store states. Your
 * serialize and deserialize algorithms should be stateless.
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
public class Codec {
    /**
     * BFS，n时间序列化，nlogn时间去序列化
     * 主要问题是层序遍历建树时效率太低，把每一个节点加入root树中，每次都要h时间
     * 时间复杂度 O(n)+O(nh)；空间复杂度 O(n)
     * Your runtime beats 70.3 % of java submissions
     * Your memory usage beats 100 % of java submissions (37.5 MB)
     * 
     * 优化方法：
     *  后序遍历或者前序遍历，更快建树
     *  用二进制存储数字，节省空间
     */
    private static final String spliter = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)   return "";

        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            sb.append(curr.val).append(spliter);
            if (curr.left != null) 
                q.add(curr.left);
            if (curr.right != null )
                q.add(curr.right);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals(""))    return null;

        String[] vals = data.split(spliter);
        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
        for (int i = 1; i < vals.length; i++) {
            int intVal = Integer.valueOf(vals[i]);
            buildTree(root, intVal);
        }
        return root;
    }
    public void buildTree(TreeNode root, int val) {
        if (val > root.val) {
            if (root.right == null)
                root.right = new TreeNode(val);
            else 
                buildTree(root.right, val);
            return;
        }
        else {
            if (root.left == null)
                root.left = new TreeNode(val);
            else 
                buildTree(root.left, val);
            return;
        }
        
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

