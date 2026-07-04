package string;

public class ImplReplaceStr {

    // 实现String.replace(subStr, targetStr)源码效果
    // 使用StringBuilder + Sliding Windows来实现全部替换子字符串成自定的字符串
    // aaaaa aaa bb -> bbaa 符合条件的子字符串只会替换首次出现的那个
    public static String replaceSubStr(String sourceStr, String subStr, String targetStr) {
        // 首先需要判断是否满足替换的条件
        int left = 0;
        StringBuilder result = new StringBuilder();
        StringBuilder windowStr = new StringBuilder();
        for (int index = 0; index < sourceStr.length(); index++) {
            // 新的字符添加到滑动窗口的末尾
            windowStr.append(sourceStr.charAt(index));
            if (windowStr.length() < subStr.length())
                continue;
            if (windowStr.toString().equals(subStr)) {
                result.append(targetStr);
                // 移动下标，开启新的一个窗口
                left = index + 1;
                windowStr = new StringBuilder();
            } else {
                // 滑动窗口在移动的过程中，如果判断不等，则将第一个字符删除
                // 第一个字符也就left index所在的位置
                windowStr.deleteCharAt(0);
                result.append(sourceStr.charAt(left));
                left++;
            }
        }
        return result.toString();
    }
}
