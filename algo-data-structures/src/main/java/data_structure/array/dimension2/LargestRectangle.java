package data_structure.array.dimension2;

// Largest Rectangle
// Given a row's x cols binary matrix filled with O's and 1's,
// find the largest rectangle containing only 1's and return its area.
//
// matrix = [
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
// ]
// Output: 6
//
// O(n*m*(n*m + ... ?)) 最差情况下会造成数列形式的复杂度
// O(1)
public class LargestRectangle {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,0,1,1,1},
                {1,0,1,1,1},
                {1,1,0,1,0},
                {0,1,1,0,1}};
        System.out.println(getLengthDown(matrix, 0, 2));
        System.out.println(getLengthDown(matrix, 1, 2));
        System.out.println(findLargestRectangle(matrix));
    }

    private static int findLargestRectangle(int[][] matrix) {
        int maxRectangle = 0;
        for (int i=0; i < matrix.length; i++) {
            for (int j=0; j < matrix[0].length; j++) {

                // 计算从当前坐标位置点出发，能够形成的最大长方形的面积
                int col = j;
                int maxDeepBefore = matrix.length + 1;
                while (col < matrix[0].length && matrix[i][col] == 1) {
                    int currentDeep = getLengthDown(matrix, i, col);
                    maxDeepBefore = Math.min(maxDeepBefore, currentDeep);

                    int rectangle = maxDeepBefore * (col - j + 1);
                    maxRectangle = Math.max(maxRectangle, rectangle);
                    col++;
                }
            }
        }
        return maxRectangle;
    }

    // 找到一个位置点往下延伸的最大深度
    private static int getLengthDown(int[][] matrix, int row, int col) {
        int index = row + 1;
        while (index < matrix.length && matrix[index][col] == 1) {
            index++;
        }
        return index - row;
    }
}
