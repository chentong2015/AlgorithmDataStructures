package entrust;

import java.util.HashMap;

public class RomanToInteger {

    // TODO. 直接遍历, 并优先判断两个字符的值
    // 罗马数组的特点: 大的字符一定在坐标，小的字符在右侧
    //
    // Input: MCMCDXLI
    // Output: 1000 + 900 + 400 + 40 + 1 = 2341
    public int convertRomanToInt(String str) {
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

        int result = 0;
        for (int index = 0; index < str.length(); index++) {
            String key;
            if (index < str.length() - 1) {
                key = str.substring(index, index + 2);
                if (mapping.containsKey(key)) {
                    result += mapping.get(key);
                    index++; // 这里必须要移动两个字符
                    continue;
                }
            }

            key = str.substring(index, index + 1);
            if (mapping.containsKey(key)) {
                result += mapping.get(key);
            }
        }
        return result;
    }

    // TODO. 基于罗马数字的生成逻辑进行推理(构成)
    public int romanToInt(String s) {
        int total = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I') {
                if (i + 1 < chars.length && (chars[i + 1] == 'V' || chars[i + 1] == 'X')) {
                    total -= 1;
                } else {
                    total += 1;
                }
            } else if (chars[i] == 'V') {
                total += 5;
            } else if (chars[i] == 'X') {
                if (i + 1 < chars.length && (chars[i + 1] == 'L' || chars[i + 1] == 'C')) {
                    total -= 10;
                } else {
                    total += 10;
                }
            } else if (chars[i] == 'L') {
                total += 50;
            } else if (chars[i] == 'C') {
                if (i + 1 < chars.length && (chars[i + 1] == 'D' || chars[i + 1] == 'M')) {
                    total -= 100;
                } else {
                    total += 100;
                }
            } else if (chars[i] == 'D') {
                total += 500;
            } else if (chars[i] == 'M') {
                total += 1000;
            }
        }
        return total;
    }
}
