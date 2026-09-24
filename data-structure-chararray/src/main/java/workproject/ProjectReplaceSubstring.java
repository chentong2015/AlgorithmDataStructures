package workproject;

// String.replace(subStr, targetStr) 源码实现
// - 将字符串中匹配的subStr全部替换成targetStr
// - 当出现重回的subStr位置时, 从head开始替换
public class ProjectReplaceSubstring {

    //
    // aaaaa aaa bb -> bbaa
    // abdcsq bd mm -> ammcsq
    //
    // O(N*M)
    public static String replaceSubStr(String sourceStr, String subStr, String targetStr) {
        // 首先需要判断是否满足替换的条件
        int left = 0;
        StringBuilder result = new StringBuilder();
        StringBuilder windowStr = new StringBuilder();
        for (int index = 0; index < sourceStr.length(); index++) {
            windowStr.append(sourceStr.charAt(index));
            if (windowStr.length() < subStr.length())
                continue;

            if (windowStr.toString().equals(subStr)) {
                result.append(targetStr);  // 将替换的字符存储到结果字符串中
                left = index + 1;          // 移动left到下一个字符位置
                windowStr = new StringBuilder();
            } else {
                windowStr.deleteCharAt(0); // 滑动窗口, 删除最左侧的字符
                result.append(sourceStr.charAt(left)); // 存储左侧字符到结果字符串中
                left++;
            }
        }
        return result.toString();
    }
}
