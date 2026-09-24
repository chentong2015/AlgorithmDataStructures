package queue;

import java.util.*;

public class BaseQueue {

    public static void main(String[] args) {
        // 设置的队列存储元素的初始容量，后续根据插入元素数量动态扩容
        // 默认最大容量限制 MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        Queue<Integer> queue = new ArrayDeque<>(3);

        // TODO: 当使用容量限制的队列时优先使用offer()
        // add()方法在无空间时会抛出异常
        // offer()在无空间时只返回false
        queue.add(10);
        queue.offer(5);
        queue.offer(15);

        queue.offer(null); // 存储空值可能造成出队后判断异常

        queue.remove();     // 从头部开始移除
        queue.remove(5); // 移除队列中特定的元素

        // 查看出队列元素但不移除，可能返回null，不能赋值给值类型
        Integer peekValue = queue.peek();

        // TODO. Poll轮询从队列中取出元素: 直接取可能抛出null
        while (!queue.isEmpty()) {
            int value = queue.poll();
        }
    }
}