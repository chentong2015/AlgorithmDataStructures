package amazonTop50;

// Substring With Largest Variance
// The variance of a string is defined as the largest difference
// between the number of occurrences of any 2 characters present in the string.
// Note the two characters may or may not be the same. 相同字符没有差值
//
// Given a string s consisting of lowercase English letters only,
// return the largest variance possible among all substrings of s.
//
// 1 <= s.length <= 10^4
// s consists of lowercase English letters.
public class SubstringLargestVariance {

    // TODO. 循环26种字符Pair组合, 降低计算的维度: 多字符很难统计比较
    // "aababbb"            -> 3
    // "lripaa"             -> 1
    // "icexiahccknibwuwgi" -> 3  最大差值来源整个字符串
    // "abbbaaaaaa"         -> 5  全取字符算法不一定保证最优解
    //
    // O(26*26*n) 有限次数的循环
    // O(26)
    public int largestVariance(String s) {
        int[] counter = new int[26];
        for (char ch : s.toCharArray()) {
            counter[ch - 'a']++;
        }

        int globalMax = 0;
        for (char iCh = 'a'; iCh <= 'z'; iCh++) {
            for (int jCh = 'a'; jCh <= 'z'; jCh++) {
                if (iCh == jCh || counter[iCh - 'a'] == 0 || counter[jCh - 'a'] == 0) {
                    continue; // 相同index坐标不考虑, 只有一种单词的不考虑
                }

                int majorCount = 0;
                int minorCount = 0;
                int restMinor = counter[jCh - 'a'];
                for (char ch : s.toCharArray()) {
                    if(ch == iCh) {
                        majorCount++; // 以major统计为主来计算，必须有利于major
                    }
                    if(ch == jCh) {
                        minorCount++;
                        restMinor--;  // 统计剩余minor字符，用于重置判断
                    }

                    // 只要有minor字符，则动态计算更新，而非遍历完再计算
                    if (minorCount > 0) {
                        globalMax = Math.max(globalMax, majorCount - minorCount);
                    }

                    // TODO. bbaaaab: minor字符太多，应该被丢弃(不再有贡献)，且后面还有
                    if (majorCount < minorCount && restMinor > 0) {
                        majorCount = 0;
                        minorCount = 0;
                    }
                }
            }
        }
        return globalMax;
    }
}
