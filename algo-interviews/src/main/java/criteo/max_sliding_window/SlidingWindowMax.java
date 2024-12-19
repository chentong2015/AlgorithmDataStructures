package criteo.max_sliding_window;

import java.util.LinkedList;

public class SlidingWindowMax {

    public static void main(String[] args) {
        int[] nums = {2, 5, 3, 4, 1, 8};
        int[] nums2 = {1, 3, -1, -3, 5, 3, 6, 7};

        int[] results = maxSlidingWindow(nums2, 3);
        for (int item: results) {
            System.out.println(item);
        }
    }

    // TODO. 数据压缩思想: 将遍历的数据压缩到LinkedList<>存储空间，用于计算结果
    //  链表存储数据坐标位置，减少存储空间
    //  链表存储时剔除无用的前面小数据，减少存储的长度
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return nums;
        }
        int[] result = new int[nums.length - k + 1];
        LinkedList<Integer> dq = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            if (!dq.isEmpty() && dq.peek() < i - k + 1) {
                dq.poll(); // 移除Head 窗口之外的坐标数据
            }
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast(); // 将前面的比当前位置小的数据移除
            }

            dq.offer(i); // 加入当前的位置，LinkedList中前面有更大的数据或者没有
            if (i - k + 1 >= 0) {
                result[i - k + 1] = nums[dq.peek()]; // 取Head 位置坐标数据
            }
        }
        return result;
    }
}
