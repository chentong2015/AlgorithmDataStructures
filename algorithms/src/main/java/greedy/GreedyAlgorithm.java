package greedy;

public class GreedyAlgorithm {

    // Cut Rope
    // 把一根绳子剪成多段，并且使得每段的长度乘积最大
    // 将绳子拆成 1 和 n-1，则 1(n-1)-n=-1<0，即拆开后的乘积一定更小，所以不能出现长度为 1 的绳子
    // 将绳子拆成 2 和 n-2，则 2(n-2)-n = n-4，在 n>=4 时这样拆开能得到的乘积会比不拆更大
    // 将绳子拆成 3 和 n-3，则 3(n-3)-n = 2n-9，在 n>=5 时效果更好
    // 将绳子拆成 4 和 n-4，因为 4=2*2，因此效果和拆成2一样
    // 将绳子拆成 5 和 n-5，因为 5=2+3，而5<2*3，所以不能出现5的绳子，而是尽可能拆成2和3
    // 将绳子拆成 6 和 n-6，因为 6=3+3，而 6<3*3，所以不能出现6的绳子，而是拆成3和3
    // 继续拆成更大的绳子可以发现都比拆成 2 和 3 的效果更差，只考虑将绳子拆成2和3，并且优先拆成3
    public int cutRope(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;
        int timesOf3 = n / 3;
        // 优先考虑拆成3的长度，并计算拆出来的总乘积值
        if (n - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        int sumTimesOf3 = (int) (Math.pow(3, timesOf3));

        int timesOf2 = (n - timesOf3 * 3) / 2;
        int sumTimeOf2 = (int) (Math.pow(2, timesOf2));
        return sumTimeOf2 * sumTimesOf3;
    }
}
