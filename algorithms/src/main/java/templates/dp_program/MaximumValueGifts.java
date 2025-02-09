package templates.dp_program;

public class MaximumValueGifts {

    // TODO: 典型的DP二维数组的加法，从开始推理到最后一个值，每一步取最优，则最后一步一定是最优解 !!
    // Maximum value of gifts
    // A gift is placed on each grid of an m*n chessboard, and each gift has a certain value (value greater than 0).
    // You can start from the upper left corner of the board to get the gifts in the grid,
    // and move to the right or down one square at a time until you reach the lower right corner of the board
    //  [1,3,1],  ==> 1 -> 3 -> 5 -> 2 -> 1 ==> 12 最大收获的路径
    //  [1,5,1],
    //  [4,2,1]
    public int maxValueGifts(int[][] gifts) {
        int row = gifts.length;
        int col = gifts[0].length;
        int[][] dp = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = gifts[0][0];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + gifts[i][j];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + gifts[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + gifts[i][j];
                }
            }
        }
        return dp[row - 1][col - 1];
    }
}
