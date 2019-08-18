import java.util.LinkedList;
import java.util.Queue;

/*
 * @lc app=leetcode id=297 lang=java
 *
 * [297] Serialize and Deserialize Binary Tree
 *
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
 *
 * algorithms
 * Hard (42.08%)
 * Likes:    1757
 * Dislikes: 87
 * Total Accepted:    205.8K
 * Total Submissions: 488.8K
 * Testcase Example:  '[1,2,3,null,null,4,5]'
 *
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or
 * transmitted across a network connection link to be reconstructed later in
 * the same or another computer environment.
 * 
 * Design an algorithm to serialize and deserialize a binary tree. There is no
 * restriction on how your serialization/deserialization algorithm should work.
 * You just need to ensure that a binary tree can be serialized to a string and
 * this string can be deserialized to the original tree structure.
 * 
 * Example: 
 * 
 * 
 * You may serialize the following tree:
 * 
 * ⁠   1
 * ⁠  / \
 * ⁠ 2   3
 * ⁠    / \
 * ⁠   4   5
 * 
 * as "[1,2,3,null,null,4,5]"
 * 
 * 
 * Clarification: The above format is the same as how LeetCode serializes a
 * binary tree. You do not necessarily need to follow this format, so please be
 * creative and come up with different approaches yourself.
 * 
 * Note: Do not use class member/global/static variables to store states. Your
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
public class CodecPreOrderRecursive {
    /**
     * 序列化二叉树，用某种方法遍历，然后记录序列化字符串；再用相同方法遍历然后build tree即可。本次采用前序遍历，递归解决问题
     * 时间复杂度 O(n); 空间复杂度 O(n)
     * Your runtime beats 88.03 % of java submissions
     * Your memory usage beats 57.14 % of java submissions (39.4 MB)
     * 
     * 其他方法：
     *  非递归BFS，层序遍历
     */
    private static final String spliter = ",";
    private static final String nothing = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null)   sb.append(nothing).append(spliter);
        else {
            sb.append(root.val).append(spliter);
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
        return;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        TreeNode root = deserialize(nodes);
        return root;
    }

    public TreeNode deserialize(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals(nothing))    return null;
        TreeNode root = new TreeNode(Integer.valueOf(val));
        root.left = deserialize(nodes);
        root.right = deserialize(nodes);
        return root;
    }
}

public class Codec {
    /**
     * BFS，非递归，始终用queue存节点，保证先入先出层序遍历
     * Your runtime beats 52.82 % of java submissions
     * Your memory usage beats 71.43 % of java submissions (39.2 MB)
     */
    private static final String spliter = ",";
    private static final String nothing = "#";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)   return nothing;
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                sb.append(nothing).append(spliter);
                continue;
            }
            sb.append(node.val).append(spliter);
            q.offer(node.left);
            q.offer(node.right);
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<TreeNode> q = new LinkedList<>();
        String[] vals = data.split(spliter);
        if (vals[0].equals(nothing))    return null;
        TreeNode root = new TreeNode(Integer.valueOf(vals[0]));
        q.offer(root);

        for (int i = 1; i < vals.length; i = i+2) {
            TreeNode curr = q.poll();
            if (!vals[i].equals(nothing)) {
                TreeNode leftT = new TreeNode(Integer.valueOf(vals[i]));
                curr.left = leftT;
                q.add(leftT);
            }
            else {
                curr.left = null;
            }
            if (!vals[i+1].equals(nothing)) {
                TreeNode rightT = new TreeNode(Integer.valueOf(vals[i+1]));
                curr.right = rightT;
                q.add(rightT);
            }
            else {
                curr.right = null;
            }
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));

