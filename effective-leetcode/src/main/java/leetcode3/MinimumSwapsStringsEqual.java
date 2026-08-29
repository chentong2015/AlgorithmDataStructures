package leetcode3;

// Minimum Swaps to Make Strings Equal
// You are given two strings s1 and s2 of equal length
// consisting of letters "x" and "y" only.
//
// Your task is to make these two strings equal to each other.
// You can swap any two characters that belong to different strings,
// which means: swap s1[i] and s2[j].
//
// Return the minimum number of swaps required to make s1 and s2 equal,
// or return -1 if it is impossible to do so.
//
// 1 <= s1.length, s2.length <= 1000
// s1.length == s2.length
// s1, s2 only contain 'x' or 'y'.
public class MinimumSwapsStringsEqual {

    // TODO. 注意相消的两个i和j位置是任意的，因此可以两两组合
    // ("xx", "yy") => 1 swap  优先使用这个交换策略
    // ("xy", "yx") => 2 swaps 再应用该策略
    //
    // s1 = "xx", s2 = "yy" -> 1
    // s1 = "yx", s2 = "yx".
    //
    // s1 = "xy", s2 = "yx" -> 2
    // s1 = "yy", s2 = "xx".
    // s1 = "xy", s2 = "xy".
    //
    // s1 = "xx", s2 = "xy" -> -1
    //
    // 只考虑两个字符组合的相消情况
    // "xxyyxyxyxx"
    // "xyyxyxxxyx"
    //   x yxy yx  优先消除xx+yy的组合
    //   y xyx xy  再考虑xy+yx的组合
    //   +  +
    public int minimumSwap(String s1, String s2) {
        // 纵向判断index位置的xy或yx特征
        int xyMismatch = 0;
        int yxMismatch = 0;
        for (int i = s1.length() - 1; i >= 0; i--) {
            if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') {
                xyMismatch++;
            } else if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') {
                yxMismatch++;
            }
        }

        // TODO. 奇对组合的情况下无法消除最后的结果
        if ((xyMismatch + yxMismatch) % 2 != 0) {
            return -1;
        }

        // apply the first strategy (xx+yy)或者(yy+xx)成对相消
        int ans = xyMismatch / 2 + yxMismatch / 2;

        // we apply the second strategy 剩一个多余需要(xy+yx)消除
        if (xyMismatch % 2 == 1) {
            ans += 2;
        }
        return ans;
    }
}
