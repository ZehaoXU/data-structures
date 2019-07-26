/*
 * @lc app=leetcode id=36 lang=java
 *
 * [36] Valid Sudoku
 */
class Solution {
    /**
     * 分三步检查：行/列/方块；用一个长度10的数组代表1-9的出现次数，保证每行/每列/每个方块1-9不会重复出现
     * 时间复杂度 O(n^2);　空间复杂度 O(n); n其实固定为9
     * Your runtime beats 100 % of java submissions
     * Your memory usage beats 84.27 % of java submissions (43.6 MB)
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        // rows validation
        for (int i = 0; i < 9; i++){
            int[] temp = new int[10];
            for (int j = 0; j < 9; j++){
                if (board[i][j] != '.'){
                    int num = board[i][j] - '0';
                    if (temp[num] == 0)
                        temp[num] = 1;
                    else
                        return false;
                }
            }
        }
        // columns validation
        for (int i = 0; i < 9; i++){
            int[] temp = new int[10];
            for (int j = 0; j < 9; j++){
                if (board[j][i] != '.'){
                    int num = board[j][i] - '0';
                    if (temp[num] == 0)
                        temp[num] = 1;
                    else
                        return false;
                }
            }
        }
        //sub-box validation
        for (int i = 0; i < 9; i=i+3){
            for (int j = 0; j < 9; j=j+3){
                int[] temp = new int[10];
                for (int ii = i; ii < i+3; ii++){
                    for (int jj = j; jj < j+3; jj++){
                        if (board[ii][jj] != '.'){
                            int num = board[ii][jj] - '0';
                            if (temp[num] == 0)
                                temp[num] = 1;
                            else
                                return false;
                        }
                    }
                }
            }
        }

        return true;
    }
}

