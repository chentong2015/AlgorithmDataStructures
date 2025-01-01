package criteo.max_sliding_window;

import java.util.LinkedList;

// Sliding Window Maximum
// 使用额外的链表存储，以空间换取最佳的时间复杂度
public class SlidingWindowMax {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int[] results = maxSlidingWindow(nums, 3);
        for (int item: results) {
            System.out.println(item);
        }
    }

    // TODO. 数据压缩思想: 将遍历的数据压缩到LinkedList<>存储空间，用于计算结果
    //  - 链表存储数据Index坐标位置，减少存储空间
    //  - 链表存储时剔除无用的前面小数据，减少存储长度
    //  - LinkedList链表头部存储的窗口中最大值位置
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            // 移除窗口之外的坐标数据: 窗口的宽度定长
            if (!linkedList.isEmpty() && linkedList.peek() < i - k + 1) {
                linkedList.poll();
            }

            // 将前面的比当前位置小的数据移除: 注意这里比较值，而非比较坐标
            while (!linkedList.isEmpty() && nums[linkedList.peekLast()] <= nums[i]) {
                linkedList.pollLast();
            }

            // 加入当前的位置: Index前面有更大的数据或者没有
            linkedList.offer(i);

            if (i - k + 1 >= 0) {
                int headIndex = linkedList.peek();
                result[i - k + 1] = nums[headIndex];
            }
        }
        return result;
    }
}
