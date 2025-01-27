package arrays.arraystring.z_function_kmp;

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
public class MinTimeRevertWord {

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
    public static int minimumTimeToInitialState(String word, int k) {
        int count = 1;
        int n = word.length();

        int[] z = zFunction(word);
        while (k * count < n) {
            if (z[k * count] >= n - k * count) {
                break;
            }
            count++;
        }
        return count;
    }

    private static int[] zFunction(String s) {
        int n = s.length();
        int z[] = new int[n];
        int R = 0;
        int L = 0;
        for(int i = 1; i < n; i++) {
            z[i] = 0;
            if (R > i) {
                z[i] = Math.min(R - i, z[i - L]);
            }
            while (i + z[i] < n && s.charAt(i+z[i]) == s.charAt(z[i])) {
                z[i]++;
            }
            if (i + z[i] > R) {
                L = i;
                R = i + z[i];
            }
        }
        z[0] = n;
        return z;
    }
}
