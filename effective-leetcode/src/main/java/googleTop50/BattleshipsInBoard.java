package googleTop50;

// Battleships in a Board
// Given an m x n matrix board where each cell is a battleship 'X' or empty '.',
// return the number of the battleships on board.
//
//Battleships can only be placed horizontally or vertically on board. In other words,
// they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column),
// where k can be of any size. At least one horizontal or vertical cell separates
// between two battleships (i.e., there are no adjacent battleships).
//
// m == board.length
// n == board[i].length
// 1 <= m, n <= 200
// board[i][j] is either '.' or 'X'.
public class BattleshipsInBoard {

    // TODO. 根据Battleships摆放特征: 不能修改传递的数组数据
    //  只需考虑船头，中部和尾部不计入统计
    //  只有当left和top位置为空才能成为船头(可以唯一代表整个船体)
    //
    // ["X",".",".","X"],
    // [".",".",".","X"],
    // [".",".",".","X"]
    //
    // O(N*M) one-pass
    // O(1)
    public int countBattleships(char[][] board) {
        int count = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == '.'){
                    continue;
                }
                if(i>0 && board[i-1][j] == 'X'){ // 船中和船尾不考虑
                    continue;
                }
                if(j>0 && board[i][j-1] == 'X'){ // 船中和船尾不考虑
                    continue;
                }
                count++;
            }
        }
        return count;
    }
}
