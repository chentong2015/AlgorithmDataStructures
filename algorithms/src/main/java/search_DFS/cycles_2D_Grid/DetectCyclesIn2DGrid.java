package search_DFS.cycles_2D_Grid;

// Detect Cycles in 2D Grid
// Given a 2D array of characters grid of size m x n,
// you need to find if there exists any cycle consisting of the same value in grid.
// A cycle is a path of length 4 or more in the grid
// that starts and ends at the same cell.
//
// From a given cell, you can move to one of the cells adjacent to it
// in one of the four directions (up, down, left, or right),
// if it has the same value of the current cell.
//
// Also, you cannot move to the cell that you visited in your last move.
// For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid
// because from (1, 2) we visited (1, 1) which was the last visited cell.
//
// Return true if any cycle of the same value exists in grid, otherwise, return false.
//
// m == grid.length
// n == grid[i].length
// 1 <= m, n <= 500
// grid consists only of lowercase English letters.
public class DetectCyclesIn2DGrid {

    // TODO. 判断使用DFS的问题场景，深入递归判断
    // ["a","a","a","a"], -> true
    // ["a","b","b","a"],
    // ["a","b","b","a"],
    // ["a","a","a","a"]
    //
    // ["c","c","c","a"], -> true
    // ["c","d","c","c"],
    // ["c","c","e","c"],
    // ["f","c","c","c"]]
    //
    // ["a","b","b"], -> false
    // ["b","z","b"],
    // ["b","b","a"]
    //
    // ["b","a","c"], -> false
    // ["c","a","c"],
    // ["d","d","c"],
    // ["b","c","c"]
    //

    public static final int[][] dirs = {{0,-1},{-1,0},{0,1},{1,0}};
    private char[][] mat;
    private boolean[][] visited;
    private int n;
    private int m;

    public boolean containsCycle(char[][] grid) {
        mat = grid;
        n = grid.length;
        m = grid[0].length;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }
                if (dfs(i, j, -1, -1, mat[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(int x, int y, int lastx, int lasty, char ch) {
        visited[x][y] = true;
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx == lastx && ny == lasty) {
                continue;
            }
            if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                continue;
            }
            if (mat[nx][ny] != ch) {
                continue;
            }
            if (visited[nx][ny]) {
                return true;
            }
            if (dfs(nx, ny, x, y, ch)) {
                return true;
            }
        }
        return false;
    }
}
