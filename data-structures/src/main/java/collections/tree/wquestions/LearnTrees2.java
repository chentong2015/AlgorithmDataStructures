package collections.tree.wquestions;

import collections.tree.bean.TreeNode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LearnTrees2 {

    // Construct Binary Tree from Preorder and Inorder Traversal
    // Preorder is the preorder traversal of a binary tree
    // Inorder is the inorder traversal of the same tree
    // preorder = [3,9,20,15,7],
    // inorder = [9,3,15,20,7]
    // -> [3,9,20,null,null,15,7]
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 测试理解：1. 先确定root node, 然后通过中序遍历切分左右子树的节点，嵌套构建左右子树，在每个子树中依次迭代
        //            O(N) 每一个节点都会被添加到tree中  O(> N) > O(1) 每处理一个节点，都开辟了多余的数组空间 !!
        TreeNode root = new TreeNode(preorder[0]);
        if (preorder.length > 1) {
            int leftLength = 0;
            for (int j = 0; j < inorder.length; j++) {  // 这里查找的算法可以优化 !!
                if (root.getVal() == inorder[j]) {
                    leftLength = j;
                    break;         // 减少多余的时间复杂度 !! 快1ms
                }
            }
            if (leftLength > 0) {  // 实际上只需要位置坐标，不需要构建4个数组增加内存空间的消耗 !!
                int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + leftLength); // 排除掉root节点后，再提取前序遍历结果
                int[] leftInorder = Arrays.copyOfRange(inorder, 0, leftLength);
                root.setLeft(buildTree(leftPreorder, leftInorder));
            }
            if (leftLength + 1 < preorder.length) {
                int[] rightPreorder = Arrays.copyOfRange(preorder, leftLength + 1, preorder.length);
                int[] rightInorder = Arrays.copyOfRange(inorder, leftLength + 1, inorder.length);
                root.setRight(buildTree(rightPreorder, rightInorder));
            }
        }
        return root;
    }

    private int preorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    // 正确理解：1. 将中序遍历的节点使用HashMap存储, 优化节点查找算法, 通过位置将preorder进行拆分, 然后逐一添加node
    //           O(n) O(n)
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        if (left > right) { // left == right 时，说明此时只有唯一的节点可以添加
            return null;
        }
        int rootValue = preorder[preorderIndex++]; // 前序遍历的第一个节点是root根节点
        TreeNode root = new TreeNode(rootValue);
        root.setLeft(arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1)); // 从位置上面拆分成左右子树 !!
        root.setRight(arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right));
        return root;
    }
}
