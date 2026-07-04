package stack;

import java.util.Stack;

public class BaseStack {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        // TODO. 入栈出栈符合"FILO"先入后出
        stack.push("item1");
        stack.push("item2");
        stack.push("item3");
        stack.push("item4");
        stack.push("item5");
        // item5
        System.out.println(stack.pop());

        // TODO. 循环遍历栈按照"Index"插入顺序
        // item1
        // item2
        // item3
        // item4
        for (String item: stack) {
            System.out.println(item);
        }
        // 4
        System.out.println(stack.size());
    }
}
