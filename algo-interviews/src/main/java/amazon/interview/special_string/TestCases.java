package amazon.interview.special_string;

public class TestCases {

    // x z z -> y z z
    // x y z x y x -> x y z x y y -> x y z x y z
    // x y z x z y -> x y z x z z -> x y z y a b
    //
    // a b b d -> a  b  c  a
    // a z y x -> a z y z
    // a z y -> a z z -> b a b
    // a z z -> b a b
    public static void main(String[] args) {
        System.out.println("a: " + SpecialStringSolution.getSpecialString("a"));
        System.out.println("y: " + SpecialStringSolution.getSpecialString("y"));
        System.out.println("z: " + SpecialStringSolution.getSpecialString("z"));

        System.out.println("xyy: " + SpecialStringSolution.getSpecialString("xyy"));
        System.out.println("xzz: " + SpecialStringSolution.getSpecialString("xzz"));

        System.out.println("xyzxyx: " + SpecialStringSolution.getSpecialString("xyzxyx"));
        System.out.println("xyzxzy: " + SpecialStringSolution.getSpecialString("xyzxzy"));

        System.out.println("abbd: " + SpecialStringSolution.getSpecialString("abbd"));
        System.out.println("azyx: " + SpecialStringSolution.getSpecialString("azyx"));
        System.out.println("azy: " + SpecialStringSolution.getSpecialString("azy"));
        System.out.println("azz: " + SpecialStringSolution.getSpecialString("azz"));

        System.out.println("abcd: " + SpecialStringSolution.getSpecialString("abcd"));
        System.out.println("aacd: " + SpecialStringSolution.getSpecialString("aacd"));
        System.out.println("abbscd: " + SpecialStringSolution.getSpecialString("abbscd"));
        System.out.println("aaaa: " + SpecialStringSolution.getSpecialString("aaaa"));
        System.out.println("zzabb: " + SpecialStringSolution.getSpecialString("zzabb"));
        System.out.println("abczz: " + SpecialStringSolution.getSpecialString("abczz"));
        System.out.println("abcc: " + SpecialStringSolution.getSpecialString("abcc"));
        System.out.println("abccss: " + SpecialStringSolution.getSpecialString("abccss"));
        System.out.println("zyx: " + SpecialStringSolution.getSpecialString("zyx"));
        System.out.println("abbd: " + SpecialStringSolution.getSpecialString("abbd"));
        System.out.println("abccdeaaa: " + SpecialStringSolution.getSpecialString("abccdeaaa"));
        System.out.println("zyxwvutstuvwxyz: " + SpecialStringSolution.getSpecialString("zyxwvutstuvwxyz"));
        System.out.println("zyz: " + SpecialStringSolution.getSpecialString("zyz"));
        System.out.println("zyxz: " + SpecialStringSolution.getSpecialString("zyxz"));
        System.out.println("zyzyzyz: " + SpecialStringSolution.getSpecialString("zyzyzyz"));
    }
}
