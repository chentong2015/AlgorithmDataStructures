package arraylist;

import java.util.ArrayList;
import java.util.List;

// Pascal's Triangle
// Given an integer numRows, return the first numRows of Pascal's triangle.
// In Pascal's triangle, each number is the sum of the two numbers directly above it:
//
//      1  0  0  0  0 ...
//     1  1  0  0  0     level=2
//    1 1+1 1  0  0      level=3
//   1  3  3  1  0       level=4
//  1  4  6  4  1        level=5
//
// 1 <= numRows <= 30
public class PascalsTriangle {

    // TODO. 注意List上下两行循环的坐标
    // numRows = 5
    // [   [1],     1  0
    //    [1,1],    2  1
    //   [1,2,1],   3  2
    //  [1,3,3,1],  4  3
    // [1,4,6,4,1]] 5  4
    //
    // O(1+2+3+..N) 总共计算的数据
    // O(1+2+3+..N) 总共存储的数据
    public List<List<Integer>> generate(int numRows) {
         List<List<Integer>> result = new ArrayList<>();
         result.add(List.of(1));

         int row = 2;
         while (row <= numRows) {
             if (row == 2) {
                 result.add(List.of(1, 1));
             } else {
                 List<Integer> listNew = new ArrayList<>();
                 listNew.add(1);

                 // 通过上一层的数据来计算当前层的数据
                 List<Integer> listRow = result.get(row - 2);
                 for (int index = 1; index <= row - 2; index++) {
                     listNew.add(listRow.get(index-1) + listRow.get(index));
                 }

                 listNew.add(1);
                 result.add(listNew);
             }
             row++;
         }
         return result;
    }
}
