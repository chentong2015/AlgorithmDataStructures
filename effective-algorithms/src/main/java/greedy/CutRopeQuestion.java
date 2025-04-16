package greedy;

// Cut Rope
// Given a rope with positive integer-length n,
// how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1],
// in order to get the maximal product of p[0]*p[1]* ... *p[m-1]
//
// m is determined by you and must be greater than 0 (at least one cut must be made).
// Return the max product you can have.
//
public class CutRopeQuestion {

    // TODO. 优先考虑拆成3和2才能使结果值最大
    // 将绳子拆成 1 和 n-1，则 1(n-1)-n = -1<0，即拆开后的乘积一定更小，所以不能出现长度为 1 的绳子
    // 将绳子拆成 2 和 n-2，则 2(n-2)-n = n-4，在 n>=4 时这样拆开能得到的乘积会比不拆更大
    // 将绳子拆成 3 和 n-3，则 3(n-3)-n = 2n-9，在 n>=5 时效果更好
    // 将绳子拆成 4 和 n-4，因为 4=2*2，因此效果和拆成2一样
    // 将绳子拆成 5 和 n-5，因为 5=2+3，而5<2*3，所以不能出现5的绳子，而是尽可能拆成2和3
    // 将绳子拆成 6 和 n-6，因为 6=3+3，而 6<3*3，所以不能出现6的绳子，而是拆成3和3
    //
    // n = 10, the max product is 3 * 3 * 2 * 2 = 36
    // n = 12, the max product is 3 * 3 * 3 * 3 = 81
    public int cutRope(int n) {
        if (n < 2) return 0;
        if (n == 2) return 1;
        if (n == 3) return 2;

        // TODO. 当拆成3后剩余1，则将(3,1)转成(2,2)更优
        int timesOf3 = n / 3;
        if (n - timesOf3 * 3 == 1) {
            timesOf3--;
        }
        int sumTimesOf3 = (int) (Math.pow(3, timesOf3));

        int timesOf2 = (n - timesOf3 * 3) / 2;
        int sumTimeOf2 = (int) (Math.pow(2, timesOf2));

        return sumTimeOf2 * sumTimesOf3;
    }
}
