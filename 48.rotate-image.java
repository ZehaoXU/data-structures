/*
 * @lc app=leetcode id=48 lang=java
 *
 * [48] Rotate Image
 */
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length < 2) return;
        solver(matrix, 0, 0);
    }

    public void solver(int[][] matrix, int row, int col){
        if (row == matrix.length/2)   return;
        int len = matrix.length-1;
        for (int i = 0; i < len-2*row; i++){
            int temp = matrix[row][col+i];
            matrix[row][col+i] = matrix[len-row-i][col];
            matrix[len-row-i][col] = matrix[len-row][len-col-i];
            matrix[len-row][len-col-i] = matrix[row+i][len-col];
            matrix[row+i][len-col] = temp;
        }
        solver(matrix, row+1, col+1);
    }
}

