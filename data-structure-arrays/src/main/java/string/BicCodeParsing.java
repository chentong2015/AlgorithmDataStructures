package string;

import java.util.ArrayList;
import java.util.List;

public class BicCodeParsing {

    // 字符的解析操作由业务逻辑所确定
    public static void main(String[] args) {
        String furtherInformation1 = "[IDENTIFICATION] SWIFT/BIC: HENBUS41. ";
        String furtherInformation2 = "[IDENTIFICATION] FDIC Certificate No: 5568. SWIFT/BIC: HENBUS41. [REPORTS]";

        BicCodeParsing instance = new BicCodeParsing();
        List<String> bicCodeList = instance.extractBicCodes(furtherInformation1);
        for (String code: bicCodeList) {
            System.out.println(code);
        }
    }

    // TODO. 逐个遍历整个字符数组，更加特殊字符来解析有效数据
    // 算法复杂度分析
    // .contains() O(N) N是整个Info字符串长度
    // .indexOf()  O(N)
    // while()     O(M) M是有效值范围的长度
    // .split()    O(M)
    // for ()      O(M)
    public List<String> extractBicCodes(String furtherInfo) {
        String identificationKey = "[IDENTIFICATION]";
        if (furtherInfo == null || !furtherInfo.contains(identificationKey)) {
            return new ArrayList<>();
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
