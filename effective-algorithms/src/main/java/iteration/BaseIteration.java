package iteration;

// Fibonacci数列 Fib(n)=Fib(n−1)+Fib(n−2)
public class BaseIteration {

    // TODO: 借助动态编程思想进行迭代
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
}
