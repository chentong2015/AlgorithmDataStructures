package master.sliding_window.average_min_max;

import java.util.LinkedList;

// Sliding Window Maximum / Minimum 等效算法
public class SlidingWindowMax {

    // TODO. LinkedList链表存储"滑动窗口内递减数据"的坐标
    // - 对于Index位置值，只有它前面更大值对于后面找Max才有意义 !!
    // - 链表支持从最左侧头部移除和peek查看右侧尾部数据(不移除)
    //
    // O(N + N) 临时链表最多操作数据N次
    // O(K + K) 结果数组和临时链表存储空间
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }

        int resultIndex;
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            resultIndex = i - k + 1;
            // 移除窗口之外的坐标数据: 窗口的宽度定长
            if (!linkedList.isEmpty() && linkedList.peek() < resultIndex) {
                linkedList.poll();
            }

            // 将前面比当前位置小的值移除: 只有比当前位置更大的值才有意义
            while (!linkedList.isEmpty() && nums[linkedList.peekLast()] <= nums[i]) {
                linkedList.pollLast();
            }

            // 加入当前位置: Index前面有更大值或没有
            linkedList.offer(i);

            if (resultIndex >= 0) {
                int headIndex = linkedList.peek();
                result[resultIndex] = nums[headIndex]; // 头部位置的值一定是K区间中最大值
            }
        }
        return result;
    }
}
