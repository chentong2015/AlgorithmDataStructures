package nonlinear.tree.binary_search_tree;

import nonlinear.tree.bean.TreeNode;

import java.util.Stack;

public class BinarySearchTree {

    // Search in a Binary Search Tree
    // Find the node in the BST that the node's value equals val and
    // return the subtree rooted with that node, return null if not doesn't exist
    // root = [4,2,7,1,3], val = 2 -> [2,1,3]  假设这里查找的节点值在数中是唯一的
    //
    // BST树的递归查找法: 代码少且清晰，同时复杂度也比较优化
    // O(n) O(1)
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.getVal() == val) {
            return root;
        }

        // 如果当前左子树中没有找到，则从右子树中查找
        TreeNode leftNode = searchBST(root.getLeft(), val);
        if (leftNode == null) {
            return searchBST(root.getRight(), val);
        }
        return leftNode;
    }

    // TODO. 利用中序遍历的过程，第k个出栈的元素将是第k小的元素
    // Kth Smallest Element in a BST (Binary Search Tree)
    // Given the root of a binary search tree, and an integer k,
    // return the kth (1-indexed) smallest element in the tree
    //
    // O(n) 遍历树的所有节点
    // O(n) Stack栈存储节点的空间复杂度
    public int kthSmallest(TreeNode root, int k) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                TreeNode popNode = stack.pop();
                if (--k == 0) {
                    return popNode.getVal();
                }
                node = popNode.getRight();
            }
        }
        return 0;
    }
}
