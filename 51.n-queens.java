import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.*;

/*
 * @lc app=leetcode id=51 lang=java
 *
 * [51] N-Queens
 */
class Solution {
    /**
     * 回溯法，检查是否合理比较困难，故像数独一样用专用函数遍历检测
     * 时间复杂度 O(n!)
     * Your runtime beats 95.15 % of java submissions
     * Your memory usage beats 99.63 % of java submissions (37.3 MB)
     * 
     * 优化方法：棋盘是对称的，只要求一半就可以得到另一半
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        char[][] chess = new char[n][n];
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                chess[i][j] = '.';
            }
        }
        backtrack(res, chess, 0);
        return res;
    }

    public void backtrack(List<List<String>> res, char[][] chess, int row){
        int n = chess.length;
        if (row == n){
            List<String> temp = new ArrayList<>();
            for (char[] line : chess){
                temp.add(new String(line));
            }
            res.add(temp);
            return;
        }
        for (int i = 0; i < n; i++){
            if (checkValidation(chess, row, i)){
                chess[row][i] = 'Q';
                backtrack(res, chess, row+1);
                chess[row][i] = '.';
            }
        }
    }

    public boolean checkValidation(char[][] chess, int row, int col){
        int n = chess.length;
        // check column
        for (int i = 0; i < row; i++){
            if (chess[i][col] == 'Q')   return false;
        }
        // check right diagonal
        for (int i = row-1, j = col+1; i >= 0 && j < n; i--, j++){
            if (chess[i][j] == 'Q') return false;
        }
        // check left diagonal
        for (int i = row-1, j = col-1; i >=0 && j >= 0; i--, j--){
            if (chess[i][j] == 'Q') return false;
        }
        return true;
    }
}

