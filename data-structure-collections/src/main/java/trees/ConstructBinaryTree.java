package trees;

import tree.bean.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTree {

    // Construct Binary Tree from Preorder and Inorder Traversal
    // Preorder is the preorder traversal of a binary tree
    // Inorder is the inorder traversal of the same tree
    // preorder = [3,9,20,15,7],
    // inorder = [9,3,15,20,7]
    // -> [3,9,20,null,null,15,7]

    private int preorderIndex;
    private Map<Integer, Integer> inorderIndexMap;

    // 将中序遍历的节点使用HashMap存储, 优化节点查找算法,
    // 通过位置将preorder进行拆分, 然后逐一添加node
    // O(n)
    // O(n)
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

        // 前序遍历的第一个节点是root根节点
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // 从位置上面拆分成左右子树 !!
        root.setLeft(arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1));
        root.setRight(arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right));
        return root;
    }
}
