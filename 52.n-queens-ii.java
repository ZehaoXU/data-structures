/*
 * @lc app=leetcode id=52 lang=java
 *
 * [52] N-Queens II
 */
class Solution {
    /**
     * 回溯，模仿Q51的解法
     */
    int count = 0;
    public int totalNQueens(int n) {
        char[][] chess = new char[n][n];
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                chess[i][j] = '.';
            }
        }
        backtrack(chess, 0);
        return count;
    }

    public void backtrack(char[][] chess, int row){
        int n = chess.length;
        if (row == n){
            count++;
            return;
        }
        for (int i = 0; i < n; i++){
            if (checkValidation(chess, row, i)){
                chess[row][i] = 'Q';
                backtrack(chess, row+1);
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

