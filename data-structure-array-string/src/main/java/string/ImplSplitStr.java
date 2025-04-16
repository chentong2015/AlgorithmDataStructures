package string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ImplSplitStr {

    // 字符的解析操作由业务逻辑所确定
    public static void main(String[] args) {
        Set<String> codesSet = Set.of("SWIFT/BIC", "SWIFT BIC");
        String furtherInformation1 = "[IDENTIFICATION] SWIFT/BIC: HENBUS41. ";
        String furtherInformation2 = "[IDENTIFICATION] FDIC Certificate No: 5568. SWIFT/BIC: HENBUS41. [REPORTS]";

        // O(N) 遍历完所有的字符
        String[] furtherInfoArray = furtherInformation1.split("IDENTIFICATION]");
        if (furtherInfoArray.length == 1) {
            System.out.println("No extra data to process");
        }

        String identification = furtherInfoArray[1];
        int endingIndex = identification.indexOf("[");
        if (endingIndex != -1) {
            identification = identification.substring(0, endingIndex);
        }

        // O(M + M) 对区间内的字符进行处理
        String[] codes = identification.split("\\. ");
        for (String code: codes) {
            String[] items = code.trim().split(":");
            if (codesSet.contains(items[0])) {
                System.out.println(items[1]);
            }
        }
    }

    // TODO. 手写实现Split字符串切片遍历的效果
    // 算法复杂度分析
    // .contains() O(N) N是整个Info字符串长度
    // .indexOf()  O(N)
    // while()     O(M) M是有效值范围的长度
    // .split()    O(M)
    // for ()      O(M)
    public List<String> extractBicCodes(String furtherInfo) {
        String identificationKey = "[IDENTIFICATION]";
        if (furtherInfo == null || !furtherInfo.contains(identificationKey)) {
            return Collections.emptyList();
        }

        List<String> bicCodeList = new ArrayList<>();
        int index = furtherInfo.indexOf(identificationKey) + identificationKey.length() + 1;
        while (index < furtherInfo.length()) {
            char current = furtherInfo.charAt(index);
            if (current == '[') { // 以后续的[字符作为遍历片段的结束
                break;
            }
            StringBuilder stringBuilder = new StringBuilder();
            boolean isValidBicCode = false;
            while (current != '.') {   // 以.点字符作为划分字符
                stringBuilder.append(current);
                if (current == ':') {  // 以:分号字符作为Key主键字符串
                    String key = stringBuilder.toString().toUpperCase();
                    if (isValidCode(key)) {
                        isValidBicCode = true;
                        stringBuilder.setLength(0); // 清空已经读取的字符Key,以存储有效值
                    }
                }
                index++;
                if (index < furtherInfo.length()) {
                    current = furtherInfo.charAt(index);
                }
            }

            if (isValidBicCode) {
                String[] bicCodes = stringBuilder.toString().split(" ");
                for (String code: bicCodes) {
                    if (!code.isEmpty()) {
                        bicCodeList.add(code);
                    }
                }
            }
            index += 2;
        }
        return bicCodeList;
    }

    private boolean isValidCode(String code) {
        return code.equals("SWIFT/BIC:") || code.equals("CODE BIC:");
    }
}
