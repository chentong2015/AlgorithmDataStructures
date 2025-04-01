package search_DFS.cycles_2d_grid;

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
    // O(N*M) 逻辑层面每个位置只会被遍历一次
    // O(N*M) 开辟空间标记是否被遍历过

    private int n;
    private int m;
    private char[][] mat;
    private boolean[][] visited;
    public static final int[][] dirs = {{0,-1},{-1,0},{0,1},{1,0}};

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
                // 如果从某个特定的点触发，能够DFS出环路则结束
                if (dfs(i, j, -1, -1, mat[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    // 从特定坐标位置出发，遍历递归特定的char字符
    private boolean dfs(int x, int y, int lastX, int lastY, char ch) {
        visited[x][y] = true;
        // 往(x,y)坐标的四个方向移动
        for (int[] dir : dirs) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];

            // 不能再回去移动到它的上一个坐标
            if (nextX == lastX && nextY == lastY) {
                continue;
            }
            // 如果坐标越界则不考虑
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                continue;
            }
            // 如果下一步的字符不一致则不考虑
            if (mat[nextX][nextY] != ch) {
                continue;
            }
            // TODO. 如果下一个坐标已经被标记遍历，则形成环路
            if (visited[nextX][nextY]) {
                return true;
            }
            // 继续下一个位置的DFS深度遍历
            if (dfs(nextX, nextY, x, y, ch)) {
                return true;
            }
        }
        return false;
    }
}
