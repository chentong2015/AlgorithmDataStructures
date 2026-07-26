package googleTop50.pair_points;

// Maximum Number of Points with Cost
// Picking the cell at coordinates (r, c) will add points[r][c] to your score.
// For every two adjacent rows r and r + 1 (where 0 <= r < m - 1),
// picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.
public class MaxNumPointsWithCost {

    // TODO. DP编程过程记录, 降低维度
    //  存储上一行读取过的有效数据 + 当前行index位置左右两边的Max最值
    //
    // [1, 2, 3]
    // [1, 5, 1]
    // [3, 1, 1]
    //
    // O((m−1)⋅(n−1+n−1+n))=O(m⋅n) 内层的循环次数始终是N级别线性的
    // O(4⋅n)=O(n)
    public long maxPoints(int[][] points) {
        int rows = points.length;
        int cols = points[0].length;

        // Initialize the first row
        long[] previousRow = new long[cols];
        for (int col = 0; col < cols; ++col) {
            previousRow[col] = points[0][col];
        }

        for (int row = 0; row < rows - 1; ++row) {
            long[] leftMax = new long[cols];
            long[] rightMax = new long[cols];
            long[] currentRow = new long[cols];

            // Calculate left-to-right maximum 两点Pair问题/左侧
            leftMax[0] = previousRow[0];
            for (int col = 1; col < cols; ++col) {
                leftMax[col] = Math.max(leftMax[col-1] - 1, previousRow[col]);
            }

            // Calculate right-to-left maximum 两点Pair问题/右侧
            rightMax[cols - 1] = previousRow[cols - 1];
            for (int col = cols - 2; col >= 0; --col) {
                rightMax[col] = Math.max(previousRow[col], rightMax[col+1] - 1);
            }

            // Calculate the current row's maximum points 往下一行推导累计值
            for (int col = 0; col < cols; ++col) {
                long maxPointsBefore = Math.max(leftMax[col], rightMax[col]);
                currentRow[col] = maxPointsBefore + points[row+1][col];
            }
            previousRow = currentRow;
        }

        // Find the maximum value in the last processed row
        long maxPoints = 0;
        for (int col = 0; col < cols; ++col) {
            maxPoints = Math.max(maxPoints, previousRow[col]);
        }
        return maxPoints;
    }
}
