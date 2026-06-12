package array2d;

// 二维数组的初级算法
//          0
//         111
//        22222
//       3333333
//      444444444
//     55555555555
//    6666666666666
//   777777777777777
//  88888888888888888
// 9999999999999999999
public class Array2Dimension {

    // TODO. 划分成2个部分作为输出
    // O(N)  循环的层数或次数
    // O(N*N)
    public static void print2Dimension() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 9 - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print(i);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        print2Dimension();
    }
}
