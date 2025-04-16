package criteo.t9_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class T9ModelMapping {

    public static void main(String[] args) {
        List<String> dict = List.of("sage", "rage", "raid", "hello", "morning", "blabla");
        List<String> result = getMappingStringsOfDigits( dict, "7243");
        for (String str: result) {
            System.out.println(str);
        }
    }

    // TODO. 时间复杂度分步计算 + 空间复杂度的优化
    //  HashMap中不能存储dict中str值，这会导致空间复杂度double增加
    //  HashMap中只需存储dict中的字符串的坐标，根据坐标直接取结果
    private static List<String> getMappingStringsOfDigits(List<String> dict, String digits) {
        HashMap<String, List<Integer>> map = new HashMap<>();
        for(int index = 0; index < dict.size(); index++) {
            StringBuilder stringBuilder = new StringBuilder();
            String value = dict.get(index);
            for(char c: value.toCharArray()) {
                stringBuilder.append(getDigitByChar(c));
            }

            String key = stringBuilder.toString();
            if(map.containsKey(key)) {
                List<Integer> list = map.get(key);
                list.add(index);
            } else {
                List<Integer> newList = new ArrayList<>();
                newList.add(index);
                map.put(key, newList);
            }
        }

        List<String> result = new ArrayList<>();
        if (map.containsKey(digits)) {
            for (int index: map.get(digits)) {
                result.add(dict.get(index));
            }
        }
        return result;
    }

    // 以下的Mapping方法不造成时间复杂度
    private static int getDigitByChar(char character) {
        return switch (character) {
            case 'a', 'b', 'c' -> 2;
            case 'd', 'e', 'f' -> 3;
            case 'g', 'h', 'i' -> 4;
            case 'j', 'k', 'l' -> 5;
            case 'm', 'n', 'o' -> 6;
            case 'p', 'q', 'r', 's' -> 7;
            case 't', 'u', 'v' -> 8;
            case 'w', 'x', 'y', 'z' -> 9;
            case ' ' -> 0;
            default -> -1;
        };
    }
}
