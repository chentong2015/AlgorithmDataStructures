package collections.queue;

import java.util.*;

public class BaseQueue {

    // hen using a capacity-restricted queue, this method is generally preferable to add,
    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();

        // TODO. 区别两种API的不同
        queue.add(10);
        queue.offer(5);

        int size = queue.size();
        Integer peekValue = queue.peek(); // 查看出队列元素但不移除，可能返回null，不能赋值给值类型 !!
        if (!queue.isEmpty()) {
            int value = queue.poll(); // 取出第一个出队列元素
        }
    }
}
