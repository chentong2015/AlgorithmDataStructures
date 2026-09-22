package queue;

import java.util.*;

public class BaseQueue {

    public static void main(String[] args) {
        // 设置的队列存储元素的初始容量，并非存储量的限制
        // 根据插入元素的数量进行动态的扩容
        // 默认最大容量限制 MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
        Queue<Integer> queue = new ArrayDeque<>(3);

        // TODO: .add() vs .offer() 区别两种API
        // .add()   在不违反容量限制的情况下，将元素追加到队列末尾
        // .offer() 当使用容量限制的队列时，该方法会抛出Exception
        queue.add(10);

        // 可以存储Null空值，但可能造成出队列时的异常
        queue.offer(5);
        queue.offer(15);
        // queue.offer(null);

        queue.remove();    // 从头部开始移除
        queue.remove(5); // 移除队列中特定的元素

        // 查看出队列元素但不移除，可能返回null，不能赋值给值类型
        Integer peekValue = queue.peek();

        // TODO. Poll轮询从队列中取出元素: 直接取可能抛出null
        while (!queue.isEmpty()) {
            int value = queue.poll();
        }
    }
}