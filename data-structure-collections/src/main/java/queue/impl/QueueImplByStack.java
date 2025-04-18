package queue.impl;

import java.util.Stack;

// TODO. 使用两个Stack实现Queue队列的效果: Temp栈用于颠倒数据
public class QueueImplByStack {

    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> tempStack = new Stack<>();

    // O(n) O(n) 使用额外的数据结构作为转换，同时需要遍历出之前全部存储的数据
    // 后压入到栈中的数据保存压入到stack的最底下，顶部表示先压入的，需要先出栈
    public void push(int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            return;
        }
        while (!stack.isEmpty()) {
            tempStack.push(stack.pop());
        }
        stack.push(x);
        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    // 如果在push方法中没有使用临时stack作为转换存储，则取出的时候，可以使用临时stack做倒换 !!
    public int peek() {
        return stack.peek();
    }

    public int pop() {
        return stack.pop();
    }

    public boolean empty() {
        return stack.isEmpty();
    }
}
