package iteration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseIteration {

    public static void main(String[] args) {
        List<Integer> values = new ArrayList<>();
        for (int index = 0; index < 10; index++) {
            values.add(index);
        }
        // TODO. 常规遍历时无法删除数据, 会抛出并发修改异常ConcurrentModificationException
        for (int value: values) {
            System.out.println(value);
            // values.remove(value);
        }

        // TODO. 使用Iterator迭代器在遍历的同时移除, 直到跳出while循环
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(values.size());
    }


    // TODO: 在循环的过程中迭代前面计算过的值
    // 第一步有一种可能，
    // 第二步有两种可能，
    // 第三步是前两种情况的和(从第一步和第二步都能够走到第三步)
    public int climbStairsFib(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    // Fibonacci数列 Fib(n)=Fib(n−1)+Fib(n−2)
    public int fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        int first = 0;
        int second = 1;
        int index = 2;
        while (index <= n) {
            int third = first + second;
            first = second;
            second = third;
            index++;
        }
        return second;
    }
}
