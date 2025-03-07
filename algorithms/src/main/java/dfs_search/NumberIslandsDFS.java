package dfs_search;

// TODO. DFS 深度优先遍历金典题目
// Number of Islands
// Input: grid = [
//  ["1","1","0","0","0"],
//  ["1","1","0","0","0"],
//  ["0","0","1","0","0"],
//  ["0","0","0","1","1"]
// ]
// Output: 3
//
// O(n*m) 内层DFSMarking递归算法最多更新所有的cell一次
// O(1)   空间复杂度降到最低
public class NumberIslandsDFS {

    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 只要有1出现便进行count的统计
                if (grid[i][j] == '1') {
                    searchIslandDFS(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private void searchIslandDFS(char[][] grid, int i, int j) {
        // 超出边界条件的index直接忽略
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length) {
            return;
        }
        // 判断grid[i][j] != '1', 避免无限循环导致的栈溢出
        if (grid[i][j] != '1') {
            return;
        }

        // 修改二维数组的元素值来避免DFS无限循环
        grid[i][j] = '0';

        // Deap Search 从一个点延伸四个方向的搜索
        searchIslandDFS(grid, i + 1, j);
        searchIslandDFS(grid, i - 1, j);
        searchIslandDFS(grid, i, j + 1);
        searchIslandDFS(grid, i, j - 1);
    }
}
