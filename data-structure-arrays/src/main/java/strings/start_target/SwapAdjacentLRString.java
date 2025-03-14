package strings.start_target;

// Swap Adjacent in LR String
// In a string composed of 'L', 'R', and 'X' characters, like "RXXLRXRXL",
// A move consists of either replacing one occurrence of "XL" with "LX",
// or replacing one occurrence of "RX" with "XR".
//
// Given the starting string start and the ending string result,
// return True if and only if there exists a sequence of moves to transform start to result.
//
// 1 <= start.length <= 10^4
// start.length == result.length
// Both start and result will only consist of characters in 'L', 'R', and 'X'.
public class SwapAdjacentLRString {

    // TODO. 双指针同步循环两个字符串，判断有效字符能否被转变得到
    // start = "RX XL RX R XL", result = "XR LX XR R LX" -> true
    // RXXLRXRXL ->
    // XRXLRXRXL ->
    // XRLXRXRXL ->
    // XRLXXRRXL ->
    // XRLXXRRLX
    //
    // start = "X", result = "L" -> false
    //
    // O(N + N + N) = O(N) 最佳时间复杂度
    // O(1)
    public boolean canTransform(String start, String result) {
        if (!isSameNumX(start, result)) {
            return false;
        }

        int n = result.length();
        int i = 0; // for start string
        int j = 0; // for result string
        while (i < n && j < n) {
            // 排除前面X字符的影响，移动到有效的字符位置
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && result.charAt(j) == 'X') {
                j++;
            }

            // TODO. 判断符合条件: 必须是相同字符且能通过左移或右移得到
            if (i < n && j < n) {
                if (start.charAt(i) != result.charAt(j)) {
                    return false;
                }
                if (start.charAt(i) == 'L' && i < j) {
                    return false;
                }
                if (start.charAt(i) == 'R' && i > j) {
                    return false;
                }
                i++;
                j++;
            }
        }
        return true;
    }

    // TODO. 推荐判断三种字符的数目都一致
    private boolean isSameNumX(String start, String result) {
        int countX1 = 0;
        int countX2 = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'X') {
                countX1++;
            }
            if (result.charAt(i) == 'X') {
                countX2++;
            }
        }
        return countX1 == countX2;
    }
}
