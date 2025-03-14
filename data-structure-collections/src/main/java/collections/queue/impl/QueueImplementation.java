package collections.queue.impl;

import java.util.ArrayList;
import java.util.List;

// TODO. 自定义实现一个"队列"类型
// 1. 使用动态数组，支持不断的往队列中扩充元素
// 2. 需要指针始终指向出队列的那个元素
public class QueueImplementation {

    private int startIndex = 0;
    private List<Integer> data = new ArrayList<>();

    public boolean enQueue(int item) {
        data.add(item);
        return true;
    }

    // 真正的出队操作，移除掉队首元素
    public boolean deQueue() {
        if (startIndex < data.size()) {
            startIndex++;
            return true;
        }
        return false;
    }

    // TODO. 使用列表在出队时会造成O(n)的复杂度 ！
    public int front() {
        return data.get(startIndex);
    }

    public boolean isEmpty() {
        return startIndex >= data.size();
    }
}
