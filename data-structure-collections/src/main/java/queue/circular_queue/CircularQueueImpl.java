package queue.circular_queue;

public class CircularQueueImpl {

    // 通过head & tail没有办法严格的判断是否是空和满的状态
    // 指针head标记从什么位置出队，出队后指针后移(循环)
    // 指针tail标记从什么位置入队，入队后指针后移(循环)
    private int head;
    private int tail;
    private int[] queue;

    // 使用count来统计数组中的数目，用于判断空和满的状态
    private int count;

    public CircularQueueImpl(int length) {
        queue = new int[length];
        head = 0;  // head从0开始标记出队的位置
        tail = -1; // tail标识的始终是新值的左边位置
        count = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        tail = (tail + 1) % queue.length; // 循环取模
        queue[tail] = value; // 入队数据
        count++;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        int value = queue[head]; // 出队数据
        head = (head + 1) % queue.length; // 循环取模
        count--;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[head];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[tail];
    }

    // 直接通过统计的数目来判断array队列是否为空或满
    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count == queue.length;
    }
}
