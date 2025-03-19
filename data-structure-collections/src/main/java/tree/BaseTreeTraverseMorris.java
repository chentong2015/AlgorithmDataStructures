package tree;

import tree.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

// TODO: Morris Traversal 二叉树的最佳遍历算法
// Using No Stacks, O(n) Time & O(1) Space
public class BaseTreeTraverseMorris {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> out = new ArrayList<Integer>();
        if (root == null) return out;
        TreeNode dummy = new TreeNode(-1), pre = null;
        dummy.setLeft(root);
        root = dummy;
        while (root != null) {
            if (root.getLeft() != null) {
                pre = root.getLeft();
                while (pre.getRight() != null && pre.getRight() != root)
                    pre = pre.getRight();
                if (pre.getRight() == null) {
                    pre.setRight(root);
                    root = root.getLeft();
                } else {
                    TreeNode node = pre;
                    reverse(root.getLeft(), pre);
                    while (node != root.getLeft()) {
                        out.add(node.getVal());
                        node = node.getRight();
                    }
                    // Print again since we are stopping at node=root.left
                    out.add(node.getVal());
                    reverse(pre, root.getLeft());
                    pre.setRight(null);
                    root = root.getRight();
                }
            } else {
                root = root.getRight();
            }
        }
        return out;
    }

    public void reverse(TreeNode from, TreeNode to) {
        if (from == to)
            return;
        TreeNode prev = from, node = from.getRight();
        while (prev != to) {
            TreeNode next = node.getRight();
            node.setRight(prev);
            prev = node;
            node = next;
        }
    }
}
