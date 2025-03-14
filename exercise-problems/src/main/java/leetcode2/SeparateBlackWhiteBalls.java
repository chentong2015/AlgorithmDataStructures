package leetcode2;

// Separate Black and White Balls
// You are given a 0-indexed binary string s of length n,
// where 1 and 0 represent black and white balls, respectively.
// In each step, you can choose two adjacent balls and swap them.
//
// Return the minimum number of steps to
// group all the black balls to the right and all the white balls to the left.
//
// s = "101" -> "011" -> 1
// s = "100" -> 2
//   - Swap s[0] and s[1], s = "010".
//   - Swap s[1] and s[2], s = "001".
// s = "0111" -> 0
public class SeparateBlackWhiteBalls {

    // TODO. 算法本质: 初始0数据的坐标和 - 归位后0数据的坐标和
    //
    // 只考虑最终数据归位后的效果
    // 1 0 0 1 0 1 1 1 0 0
    //   1 2   4       9 10
    // ...
    // ...
    // 0 0 0 0 0 1 1 1 1 1
    // 0 1 2 3 4
    public long minimumSteps(String s) {
        int countZero = 0;
        long sumZeroIndex = 0;
        for (int index = 0; index < s.length(); index++) {
            if (s.charAt(index) == '0') {
                countZero++;
                sumZeroIndex += index;
            }
        }
        // 返回剩余需要移动的总步数
        long targetZeroIndex = (long) (countZero - 1) * countZero / 2;
        return sumZeroIndex - targetZeroIndex;
    }
}
