package recursion;

public class BaseRecursion {

    // 使用迭代计算阶乘
    public static long factor(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factor(n - 1);
    }

    // TODO. 递归会造成巨大的复杂度(时间次数过多)
    // Fibonacci数列 Fib(n)=Fib(n−1)+Fib(n−2)
    public static long fibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
}
