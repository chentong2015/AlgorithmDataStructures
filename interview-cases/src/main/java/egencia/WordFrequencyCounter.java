package egencia;

import java.util.*;

public class WordFrequencyCounter {

    // TODO. 使用LinkedHashMap<>存储结构来统计，并且保证统计是有序的
    // 根据item统计的数据排序; 根据count排序
    // the dog got the bonne
    // the the dog dog home ... 排序之后的特征出来了，只需要统计相邻的数据
    // 1
    // 2
    // 2   2  1
    // O(n*log(n))  O(n) 利用了额外的存储空间
    public static List<Integer> count(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.EMPTY_LIST;
        }

        Arrays.sort(words);
        HashMap<String, Integer> countsMap = new LinkedHashMap<>();
        for (String word : words) {
            if (countsMap.containsKey(word)) {
                countsMap.put(word, countsMap.get(word) + 1);
            } else {
                countsMap.put(word, 1);
            }
        }
        return countsMap.values().stream().toList();
    }

    // TODO. 直接将统计的结果记录到最后返回的数组中，减少空间复杂度
    // 1. 如果直接创建最后的返回数组，则无法知道要统计的离散word的数量 => 需要额外遍历排序后的数组 O(n)
    // 2. 如果使用可变的数组ArrayList<>，则最后还需要将列表中的数据转换成数组 => 需要额外的空间复杂度 0(n)
    public static List<Integer> countWords(String[] words) {
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        Arrays.sort(words);

        int index = 0;
        String comparedWord = words[0];
        List<Integer> results = new ArrayList<>();
        results.add(1);
        for (int id = 1; id < words.length; id++) {
            if (comparedWord.equals(words[id])) {
                results.set(index, results.get(index) + 1);
            } else {
                index++;
                results.add(1);
                comparedWord = words[id];
            }
        }
        return results;
    }
}
