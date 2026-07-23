package live_coding.google.top50;

import java.util.Arrays;
import java.util.Random;

// Random Pick with Weight
// You are given a 0-indexed array of positive integers w where w[i] describes the weight of the ith index.
//
// You need to implement the function pickIndex(), which randomly picks an index in the range [0, w.length - 1]
// (inclusive) and returns it. The probability of picking an index i is w[i] / sum(w).
//
// For example, if w = [1, 3], the probability of picking index 0 is 1 / (1 + 3) = 0.25 (i.e., 25%),
// and the probability of picking index 1 is 3 / (1 + 3) = 0.75 (i.e., 75%).
public class RandomPickWithWeight {

    // TODO. 权重的本质是数据的范围区间
    // 1   2   3   4
    // 10%  20%  30%  40% = 1000%
    // Index -> weight -> use a range ?
    // 0 -> 0       -> 0, 0  -> 0
    // 1 -> 1 1     -> 1, 2  -> 1
    // 2 -> 2 2 2   -> 3, 5  -> 3
    // 3 -> 3 3 3 3 -> 6, 9  -> 6

    private int[] array;
    private int startPoint = 0;

    // O(N)
    public RandomPickWithWeight(int[] w) {
        for (int index = 0; index < w.length; index++) {
            int value = w[index];
            w[index] = startPoint;
            startPoint += value;
        }
        this.array = w;
    }

    // O(LogN) 可以直接手写二分发查询
    public int pickIndex() {
        Random random = new Random();
        int rIndex = random.nextInt(0, startPoint);
        int targetIndex = Arrays.binarySearch(array,0, array.length, rIndex); // logN
        if (targetIndex < 0) {
            targetIndex = -(targetIndex) - 1; // get insertion point
            return targetIndex - 1; // 找到的插入范围属于前面坐标范围
        } else {
            return targetIndex; // find the start point value
        }
    }
}