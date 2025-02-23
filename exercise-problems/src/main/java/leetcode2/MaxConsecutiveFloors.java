package leetcode2;

import java.util.Arrays;

// Maximum Consecutive Floors Without Special Floors
// Alice has decided some of these floors should be special floors, used for relaxation only.
// You are given two integers bottom and top, which denote that
// Alice has rented all the floors from bottom to top (inclusive).
//
// You are also given the integer array special,
// where special[i] denotes a special floor that Alice has designated for relaxation.
// Return the maximum number of consecutive floors without a special floor.
//
// 1 <= special.length <= 10^5
// 1 <= bottom <= special[i] <= top <= 10^9
// All the values of special are unique.
public class MaxConsecutiveFloors {

    // TODO. 无需遍历所有的楼层，直接根据special数组计算最大间隔
    // bottom = 2, top = 9, special = [4,6]
    // 2 3 4 5 6 7 8 9
    //     s   s
    //  2    1     3 -> 3
    //
    // bottom = 6, top = 8, special = [7,6,8]
    // 6 7 8 -> 0
    //
    // O(N*logN) N is the number of special floor
    // O(1)
    public int maxConsecutive(int bottom, int top, int[] special) {
        Arrays.sort(special);

        int maxLength = special[0] - bottom;
        for (int index = 1; index < special.length; index++) {
            // 计算两个楼层的中间间隔，必须减1
            int length = special[index] - special[index - 1] - 1;
            maxLength = Math.max(maxLength, length);
        }

        // 最后计算右侧间隔，无需减1
        int lastLength = top - special[special.length - 1];
        maxLength = Math.max(maxLength, lastLength);

        return maxLength;
    }
}
