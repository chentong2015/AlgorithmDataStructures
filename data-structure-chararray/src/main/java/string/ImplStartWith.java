package string;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// 实现场景: 只保留字符串开头字符，以前8个字符作为唯一标识
// Swift: JAMB LY LT 001
// JAMB = bank name code
// LY = country code
// LT = city code
// 001 = code for agency
public class ImplStartWith {

    private static final int NORMAL_LENGTH = 8;
    private static final int LIMIT_LENGTH = 11;

    // TODO. 直接一次遍历获取离散的有效值
    // JAMBLY, JAMBLYLT001, JAMBLYLT002
    // -> JAMBLY, JAMBLYLT
    //
    // O(N)
    // O(N)
    public List<String> cleanSwiftCodes(List<String> originalSwiftCodes) {
        Set<String> normalCodeSet = new HashSet<>();
        List<String> result = new ArrayList<>();

        boolean isCleanUp = false;
        for (String code : originalSwiftCodes) {
            if (code.length() == LIMIT_LENGTH) {
                isCleanUp = true;
                code = code.substring(0, NORMAL_LENGTH);
            }
            if (!normalCodeSet.contains(code)) {
                normalCodeSet.add(code);
                result.add(code);
            }
        }
        // 判断存在截取或重复Code的删除
        if (isCleanUp || result.size() < originalSwiftCodes.size()) {
            System.out.println("There is cleaned up about swift codes");
        }
        return result;
    }
}
