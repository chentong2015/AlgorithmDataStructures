package templates.sliding_window.sliding_window_min;

// Sliding Window Minimum
// [1,3,5,2,7], 3
// output: [1,2,2]
public class SlidingWindowMin {

    // 计算指定长度的窗口中的最小值
    private int[] findSlidingWindowMin(int[] nums, int length) {

        return new int[nums.length - length + 1];
    }
}
