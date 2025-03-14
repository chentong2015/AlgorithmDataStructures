package asubstrings.count_substring;

import java.util.HashMap;

public class CountBeautifulSubstringsII {

    public static void main(String[] args) {
        CountBeautifulSubstringsII instance = new CountBeautifulSubstringsII();
        System.out.println(instance.beautifulSubstrings("baeyh", 2));
    }

    // TODO. 二维HashMap[l]数组来统计(V, C)字符出现频率
    // a b a b a b a b a b, k=2
    // 2*2 % 2 = 0
    // 4*4 % 2 = 0
    // 6*6 % 2 = 0
    // ...
    //
    // O(n + k)
    // O(n + k)
    public long beautifulSubstrings(String s, int k) {
        int length = 1;
        while (length * length % (k * 4) > 0) {
            length++;
        }

        HashMap<Integer, Integer>[] seen = new HashMap[length];
        for (int i = 0; i < length; i++) {
            seen[i] = new HashMap<>();
        }
        seen[length - 1].put(0, 1);

        long res = 0;
        int countV = 0;
        for (int i = 0; i < s.length(); i++) {
            countV += isVowels(s.charAt(i)) ? 1: -1;

            int c = seen[i % length].getOrDefault(countV, 0);
            res += c;
            seen[i % length].put(countV, c + 1);
        }
        return res;
    }

    private boolean isVowels(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
