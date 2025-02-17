package leetcode2;

// Minimum Additions to Make Valid String
// Given a string word to which you can insert letters "a", "b" or "c" anywhere and any number of times
// return the minimum number of letters that must be inserted so that word becomes valid.
// A string is called valid if it can be formed by concatenating the string "abc" several times.
//
// 1 <= word.length <= 50
// word consists of letters "a", "b" and "c" only.
public class MinAdditionsMakeValidString {

    // TODO. 等效于给数据配平, 使用While循环判断index移动的条件
    // b -> abc -> 2
    // aaa -> abcabcabc -> 6
    // abc -> 0
    // bca -> abcabc -> 3
    // aaaaac -> 对于ac的情况只需要添加一个b
    //
    // O(n) n is the length of array
    // O(1)
    public int addMinimum(String word) {
        int result = 0;
        char[] charArray = word.toCharArray();
        int length = charArray.length;

        int index = 0;
        while (index < length) {
            if (charArray[index] == 'a') {
                if (index < length - 1 && charArray[index + 1] == 'b') {
                    if (index < length - 2 && charArray[index + 2] == 'c') {
                        index += 3;
                    } else {
                        result++;
                        index += 2;
                    }
                } else if (index < length - 1 && charArray[index + 1] == 'c') {
                    result++;
                    index += 2;
                } else {
                    result += 2;
                    index++;
                }
            } else if (charArray[index] == 'b') {
                if (index < length - 1 && charArray[index + 1] == 'c') {
                    result++;
                    index += 2;
                } else {
                    result += 2;
                    index++;
                }
            } else {
                result += 2;
                index++;
            }
        }
        return result;
    }
}
