package amazon.question;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Total Appeal of A String
// The appeal of a string is the number of distinct characters found in the string.
// The appeal of "abbca" is 3 because it has 3 distinct characters: 'a', 'b', and 'c'.
//
// Given a string s, return the total appeal of all of its substrings.
// A substring is a contiguous sequence of characters within a string.
//
// 1 <= s.length <= 105
// s consists of lowercase English letters.
public class TotalAppealString {

    // TODO. 思考进一步的优化空间: 不要保留中途无用的计算结果
    // 1. 使用一个变量即可以存储index-1之前的统计结果
    // 2. 存储的mapping lastIndex可以使用26常量数组
    //
    // O(n)   极限复杂度，只使用一次遍历计算出最后的结果
    // O(n+n) 存储之前遍历过程中的关键信息(相同字符的位置 + 之前的统计Sum)
    public static long appealSum(String s) {
        long sum = 0;
        long sumCurrent = 0;

        long[] sumsBefore = new long[s.length()];
        Map<Character, Integer> mapIndex = new HashMap<>();

        for (int index=0; index < s.length(); index++) {
            int lastIndex = mapIndex.getOrDefault(s.charAt(index), -1);
            if (lastIndex == -1) {
                if (index == 0) {
                    sum += 1;
                    sumsBefore[index] = 1;
                } else {
                    sumCurrent = sumsBefore[index - 1] + index + 1;
                    sum += sumCurrent;
                    sumsBefore[index] = sumCurrent;
                }
            } else {
                sumCurrent = sumsBefore[index - 1] + index - lastIndex;
                sum += sumCurrent;
                sumsBefore[index] = sumCurrent;
            }
            mapIndex.put(s.charAt(index), index);
        }
        return sum;
    }

    // TODO. Dynamic Programming: 缩小动态编程的存储数据，约束在26个字符
    //  最后的结果与Index位置坐标强相关，直接根据Index坐标进行计算
    // a  b  b   c   a
    // 1
    // 2  1
    // 2  1  1
    // 3  2  2   1
    // 3  3  3   2   1
    // O(n)
    // O(1) 不造成时间复杂度
    public static long appealSumPlus(String s) {
        long totalAppeal = 0;
        long currentAppeal = 0;
        long[] lastPosition = new long[26];
        Arrays.fill(lastPosition, -1);

        for (int i = 0; i < s.length(); ++i){
            int charIndex = s.charAt(i) - 'a';
            currentAppeal += i - lastPosition[charIndex]; // 从新增的index中减去lastIndex坐标

            lastPosition[charIndex] = i; // 存储字符的上一个所在位置
            totalAppeal += currentAppeal ; // currentAppeal表示一层统计结果，totalAppeal累计所有层的结果
        }
        return totalAppeal;
    }
}
