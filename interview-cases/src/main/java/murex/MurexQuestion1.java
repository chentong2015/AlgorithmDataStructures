package murex;


public class MurexQuestion1 {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    // TODO. 二叉树中最小的公共前继
    // LCA(Lowest Common Ancestor) of a Binary Tree
    // 输入二叉树的root结点，以及两个结点的值，返回最小公共node
    public static int findLCA(TreeNode root, int p, int q) {
        // 判断初始条件
        // 递归判断左右子树
        // 按照找到的结果进行返回
        return 0;
    }

    // 对字符串进行指定规则的加工，添加指定的后缀??
    // this asa out test
    // ihts?? asa?? uot?? sett??
    // 至少有一个单词，并且单词之间使用空格隔开
    // O(N) O(N) N为字符串中字符的数目
    public static String toMurextLatin(String suffix, String message) {
        StringBuilder result = new StringBuilder();
        for (String word : message.split(" ")) {
            String formattedWord = formatWord(suffix, word);
            if (result.length() > 0) result.append(" ");
            result.append(formattedWord);
        }
        return result.toString();
    }

    private static String formatWord(String suffix, String word) {
        StringBuilder tempWord = new StringBuilder(word.toLowerCase());
        tempWord.reverse();
        char tempChar = tempWord.charAt(0);
        if (tempChar != 'a' && tempChar != 'e' && tempChar != 'i') {
            tempWord.deleteCharAt(0).append(tempChar);
        }
        return tempWord.append(suffix).toString();
    }
}
