package iteration;

// Count and Say
// The count-and-say sequence is a sequence of digit strings defined by the recursive formula:
//  countAndSay(1) = "1"
//  countAndSay(n) is the run-length encoding of countAndSay(n - 1).
//
// TODO. Run-length encoding (RLE)加密算法
// Run-length encoding (RLE) is a string compression method
// that works by replacing consecutive identical characters (repeated 2 or more times)
// with the concatenation of the character
// and the number marking the count of the characters (length of the run).
// For string "3322251" -> "23321511"
//   replace "33" with "23",
//   replace "222" with "32",
//   replace "5" with "15",
//   replace "1" with "11"
//
// Given a positive integer n, return the nth element of the count-and-say sequence.
// 1 <= n <= 30
//
// Follow up: Could you solve it iteratively?
public class CountAndSay {

    // TODO. 本质上使用迭代计算更优
    // n = 5
    // countAndSay(1) = "1"
    // countAndSay(2) = RLE of "1" = "11"
    // countAndSay(3) = RLE of "11" = "21"
    // countAndSay(4) = RLE of "21" = "1211"
    // countAndSay(5) = RLE of "1211" = "11121111"
    //
    public String countAndSay(int n) {
        int index = 1;

        if (n == 1) {
            return "1";
        } else {
            // helper(n - 1);
        }
        return null;
    }
}
