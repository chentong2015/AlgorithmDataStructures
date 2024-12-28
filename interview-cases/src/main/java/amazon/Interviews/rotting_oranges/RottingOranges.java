package amazon.Interviews.rotting_oranges;

import java.util.HashSet;
import java.util.Set;

// Rotting Oranges
// You are given an m x n grid where each cell can have one of three values:
//
// 0 representing an empty cell,
// 1 representing a fresh orange, or
// 2 representing a rotten orange.
// Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
//
// Return the minimum number of minutes that must elapse until no cell has a fresh orange.
// If this is impossible, return -1.
//
// [2,1,1],   [2,2,1],   [2,2,2],   [2,2,2],   [2,2,2],
// [1,1,0], > [2,1,0], > [2,2,0], > [2,2,0], > [2,2,0],
// [0,1,1]    [0,1,1],   [0,1,1],   [0,2,1],   [0,2,2],
public class RottingOranges {

    // TODO. 循环终止条件: 每次从2的位置开始扩散，直到到Grid中没有位置可以扩散
    public int orangesRotting(int[][] grid) {
        int minMinutes = 0;
        while (checkRotting(grid)) {
            minMinutes++;
        }
        return hasFreshOrange(grid) ? -1: minMinutes;
    }

    // TODO. 使用临时的Set集合进行缓存修改过的数据
    // 用于往外扩散的点2不能是当前这个For循环所修改的点
    private boolean checkRotting(int[][] grid) {
        Set<String> indexSets = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2 && !indexSets.contains(i + "-" + j)) {
                    if (i > 0 && grid[i-1][j] == 1) {
                        grid[i-1][j] = 2;
                        indexSets.add((i-1) + "-" + j);
                    }
                    if (i < grid.length - 1 && grid[i+1][j] == 1) {
                        grid[i+1][j] = 2;
                        indexSets.add((i+1) + "-" + j);
                    }
                    if (j > 0 && grid[i][j-1] == 1) {
                        grid[i][j-1] = 2;
                        indexSets.add(i + "-" + (j-1));
                    }
                    if (j < grid[0].length - 1 && grid[i][j+1] == 1) {
                        grid[i][j+1] = 2;
                        indexSets.add(i + "-" + (j+1));
                    }
                }
            }
        }
        return !indexSets.isEmpty();
    }

    private boolean hasFreshOrange(int[][] grid) {
        for (int i = 0; i<grid.length; i++) {
            for (int j=0; j <grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}
