package basic.searching.template3;

// Sqrt(x)  0<=x<=2^31-1
// Given a non-negative integer x, compute and return the square root of x
public class ComputeSquareRoot {

    // 二分法求平方根，只保留正数的部分
    // 1. x=8 -> 2.82 -> 2     必须去掉正数后面的小数
    // 2. 2147395599  -> 46339 对于大数, 需要考虑乘积是否会出现值的溢出, 直接比较会出现值的截取, 判断不准 !!
    public static int mySqrt(int x) {
        if (x <= 1) return x;
        int low = 1;
        int high = x;
        while (low + 1 < high) {                  // TODO 边界条件: 平方根很可能出现在两个整数的中间，必须留一个间隔位置 !!
            int middle = low + (high - low) / 2;
            if (middle == x / middle) {           // TODO 先算除法，再来比较，避免乘法造成的值溢出 !!
                return middle;
            } else if (middle > x / middle) {
                high = middle;                    // 这里不需要浮动+1和-1，浮动之后很有可能错过平方根位置低位的值
            } else {
                low = middle;
            }
        }
        // 出循环条件: low+1=high, 说明平方根在low和high之间
        return low;
    }
}
