package stack.simplify_path;

import java.util.Stack;

// Simplify Path
// You are given an absolute path for a Unix-style file system, which always begins with a slash '/'.
// transform this absolute path into its simplified canonical path.
//
// A single period '.' 单点忽略
// A double period '..' 双点找上层
// '//' and '///' are treated as a single slash '/' 多斜杠整合
//
// 1 <= path.length <= 3000
// path consists of English letters, digits, period '.', slash '/' or '_'.
// path is a valid absolute Unix path.
public class SimplifyPath {

    // TODO. 用Stack存储每一层有效目录, 用于记录并回溯
    // "/../"        -> "/"
    // "/home/"      -> "/home"
    // "/home//foo/" -> "/home/foo"
    // "/..hidden"   -> "/..hidden"
    // "/.../a/../b/c/../d/./" -> "/.../b/d"
    // "/home/user/Documents/../Pictures" -> "/home/user/Pictures"
    // "/../..ga/b/.f..d/..../e.baaeeh./.a" -> "/..ga/b/.f..d/..../e.baaeeh./.a"
    //
    // O(N + N) 遍历每一个字符且组合成结果
    // O(N)     栈中可能存储大量字符
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder strBuilder = new StringBuilder();
        int index = 0;
        while (index < path.length()) {
            if (path.charAt(index) == '/') {
                // TODO. 每次遇到/就处理前面的字符串
                writeToStack(stack, strBuilder);

                // 过滤掉后面连续的'/'
                int right = index + 1;
                while (right < path.length() && path.charAt(right) == '/') {
                    right++;
                }
                index = right;
            } else {
                strBuilder.append(path.charAt(index));
                index++;
            }
        }
        writeToStack(stack, strBuilder);

        while (!stack.isEmpty()) {
            strBuilder.insert(0, "/" + stack.pop());
        }
        return strBuilder.toString().isEmpty() ? "/" : strBuilder.toString();
    }

    // 忽略记录的特殊字符串, 不存储到Stack栈中
    private void writeToStack(Stack<String> stack, StringBuilder strBuilder) {
        if (strBuilder.isEmpty()) {
            return;
        }
        if (strBuilder.toString().equals(".")) {
            // ignore
        } else if (strBuilder.toString().equals("..")) {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        } else {
            stack.push(strBuilder.toString());
        }
        strBuilder.setLength(0);
    }
}
