package leetcode4.cycles_2D_Grid;

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

    // TODO. BFS遍历，修改原始值，判断联通的字符中，坐标是否构成环路
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
    // (0,0)  >  (0,1)
    //   ^         v
    // (1,0)  <  (1,1)
    public boolean containsCycle(char[][] grid) {


        return false;
    }
}
