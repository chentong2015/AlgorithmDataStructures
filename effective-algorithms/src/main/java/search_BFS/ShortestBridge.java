package search_BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

// Shortest Bridge
// You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
// An island is a 4-directionally connected group of 1's not connected to any other 1's.
// There are exactly two islands in grid.
//
// You may change 0's to 1's to connect the two islands to form one island.
// Return the smallest number of 0's you must flip to connect the two islands.
//
// n == grid.length == grid[i].length
// 2 <= n <= 100
// grid[i][j] is either 0 or 1.
// There are exactly two islands in grid.
public class ShortestBridge {

    // TODO. DFS找出岛屿所有坐标 + BFS从岛屿往外一圈圈扩散
    // [0,1,0] -> 2
    // [0,0,0],
    // [0,0,1]
    //
    // [1,1,1,1,1], -> 1
    // [1,0,0,0,1],
    // [1,0,1,0,1],
    // [1,0,0,0,1],
    // [1,1,1,1,1]]
    //
    // [0,1,0,0,0], -> 4
    // [0,1,0,1,1],
    // [0,0,0,0,1],
    // [0,0,0,0,0],
    // [0,0,0,0,0]
    //
    // O(N) 需要遍历所有位置
    // O(N) 最差情况时需要存储几乎所有的位置
    public int shortestBridge(int[][] grid) {
        // DFS find all positions for the first island
        ArrayList<int[]> islandPositions = getIslandPositions(grid);

        // BFS find the shortest steps to the second island
        Queue<int[]> nextPositions = new ArrayDeque<>();
        for (int[] position : islandPositions) {
            bfs(grid, position, nextPositions);
        }
        return findShortestSteps(grid, nextPositions);
    }

    private ArrayList<int[]> getIslandPositions(int[][] grid) {
        ArrayList<int[]> islandPositions = new ArrayList<>();
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == 1) {
                    dfs(grid, row, col, islandPositions);
                    return islandPositions;
                }
            }
        }
        return islandPositions;
    }

    // -1 DFS之后的标记值
    private void dfs(int[][] grid, int row, int col, ArrayList<int[]> islandPositions) {
        islandPositions.add(new int[] {row, col});
        grid[row][col] = -1;

        if (row > 0 && grid[row - 1][col] == 1) {
            dfs(grid, row - 1, col, islandPositions);
        }
        if (row < grid.length - 1 && grid[row + 1][col] == 1) {
            dfs(grid, row + 1, col, islandPositions);
        }
        if (col > 0 && grid[row][col - 1] == 1) {
            dfs(grid, row, col - 1, islandPositions);
        }
        if (col < grid[0].length - 1 && grid[row][col + 1] == 1) {
            dfs(grid, row, col + 1, islandPositions);
        }
    }

    // TODO. BFS 基于Queue的层级遍历, 标记遍历过的位置
    private int findShortestSteps(int[][] grid, Queue<int[]> nextPositions) {
        int numSteps = 0;
        while (!nextPositions.isEmpty()) {
            int length = nextPositions.size();
            for (int index = 0; index < length; index++) {
                int[] position = nextPositions.poll();
                int row = position[0];
                int col = position[1];
                if ((row > 0 && grid[row - 1][col] == 1)
                    || (row < grid.length - 1 && grid[row + 1][col] == 1)
                    || (col > 0 && grid[row][col - 1] == 1)
                    || (col < grid[0].length - 1 && grid[row][col + 1] == 1)) {
                    return numSteps + 1;
                }
                // 从当前位置找下一层的位置坐标
                bfs(grid, position, nextPositions);
            }
            numSteps++;
        }
        return numSteps;
    }

    // 2 BFS后的标记值
    private void bfs(int[][] grid, int[] startPosition, Queue<int[]> nextPositions) {
        int row = startPosition[0];
        int col = startPosition[1];
        if (row > 0 && grid[row - 1][col] == 0) {
            nextPositions.add(new int[] {row - 1, col});
            grid[row - 1][col] = 2;
        }
        if (row < grid.length - 1 && grid[row + 1][col] == 0) {
            nextPositions.add(new int[] {row + 1, col});
            grid[row + 1][col] = 2;
        }
        if (col > 0 && grid[row][col - 1] == 0) {
            nextPositions.add(new int[] {row, col - 1});
            grid[row][col - 1] = 2;
        }
        if (col < grid[0].length - 1 && grid[row][col + 1] == 0) {
            nextPositions.add(new int[] {row, col + 1});
            grid[row][col + 1] = 2;
        }
    }
}
