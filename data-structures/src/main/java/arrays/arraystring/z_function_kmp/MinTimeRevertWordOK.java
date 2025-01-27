package arrays.arraystring.z_function_kmp;

public class MinTimeRevertWordOK {

    public int minimumTimeToInitialState(String word, int k) {
        int n = word.length();

        int[] kmp = helper(word.toCharArray());
        int len = kmp[n - 1];
        while(len > 0 && (n - len) % k != 0) {
            len = kmp[len - 1];
        }

        if(len == 0) {
            return (n + k - 1) / k;
        }
        return (n - len) / k;
    }

    private int[] helper(char[] ch) {
        int n = ch.length;
        int[] res = new int[n];
        int index = 0;

        for(int i = 1; i < n;) {
            if(ch[i] == ch[index]) {
                res[i] = index + 1;
                i++;
                index++;
            } else {
                if(index != 0) {
                    index = res[index - 1];
                } else {
                    i++;
                }
            }
        }
        return res;
    }
}

