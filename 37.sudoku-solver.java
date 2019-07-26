/*
 * @lc app=leetcode id=37 lang=java
 *
 * [37] Sudoku Solver
 */
class Solution {
    /**
     * 回溯法找数独解，每一层找一个位置的解，借助回溯树理解
     * 时间复杂度 O(9^81)
     * 
     * @param board
     */
    public void solveSudoku(char[][] board) {
        boolean res = backtrack(board, 0, 0);
        System.out.println(res);
    }

    public boolean backtrack(char[][] board, int row, int col){
        if (col == 9){
            if (row == 8)   return true;
            else{
                col = 0;
                row ++;
            }
        }
        if (board[row][col] != '.')
            return backtrack(board, row, col+1);

        for (char i = '1'; i <= '9'; i++){
            if (checkValidation(board, row, col, i)){
                if (backtrack(board, row, col+1))
                    return true;
            }
        }
        // 下面这步非常关键，如果什么都填不进去，那么回归空！之后再回溯到上一层
        board[row][col] = '.';
        return false;
    }

    // 检查新加入元素的可行性，如果可行就添加；只需检查该行/列/方块
    public boolean checkValidation(char[][] board, int row, int col, char val){
        // check row
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val && i != col)   return false;
        }
        // check line
        for (int i = 0; i < 9; i++){
            if (board[i][col] == val && i != row)   return false;
        }
        // check sub-box
        int rowNum = row / 3, colNum = col / 3;
        rowNum *= 3;
        colNum *= 3;
        for (int i = rowNum; i < rowNum + 3; i++){
            for (int j = colNum; j < colNum + 3; j++){
                if (i != row|| j != col){
                    if (board[i][j] == val) return false;
                }
            }
        }
        board[row][col] = val;
        return true;
    }
}

