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

    // TODO. 本质上是基于"目录字符串"为元素来处理 => Split字符串
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
        for (String dir : path.split("/")) {
            if (dir.isEmpty() || dir.equals(".")) {
                continue;
            }
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(dir);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }

    // TODO. 基于'每个字符'为元素来逐一处理 => 增加复杂度
    public String simplifyPathByChars(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder strBuilder = new StringBuilder();
        int index = 0;
        while (index < path.length()) {
            if (path.charAt(index) == '/') {
                // 遇到/就处理前面读取的字符串
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
        // 写入最后读取的字符串
        writeToStack(stack, strBuilder);

        while (!stack.isEmpty()) {
            strBuilder.insert(0, "/" + stack.pop());
        }
        return strBuilder.toString().isEmpty() ? "/" : strBuilder.toString();
    }

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
