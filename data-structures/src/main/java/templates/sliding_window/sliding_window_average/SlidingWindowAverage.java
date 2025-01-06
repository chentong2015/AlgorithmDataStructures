package templates.sliding_window.sliding_window_average;

import java.util.ArrayList;
import java.util.List;

public class SlidingWindowAverage {

    // TODO. 滑动过程中如果窗口的Size确定，则可以只使用一个Index
    // Sliding Average
    // Calculate the average in windows size k, return list of averages
    //
    // input = 2 5 3 1 8, k = 3
    // output = 3, 3, 4
    //
    // O(n)     时间基于遍历完所有数据
    // O(n-k+1) 空间基于会产生多少结果，最糟糕是O(n), 最佳为O(1)
    public static List<Integer> getSlidingAverage(int[] nums, int k) {
        List<Integer> averageList = new ArrayList<>();
        // For special cases and parameters
        if (nums == null || nums.length < k) {
            return averageList;
        }
        if (k == 1) {
            for (int num: nums) {
                averageList.add(num);
            }
            return averageList;
        }

        // For the first k numbers average
        int index = 0;
        int sum = 0;
        while (index < k) {
            sum += nums[index];
            index++;
        }
        averageList.add(sum / k);

        // For remaining of the numbers
        while (index < nums.length) {
            sum += nums[index];
            sum -= nums[index - k]; // remove the first num
            averageList.add(sum / k);
            index++;
        }
        return averageList;
    }
}
