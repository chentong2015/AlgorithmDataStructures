package amazon.question;

// Number of Ways to Select Buildings
// You are given a 0-indexed binary string s which represents
// the types of buildings along a street where:
//   s[i] = '0' denotes that the ith building is an office and
//   s[i] = '1' denotes that the ith building is a restaurant.
//
// Select 3 buildings for random inspection.
// No two consecutive buildings out of the selected buildings can be of the same type.
// Return the number of valid ways to select 3 buildings.
//
// Input: s = "001101" -> 6
// - [0,2,4] from "001101" forms "010"
// - [0,3,4] from "001101" forms "010"
// - [1,2,4] from "001101" forms "010"
// - [1,3,4] from "001101" forms "010"
// - [2,4,5] from "001101" forms "101"
// - [3,4,5] from "001101" forms "101"
//
// 3 <= s.length <= 105
// s[i] is either '0' or '1'
public class WaysSelectionBuilding {

    public static void main(String[] args) {
        System.out.println(numberOfWays("001101"));
        // System.out.println(numberOfWays("11100"));
        // System.out.println(numberOfWays("101"));
        // System.out.println(numberOfWays("0001100100"));
    }

    // TODO. 递归思想: 利用前面的累加结果来推导最后的值
    // - 如果当前位置为"0": 只关心前面有多少"01" -> "0"可以来自前面任何位置
    // - 如果当前位置为"1": 只关心前面有多少"10" -> 前面的"1"全部可以缩合
    public static long numberOfWays(String s) {
        int zero = 0; // Individual zeroes count
        long zeroOne = 0; // Number of combinations of 01s
        int one = 0; // Individual ones count
        long oneZero = 0; // Number of combinations of 10s

        long numWays = 0;
        for(char ch : s.toCharArray()) {
            if(ch == '0') {
                zero++;
                oneZero += one; // Before 1 + Current 0 = Count 10
                numWays += zeroOne; // Before 01 + Current 0 = Count 010
            } else {
                one++;
                zeroOne += zero; // Before 0 + Current 1 = Count 01
                numWays += oneZero; // Before 10 + Current 1 = Count 101
            }
        }
        return numWays;
    }
}
