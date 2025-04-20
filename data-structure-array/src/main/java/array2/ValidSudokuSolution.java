package array2;

// Valid Sudoku
// Determine if a 9 x 9 Sudoku board is valid.
// Only the filled cells need to be validated according to the following rules:
// - Each row must contain the digits 1-9 without repetition.
// - Each column must contain the digits 1-9 without repetition.
// - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
//
// A Sudoku board (partially filled) could be valid but is not necessarily solvable.
// Only the filled cells need to be validated according to the mentioned rules.
//
// board.length == 9
// board[i].length == 9
// board[i][j] is a digit 1-9 or '.'.
public class ValidSudokuSolution {

    // TODO. 只需验证27个Block区域, 每个区域包含9个数字
    //[["5","3",".",".","7",".",".",".","."] -> true
    //,["6",".",".","1","9","5",".",".","."]
    //,[".","9","8",".",".",".",".","6","."]
    //,["8",".",".",".","6",".",".",".","3"]
    //,["4",".",".","8",".","3",".",".","1"]
    //,["7",".",".",".","2",".",".",".","6"]
    //,[".","6",".",".",".",".","2","8","."]
    //,[".",".",".","4","1","9",".",".","5"]
    //,[".",".",".",".","8",".",".","7","9"]]
    //
    //[["8","3",".",".","7",".",".",".","."] -> false
    //,["6",".",".","1","9","5",".",".","."]
    //,[".","9","8",".",".",".",".","6","."]
    //,["8",".",".",".","6",".",".",".","3"]
    //,["4",".",".","8",".","3",".",".","1"]
    //,["7",".",".",".","2",".",".",".","6"]
    //,[".","6",".",".",".",".","2","8","."]
    //,[".",".",".","4","1","9",".",".","5"]
    //,[".",".",".",".","8",".",".","7","9"]]
    //
    // O(1) 有限的时间和空间复杂度
    // O(1)
    public boolean isValidSudoku(char[][] board) {
        int[][] blockNums = new int[27][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c == '.') {
                    continue;
                }
                // 判断行数和列数的有效性
                if (blockNums[i][c - '1'] > 0 || blockNums[9 + j][c - '1'] > 0) {
                    return false;
                }
                blockNums[i][c - '1']++;
                blockNums[9 + j][c - '1']++;

                // TODO. 注意最后9个Block块的偏移计算
                int index = 18 + (i/3) * 3 + j/3;
                if (blockNums[index][c - '1'] > 0) {
                    return false;
                }
                blockNums[index][c - '1']++;
            }
        }
        return true;
    }
}
