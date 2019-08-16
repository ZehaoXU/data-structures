/*
 * @lc app=leetcode id=669 lang=java
 *
 * [669] Trim a Binary Search Tree
 *
 * https://leetcode.com/problems/trim-a-binary-search-tree/description/
 *
 * algorithms
 * Easy (60.74%)
 * Likes:    1212
 * Dislikes: 135
 * Total Accepted:    72K
 * Total Submissions: 118.6K
 * Testcase Example:  '[1,0,2]\n1\n2'
 *
 * 
 * Given a binary search tree and the lowest and highest boundaries as L and R,
 * trim the tree so that all its elements lies in [L, R] (R >= L). You might
 * need to change the root of the tree, so the result should return the new
 * root of the trimmed binary search tree.
 * 
 * 
 * Example 1:
 * 
 * Input: 
 * ⁠   1
 * ⁠  / \
 * ⁠ 0   2
 * 
 * ⁠ L = 1
 * ⁠ R = 2
 * 
 * Output: 
 * ⁠   1
 * ⁠     \
 * ⁠      2
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * ⁠   3
 * ⁠  / \
 * ⁠ 0   4
 * ⁠  \
 * ⁠   2
 * ⁠  /
 * ⁠ 1
 * 
 * ⁠ L = 1
 * ⁠ R = 3
 * 
 * Output: 
 * ⁠     3
 * ⁠    / 
 * ⁠  2   
 * ⁠ /
 * ⁠1
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
     * 聪明方法，trimBST返回一个根节点，他的所有子树满足条件；以此作为递归，如果root满足条件，则left right分别调用trim；如果root不满足条件，则在他的left right中寻找答案，依旧调用trim
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 97.22 % of java submissions (38.1 MB)
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null)   return root;
        if (root.val > R)   return trimBST(root.left, L, R);
        if (root.val < L)   return trimBST(root.right, L, R);
        // root is valid
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }
}

class SolutionStupid {
    /**
     * 笨蛋做法，把一个easy题想复杂了。用了个dummynode，遍历一遍树，根据儿子是否符合条件修改父亲的指针
     * 时间复杂度 O(n)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 97.22 % of java submissions (37.9 MB)
     * 
     * 其他方法：
     *  学会返回节点！而不是void 在遍历的时候修改right left
     * @param root
     * @param L
     * @param R
     * @return
     */
    public TreeNode trimBST(TreeNode root, int L, int R) {
        TreeNode dommy = new TreeNode(Integer.MAX_VALUE);
        dommy.left = root;
        pruning(root, dommy, L, R);
        return dommy.left;
    }
    // 判断root是否符合，并进行剪枝
    public void pruning(TreeNode root, TreeNode pre, int L, int R) {
        if (root == null)   return;
        if (root.val >= L && root.val <= R) {
            pruning(root.left, root, L, R);
            pruning(root.right, root, L, R);
        }
        else if (root.val < L) {
            pre.left = root.right;
            pruning(root.right, pre, L, R);
        }
        else {  // root.val > R
            if (pre.val == Integer.MAX_VALUE)
                pre.left = root.left;
            else    pre.right = root.left;
            pruning(root.left, pre, L, R);
        }
        return;
    }
}

