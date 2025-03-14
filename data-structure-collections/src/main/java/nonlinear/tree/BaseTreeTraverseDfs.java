package nonlinear.tree;

import nonlinear.tree.bean.TreeNode;

import java.util.*;

// TODO: DFS非递归的遍历方式
public class BaseTreeTraverseDfs {

    // TODO. Pre Order 前序遍历
    // O(n) O(1)
    // 最多只有两个节点在栈中，比"递归"的空间复杂度要更加优化 !!
    //
    // 需要首先将root跟节点入栈，再取出来依次循环
    public void preOrderStackTraverse(TreeNode root) {
        Stack<TreeNode> nodes = new Stack<>();
        nodes.push(root);
        while (!nodes.isEmpty()) {
            TreeNode current = nodes.pop();
            System.out.println(current.getVal());

            if (current.getRight() != null) {
                nodes.push(current.getRight());
            }
            if (current.getLeft() != null) {
                nodes.push(current.getLeft());
            }
        }
    }

    // TODO. In Order 中序遍历
    // O(n) O(n)
    // 这里的空间复杂度和Tree的高度有关，也取决于是否是平衡树
    // 最糟糕的情况是所有的node都排在root左边，栈中需要压入全部的node
    public void inOrderStackTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                // 先压root栈，然后持续压左边子树，直到压到叶子节点
                stack.push(node);
                node = node.getLeft();
            } else {
                // 从最后压入的左边子树的叶子节点开始出栈
                // 如果该节点有右边的子树，将有节点压入，作为root之后出栈的node
                TreeNode popNode = stack.pop();
                System.out.println(popNode.getVal());

                node = popNode.getRight();
            }
        }
    }

    // TODO. Post Order 后续遍历
    // 记录始终记录前一个输出的节点，当且仅当node的右边节点为null，
    // 或者右边节点是前一个输出，则最后才输出该node ==> 后继输出 !!
    //          Using 1 Stack. O(n), O(n)
    //          3
    //    1              5
    // null 2visited   4   6
    // => stack: 3 1
    // => out: 2 1 出栈的顺序(左,右,后)
    public void postOrderStackTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack();
        TreeNode visitedRightNode = null;

        while (root != null || !stack.empty()) {
            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                root = stack.peek();
                // 如果node的右边子树为null或已经被遍历过
                if (root.getRight() == null || root.getRight() == visitedRightNode) {
                    System.out.println(root.getVal());
                    stack.pop();
                    visitedRightNode = root;  // 纪录前面一个输出的节点，把遍历过的位置路径做标记
                    root = null;              // 说明这个node和它下面的子树都遍历完成，再次循环的时候需要从stack栈中取node !!
                } else {
                    root = root.getRight();
                }
            }
        }
    }
}
