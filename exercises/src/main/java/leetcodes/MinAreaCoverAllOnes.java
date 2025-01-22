package leetcodes;

// Find the Minimum Area to Cover All Ones I
// You are given a 2D binary array grid.
// Find a rectangle with horizontal and vertical sides with the smallest area,
// such that all the 1's in grid lie inside this rectangle.
// Return the minimum possible area of the rectangle.
//
// 1 <= grid.length, grid[i].length <= 1000
// grid[i][j] is either 0 or 1.
// The input is generated such that there is at least one 1 in grid.
public class MinAreaCoverAllOnes {

    // TODO. 最小矩形由宽和高两个长度来唯一确定，由4个点确定
    // 求能够框住所有1的最小矩形的面积
    // [0,1,0] -> 2*3=6
    // [1,0,1]
    //
    // [1,0]  -> 1*1=1
    // [0,0]
    //
    // 1 0 1 0 0 -> 4*4=16
    // 1 1 1 0 0
    // 0 0 1 0 0
    // 0 0 0 1 0
    //
    // O(n*m) 无论如何都需要遍历每一个值
    // O(1)
    public int minimumArea(int[][] grid) {
        int leftMin = -1;
        int rightMax = -1;
        int topMin = -1;
        int bottomMax = -1;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (leftMin == -1 || j < leftMin) {
                        leftMin = j;
                    }
                    if (rightMax == -1 || rightMax < j) {
                        rightMax = j;
                    }
                    if (topMin == -1) { // 记录第一次出现1的行数
                        topMin = i;
                    }
                    if (bottomMax == -1 || bottomMax < i) {
                        bottomMax = i;
                    }
                }
            }
        }
        return (rightMax - leftMin + 1) * (bottomMax - topMin + 1);
    }
}
