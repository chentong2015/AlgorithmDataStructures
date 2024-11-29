package criteo;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowMax {

    // TODO. 使用一个Index来标识在Window中要找的结果
    // Sliding Max
    // Get the max value inside widows size k
    //
    // input = 2 5 3 4 1 8, k = 3
    // output = 5, 5, 4, 8
    //
    // O(n+n)   内层while和外层while分别最多只能运行n次
    // O(n-k+1)
    public static List<Integer> getSlidingMax(int[] nums, int k) {
        // Check conditions
        List<Integer> maxList = new ArrayList<>();
        int maxIndex = 0;
        int rightIndex = 0;

        // Get the first max value
        while (rightIndex < k) {
            if (nums[maxIndex] < nums[rightIndex]) {
                maxIndex = rightIndex;
            }
            rightIndex++;
        }
        maxList.add(nums[maxIndex]);

        while (rightIndex < nums.length) {
            if (maxIndex == rightIndex - k) { // if the max value is the first value
                maxIndex++;                   // Move maxIndex to next step, and refresh it
                int tempIndex = maxIndex;
                while (tempIndex <= rightIndex) {
                    if (nums[maxIndex] < nums[tempIndex]) {
                        maxIndex = tempIndex;
                    }
                    tempIndex++;
                }
            } else {
                if (nums[maxIndex] < nums[rightIndex]) {
                    maxIndex = rightIndex;     // 更新最大值的位置在窗口的右侧
                }
            }
            maxList.add(nums[maxIndex]);
            rightIndex++;
        }
        return maxList;
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 4, 1, 8};
        List<Integer> result = getSlidingMax(nums, 2);
        for (int item: result) {
            System.out.println(item);
        }
    }
}
