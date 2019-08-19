import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
/*
 * @lc app=leetcode id=99 lang=java
 *
 * [99] Recover Binary Search Tree
 *
 * https://leetcode.com/problems/recover-binary-search-tree/description/
 *
 * algorithms
 * Hard (35.39%)
 * Likes:    877
 * Dislikes: 48
 * Total Accepted:    125.3K
 * Total Submissions: 353.8K
 * Testcase Example:  '[1,3,null,null,2]'
 *
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * 
 * Recover the tree without changing its structure.
 * 
 * Example 1:
 * 
 * 
 * Input: [1,3,null,null,2]
 * 
 * 1
 * /
 * 3
 * \
 * 2
 * 
 * Output: [3,1,null,null,2]
 * 
 * 3
 * /
 * 1
 * \
 * 2
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [3,1,4,null,null,2]
 * 
 * ⁠ 3
 * ⁠/ \
 * 1   4
 * /
 * 2
 * 
 * Output: [2,1,4,null,null,3]
 * 
 * ⁠ 2
 * ⁠/ \
 * 1   4
 * /
 * ⁠ 3
 * 
 * 
 * Follow up:
 * 
 * 
 * A solution using O(n) space is pretty straight forward.
 * Could you devise a constant space solution?
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
     * 有点复杂，总的来说是中序遍历时记录值被调换的节点位置，这里又有特殊情况：临近值被调换，还是非临近值被调换
     * follow up里O(1)时间完成实际上暗示morris方法，注意morris的中序遍历有两个地方对根节点计算，需要prev节点记录上个访问的点
     * 时间复杂度 O(n); 空间复杂度 O(h)
     * Your runtime beats 32.95 % of java submissions
     * Your memory usage beats 69.23 % of java submissions (43.6 MB)
     * @param root
     */
    public void recoverTree(TreeNode root) {
        TreeNode curr = root, prev = null;
        TreeNode first = null, second = null;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (prev != null && curr.val < prev.val) {
                second = curr;
                if (first == null)
                    first = prev;
                else    break;
            }
            prev = curr;
            curr = curr.right;
        }
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
        return;
    }
}

