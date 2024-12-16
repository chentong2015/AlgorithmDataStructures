package amazon.question;

import java.util.ArrayList;
import java.util.List;

// Number of Ways to Select Buildings
// You are given a 0-indexed binary string s which represents the types of buildings along a street where:
//
// s[i] = '0' denotes that the ith building is an office and
// s[i] = '1' denotes that the ith building is a restaurant.
//
// select 3 buildings for random inspection. However, to ensure variety,
// no two consecutive buildings out of the selected buildings can be of the same type.
// Return the number of valid ways to select 3 buildings.
//
// Input: s = "001101" -> 6 结果只有两种可能 "010" "101"
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

    // 2 2 1 1
    // 2*2*1 + 2*1*1 = 6
    //
    // 0 0 0 1 1 1 1 1 0 0 0 0 0 1 1 1 0 0
    // 3 5 5 3 2
    // 3*5*5 + 3*3*2 + 5*5*3 使用频率计算结果
    //
    // 0001100100
    // 3 2 2 1 2 最后的排序组合可以跳跃/避开相邻的重合即可
    // 3*2*2 + 3*1*2 + 2*2*1 + 2*1*2 ?
    public static void main(String[] args) {
        System.out.println(numberOfWays("001101"));
        System.out.println(numberOfWays("11100"));
        System.out.println(numberOfWays("101"));
        System.out.println(numberOfWays("0001100100"));
    }

    // TODO. N^3的时间复杂度是不被允许的
    // O(n*n*n) 最差时间复杂度为二维的遍历
    // O(n)     最差空间复杂度全部统计为1
    public static long numberOfWays(String s) {
        List<Integer> countList = countZeroAndOne(s);
        if (countList.size() < 3) {
            return 0;
        }
        int numWays = 0;
        for (int i=0; i < countList.size() - 2; i++) {
            for (int j=i+1; j< countList.size() - 1; j=j+2) {
                for (int k=j+1; k< countList.size(); k=k+2) {
                    numWays += countList.get(i) * countList.get(j) * countList.get(k);
                }
            }
        }
        return numWays;
    }

    private static List<Integer> countZeroAndOne(String s) {
        List<Integer> countList = new ArrayList<>();

        char[] charArray = s.toCharArray();
        char charBefore = charArray[0];
        int count = 1;
        for (int index=1; index < charArray.length; index++) {
            if (charBefore == charArray[index]) {
                count++;
            } else {
                countList.add(count);
                count = 1;
                charBefore = charArray[index];
            }
        }
        countList.add(count); // add the count for the last char
        return countList;
    }
}
