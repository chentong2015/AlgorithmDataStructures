package tree.prefix_tree.word_search;

public class WordSearchDFS {

    // TODO. DFS探索节点组合数量可超过中node节点数
    // DFS的“搜索节点数量” ≠ board中格子的数量
    // 每个node可能作为不同字符串的一个字符
    //
    // O(N*M * 4×3^L−1)= O(N*M * 3^L)  L为字符长度, 以指数倍数扩展
    // O(N*M + L)                      临时数组+栈的深度/字符串长度
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        if (m*n < word.length()) {
            return false; // 即便组合全部字符也够不成
        }

        boolean[][] visited = new boolean[n][m];
        for(int row = 0; row < n; row++){
            for(int col = 0; col < m; col++){
                if(board[row][col] != word.charAt(0)){
                    continue;
                }
                if(dfs(row, col, word, 0, visited, board)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(int row, int col, String word, int stepIndex, boolean[][] visited, char[][] board){
        if(stepIndex == word.length()){
            return true;
        }
        if(row < 0 || row >= board.length || col < 0 || col >= board[0].length){
            return false;
        }
        if(visited[row][col] || board[row][col] != word.charAt(stepIndex)) {
            return false;
        }

        // 从某个点往四种移动，然后执行回溯
        visited[row][col] = true;
        boolean isFound = dfs(row + 1, col, word, stepIndex + 1, visited, board)
                || dfs(row - 1, col, word, stepIndex + 1, visited, board)
                || dfs(row, col + 1, word, stepIndex + 1, visited, board)
                || dfs(row, col - 1, word, stepIndex + 1, visited, board);
        visited[row][col] = false;

        // 每个节点递归完成后都需要返回结果
        return isFound;
    }
}
