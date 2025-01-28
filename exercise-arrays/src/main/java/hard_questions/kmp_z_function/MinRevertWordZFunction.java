package hard_questions.kmp_z_function;

// Minimum Time to Revert Word to Initial State
//
// You are given a 0-indexed string word and an integer k.
// At every second, you must perform the following operations:
// - Remove the first k characters of word.
// - Add any k characters to the end of word.
//
// Not necessarily need to add the same characters that you removed.
// However, you must perform both operations at every second.
// Return the minimum time greater than zero required for word to revert to its initial state.
//
// 1 <= word.length <= 10^6
// 1 <= k <= word.length
// word consists only of lowercase English letters
public class MinRevertWordZFunction {

    // TODO. 本质上只需要判断字符的第几个K端的字符能够完全匹配开头
    // 补全时可以补全任意顺序的任意字符，从而最大限度的还原初始字符
    // 移除开头全部字符后，随机添加字符必然能还原，最大还原次数为length/k次
    //
    // aba cab a, k=3
    //     cab aba c
    //         aba cab a
    //
    // abac aba, k=4
    //      aba caba
    public int minTimeToInitialState(String word, int k) {
        int[] zArray = zFunction(word);
        int count = 1;
        while (k * count < word.length()) {
            // 判断index统计的字符长度是否大于剩余字符长度
            if (zArray[k * count] >= word.length() - k * count) {
                break;
            }
            count++;
        }
        return count;
    }

    // 统计从当前位置开始后续有多少字符长度是前缀
    // a b a c a b a
    // 7 0 1 0 3 0 1
    private int[] zFunction(String s) {
        int[] zArray = new int[s.length()];
        int left = 0;
        int right = 0;
        for(int index = 1; index < s.length(); index++) {
            zArray[index] = 0;
            if (index < right) {
                zArray[index] = Math.min(right - index, zArray[index - left]);
            }
            while (index + zArray[index] < s.length()
                   && s.charAt(zArray[index]) == s.charAt(index + zArray[index])) {
                zArray[index]++;
            }
            if (index + zArray[index] > right) {
                left = index;
                right = index + zArray[index];
            }
        }

        zArray[0] = s.length();
        return zArray;
    }
}
