package hard_questions.kmp_z_function;

public class MinRevertWordKmp {

    // TODO. 如果从KMP统计表中得出结论
    // a b a  c a b  a, k=3
    // 0 0 1  0 1 2  3
    public int minTimeToInitialState(String word, int k) {
        int n = word.length();
        int[] kmp = kmpHelper(word.toCharArray());

        // 从KMP统计数组中判断完整的K截取点 (n - subLength) % k == 0
        int subLength = kmp[n - 1];
        while(subLength > 0 && (n - subLength) % k != 0) {
            subLength = kmp[subLength - 1];
        }

        if (subLength > 0) {
            return (n - subLength) / k;
        }
        return (n + k - 1) / k;
    }

    // 统计从当前index位置起“从头开始”匹配的字符串长度
    // a b a c a b a k
    // 0 0 1 0 1 2 3 0
    // L     I->
    private int[] kmpHelper(char[] ch) {
        int[] indexArray = new int[ch.length];

        int left = 0;
        for(int index = 1; index < ch.length;) {
            if(ch[left] == ch[index]) {
                indexArray[index] = left + 1;
                index++;
                left++;
                continue;
            }

            if(left != 0) {
                // 退回到上一个字符所从头开始匹配的位置
                left = indexArray[left - 1];
            } else {
                index++;
            }
        }
        return indexArray;
    }
}

