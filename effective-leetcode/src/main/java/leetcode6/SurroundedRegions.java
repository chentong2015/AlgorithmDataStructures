package leetcode6;

// Surrounded Regions
// You are given an m x n matrix board containing letters 'X' and 'O',
// capture regions that are surrounded:
//   Connect: A cell is connected to adjacent cells horizontally or vertically.
//   Region: To form a region connect every 'O' cell.
//   Surround: A region is surrounded if none of the 'O' cells in that
//            region are on the edge of the board.
//            Such regions are completely enclosed by 'X' cells.
//
// To capture a surrounded region, replace all 'O's with 'X's in-place
// within the original board. You do not need to return anything.
public class SurroundedRegions {

    // TODO. DFS从边缘递归遍历，标记不能被更改的Cell
    //
    // ["X","X","X","X"],     ["X","X","X","X"]
    // ["X","O","O","X"],  -> ["X","X","X","X"]
    // ["X","X","O","X"],     ["X","X","X","X"]
    // ["X","O","X","X"],     ["X","O","X","X"]
    //
    // O(M+N+M+N + M*N + M*N)
    // O(1)
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        for (int col = 0; col < n; col++) { // top & bottom
            if (board[0][col] == 'O') {
                dfs(board, 0, col);
            }
            if (board[m-1][col] == 'O') {
                dfs(board, m - 1, col);
            }
        }
        for (int row = 0; row < m; row++) { // left & right
            if (board[row][0] == 'O') {
                dfs(board, row, 0);
            }
            if (board[row][n-1] == 'O') {
                dfs(board, row, n - 1);
            }
        }

        // Set all remaining 'O' to 'X'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }

        // Set all '1' to 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '1') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int row, int col) {
        int m = board.length;
        int n = board[0].length;
        if (row < 0 || row == m || col < 0 || col == n) {
            return;
        }
        if (board[row][col] == '1') {
            return; // 已经标记过，避免无限循环
        }

        board[row][col] = '1';
        if (row < m - 1 && board[row+1][col] == 'O') {
            dfs(board, row+1, col);
        }
        if (row > 0 && board[row-1][col] == 'O') {
            dfs(board, row-1, col);
        }
        if (col < n -1 && board[row][col + 1] == 'O') {
            dfs(board, row, col + 1);
        }
        if (col > 0 && board[row][col-1] == 'O') {
            dfs(board, row, col-1);
        }
    }
}
