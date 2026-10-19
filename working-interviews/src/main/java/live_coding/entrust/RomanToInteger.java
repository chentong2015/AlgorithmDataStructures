package live_coding.entrust;

import java.util.HashMap;

public class RomanToInteger {

    // TODO. 先判断两个字符的映射(唯一性), 再判断一个字符的映射值
    //
    // Input Roman String, Return the corresponding integer value.
    // Input: MCMCDXLI
    // Output: 1000 + 900 + 400 + 40 + 1 = 2341
    public int convertRomanToInt(String str) {
        HashMap<String, Integer> mapping = getMapping();
        int sumValue = 0;
        for (int index = 0; index < str.length(); index++) {
            String key;
            if (index < str.length() - 1) {
                key = str.substring(index, index + 2);
                if (mapping.containsKey(key)) {
                    sumValue += mapping.get(key);
                    index++;
                    continue;
                }
            }
            key = str.substring(index, index + 1);
            if (mapping.containsKey(key)) {
                sumValue += mapping.get(key);
            }
        }
        return sumValue;
    }

    private HashMap<String, Integer> getMapping() {
        HashMap<String, Integer> mapping = new HashMap<>();
        mapping.put("M", 1000);
        mapping.put("CM", 900); //
        mapping.put("D", 500);
        mapping.put("CD", 400); //
        mapping.put("C", 100);
        mapping.put("XC", 90);  //
        mapping.put("L", 50);
        mapping.put("XL", 40);  //
        mapping.put("X", 10);
        mapping.put("IX", 9);   //
        mapping.put("V", 5);
        mapping.put("IV", 4);   //
        mapping.put("I", 1);
        return mapping;
    }
}
