import java.util.ArrayList;
import java.util.List;

/*
 * @lc app=leetcode id=54 lang=java
 *
 * [54] Spiral Matrix
 */
class Solution {
    /**
     * 矩阵遍历，主要是行列以及循环的条件
     * 时间复杂度 O(nm)
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 99.52 % of java submissions (34.5 MB)
     * @param matrix
     * @return
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length==0)   return new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        List<Integer> res = new ArrayList<>();

        for (int row = 0, col = 0; row < (m+1)/2 && col < (n+1)/2; row++, col++){
            for (int j = col; j < n-col; j++){
                res.add(matrix[row][j]);
            }
            for (int i = row+1; i < m-row; i++){
                res.add(matrix[i][n-col-1]);
            }
            if (res.size() == m*n)  break;
            for (int j = n-col-2; j >= col; j--){
                res.add(matrix[m-row-1][j]);
            }
            for (int i = m-row-2; i > row; i--){
                res.add(matrix[i][col]);
            }
        }
        return res;
    }
}

