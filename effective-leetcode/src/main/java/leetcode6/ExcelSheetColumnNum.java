package leetcode6;

public class ExcelSheetColumnNum {

    // Excel Sheet Column Number
    // Given a string columnTitle that represents the column title as appears in an Excel sheet,
    // return its corresponding column number.
    //
    // 1 <= columnTitle.length <= 7
    // columnTitle consists only of uppercase English letters.
    // columnTitle is in the range ["A", "FXSHRXW"].
    //
    // A -> 1
    // B -> 2
    // C -> 3
    // ...
    // Z -> 26
    // AA -> 27
    // AB -> 28
    // ...
    public int titleToNumber(String columnTitle) {
        int result = 0;
        int factor = 1;
        int n = columnTitle.length();
        for (int index = n-1; index >= 0; index--) {
            int c = columnTitle.charAt(index);
            result += factor * (c - 'A' + 1);
            factor *= 26;
        }
        return result;
    }

    // Excel Sheet Column Title
    // Given an integer columnNumber,
    // return its corresponding column title as it appears in an Excel sheet.
    // 1 <= columnNumber <= 2^31 - 1
    //
    // 28 -> AB
    // 52 -> AZ
    //
    public String convertToTitle(int columnNumber) {
        return recursion(columnNumber);
    }

    // TODO. 注意完全整除的特殊情况
    private String recursion(int number) {
        if (number <= 26) {
            char c = (char) ('A' + number - 1);
            return String.valueOf(c);
        } else {
            if (number % 26 == 0) {
                return recursion(number / 26 - 1) + "Z";
            }
            return recursion(number / 26) + recursion(number % 26);
        }
    }
}