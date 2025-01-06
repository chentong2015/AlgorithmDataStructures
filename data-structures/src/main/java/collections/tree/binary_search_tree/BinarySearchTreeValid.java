package collections.tree.binary_search_tree;

import collections.tree.bean.TreeNode;

public class BinarySearchTreeValid {

    // TODO. 核心判断条件: leftMaxNode < Root < rightMinNode 递归判断左右子树
    // Validate "Binary Search Tree"
    // Given the root of a binary tree, determine if it is a valid binary search tree (BST)
    // 左边nodes都比key小，右边的所有nodes都比key大，同时左右都都各自满足BST
    //
    // O(n) O(n) 递归方法造成栈空间的开销, 最差的情况是n个节点排在n层 !!
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }

        // DFS深度查找，直到子树的叶子节点
        TreeNode tempNode = root.getLeft();
        TreeNode leftMaxNode = tempNode;
        while (tempNode != null) {
            leftMaxNode = tempNode;
            tempNode = tempNode.getRight();
        }
        
        tempNode = root.getRight();
        TreeNode rightMinNode = tempNode;
        while (tempNode != null) {
            rightMinNode = tempNode;
            tempNode = tempNode.getLeft();
        }

        // 左子树找到的最大值 vs 右边子树找到的最小值
        if (leftMaxNode == null && rightMinNode == null) {
            return true;
        }
        if (leftMaxNode == null) {
            if (root.getVal() < rightMinNode.getVal()) {
                return isValidBST(root.getRight());
            }
        } else if (rightMinNode == null) {
            if (root.getVal() > leftMaxNode.getVal()) {
                return isValidBST(root.getLeft());
            }
        } else {
            // 核心判断条件: root节点的值为中间值
            if (leftMaxNode.getVal() < root.getVal() && root.getVal() < rightMinNode.getVal()) {
                return isValidBST(root.getLeft()) && isValidBST(root.getRight());
            }
        }
        return false;
    }
}
