package leetcode;

// Removing Stars From a String
// You are given a string s, which contains stars *.
// In one operation, you can:
// - Choose a star in s.
// - Remove the closest non-star character to its left, as well as remove the star itself.
// Return the string after all stars have been removed.
//
// 1 <= s.length <= 10^¨5
// s consists of lowercase English letters and stars *.
// The operation above can be performed on s.
//
// Note: 每个星号保证一定能够消除
// The input will be generated such that the operation is always possible.
// It can be shown that the resulting string will always be unique.
public class RemovingStarsFromString {

    // TODO. 直接在遍历字符时构建最后的结果
    // leet**cod*e
    // lee*cod*e
    // lecod*e
    // lecoe
    //
    // erase*****
    // ""
    public String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        for(char c: s.toCharArray()){
            if(c=='*'){
                if(!sb.isEmpty()){
                    sb.setLength(sb.length() - 1);
                }
            } else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
