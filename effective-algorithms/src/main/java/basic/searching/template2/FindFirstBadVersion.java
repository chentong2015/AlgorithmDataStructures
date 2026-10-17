package basic.searching.template2;

// First Bad Version
// n versions [1, 2, ..., n] and find out the first bad one,
// which causes all the following ones to be bad
public class FindFirstBadVersion {

    //
    // 找到一组产品中第一次次品: 该index位置是次品，index-1前面是正品
    //
    // 1 2 3 4 0 0 0 0
    // 1 0 0 0 0 0 0 0
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        while (low < high) {
            int middle = low + (high - low) / 2;
            if (isBadVersion(middle)) {
                if (!isBadVersion(middle - 1)) {
                    return middle;
                }
                high = middle;    // 只要能判断左右的移动方向即可
            } else {
                low = middle + 1; // 需要+1，原因是只要能进入else则说明middle位置一定为正品 !!
            }
        }
        // 出循环条件: low=high, (low-1)位置正品 < low第一个次品
        return low;
    }

    private boolean isBadVersion(int n) {
        return n >= 4;
    }
}
