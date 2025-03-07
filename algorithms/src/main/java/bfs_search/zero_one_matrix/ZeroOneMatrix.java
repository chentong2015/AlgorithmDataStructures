package bfs_search.zero_one_matrix;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 01 Matrix
// Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell
// The distance between two adjacent cells is 1
public class ZeroOneMatrix {

    // TODO: 借助Queue队列广度遍历到所有的值，统计从0移动到非0位置的步数，逐次累加
    // mat = [0,0,0], ->  [0,  0,   0]    ->  [0,0,0]
    //       [0,1,0],     [0,  max, 0]        [0,1,0]
    //       [1,1,1]      [max,max, max]      [1,2,1]
    //
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        int m = matrix.size();
        int n = matrix.get(0).size();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix.get(i).get(j) == 0) {
                    queue.add(new int[]{i, j});
                } else {
                    matrix.get(i).set(j, Integer.MAX_VALUE);
                }
            }
        }

        // 对于队列中的位置(值为0)分别移动4个方向，找到提前标记的MAX_VALUE
        // 将其位置的值在前一个Cell值的基础上+1 (到达的步数会累积起来)
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : directions) {
                int row = cell[0] + direction[0];
                int col = cell[1] + direction[1];
                if (row < 0 || row >= m || col < 0 || col >= n) continue;

                // 只有当移动后的位置上的值比原来基础上的值+1大的时候，才添加到队列中，并在原来位置的基础上+1步数
                int stepCellValueBefore = matrix.get(cell[0]).get(cell[1]);
                if (matrix.get(row).get(col) > stepCellValueBefore + 1) {
                    queue.add(new int[]{row, col});
                    matrix.get(row).set(col, stepCellValueBefore + 1);
                }
            }
        }
        return matrix;
    }
}
