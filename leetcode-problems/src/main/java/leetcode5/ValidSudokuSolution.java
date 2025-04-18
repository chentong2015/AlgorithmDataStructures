package leetcode5;

import java.util.HashSet;
import java.util.Set;

public class ValidSudokuSolution {

    // 将数字和它的所处位置关系，构建成一个唯一的key键值
    // Valid Sudoku
    // Determine if a 9 x 9 Sudoku board is valid
    // Input: board => true
    //      [["5","3",".",".","7",".",".",".","."]
    //      ,["6",".",".","1","9","5",".",".","."]
    //      ,[".","9","8",".",".",".",".","6","."]
    //      ,["8",".",".",".","6",".",".",".","3"]
    //      ,["4",".",".","8",".","3",".",".","1"]
    //      ,["7",".",".",".","2",".",".",".","6"]
    //      ,[".","6",".",".",".",".","2","8","."]
    //      ,[".",".",".","4","1","9",".",".","5"]
    //      ,[".",".",".",".","8",".",".","7","9"]]
    public boolean isValidSudokuTest(char[][] board) {
        // Test：核心在于验证数字的重复性，如何判断一个数字，在它对应的行，列和block局域中已经存在
        //       1(5)    表示5在第一行
        //       (5)1    表示5在第一列
        //       0(5)0   表示5在第一个block的位置
        Set<String> seen = new HashSet<>();
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    String b = "(" + board[i][j] + ")";
                    if (!seen.add(b + i) || !seen.add(j + b) || !seen.add(i / 3 + b + j / 3))
                        return false;
                }
            }
        }
        return true;
    }
}
