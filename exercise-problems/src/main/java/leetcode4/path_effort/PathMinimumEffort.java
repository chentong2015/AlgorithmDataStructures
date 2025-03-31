package leetcode4.path_effort;

// Path With Minimum Effort
// You are a hiker preparing for an upcoming hike.
// You are given heights, a 2D array of size rows x columns,
// where heights[row][col] represents the height of cell (row, col).
//
// You are situated in the top-left cell, (0, 0),
// and you hope to travel to the bottom-right cell (rows-1, columns-1)
// You can move "up, down, left, or right",
// and you wish to find a route that requires the minimum effort.
//
// A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
// Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
//
// rows == heights.length
// columns == heights[i].length
// 1 <= rows, columns <= 100
// 1 <= heights[i][j] <= 10^6
public class PathMinimumEffort {

    // TODO. 4方向移动的路径规划问题
    // [1,2,2] -> [0,1,1]
    // [3,8,2]    [2,5,1]
    // [5,3,5]    [2,2,2]
    //
    // [1,2,1,1,1],
    // [1,2,1,2,1],
    // [1,2,1,2,1],
    // [1,2,1,2,1],
    // [1,1,1,2,1]
    //
    // [1,2,1,1,1],
    // [1,2,1,2,1],
    // [1,2,1,2,1],
    // [1,2,1,2,1],
    // [1,1,1,2,1]
    //
    //
    // O(n*m) n is rows, m is cols
    // O(n*m) 需要暂存中间的循环结果用于比较
    public int minimumEffortPath(int[][] heights) {

        return 0;
    }
}
