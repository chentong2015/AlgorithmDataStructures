package stack.impl;

import java.util.LinkedList;
import java.util.Queue;

// TODO. 使用两个Queue可实现Stack的效果: Temp队列用于颠倒数据
public class StackImplByQueue {

    // 用于存储数据的队列
    Queue<Integer> queue = new LinkedList<>();

    // 用于数据颠倒交换的队列
    Queue<Integer> queueSwitch = new LinkedList<>();

    public void push(int x) {
        while(!queue.isEmpty()){
            queueSwitch.add(queue.remove());
        }
        queue.add(x);
        while(!queueSwitch.isEmpty()){
            queue.add(queueSwitch.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
