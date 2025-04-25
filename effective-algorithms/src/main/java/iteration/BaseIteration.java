package iteration;

public class BaseIteration {




    // TODO: 在循环的过程中迭代前面计算过的值
    // 第一步有一种可能，
    // 第二步有两种可能，
    // 第三步是前两种情况的和(从第一步和第二步都能够走到第三步)
    //
    // O(n) O(1)
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
