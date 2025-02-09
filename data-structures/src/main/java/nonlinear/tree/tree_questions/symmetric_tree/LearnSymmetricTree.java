package nonlinear.tree.tree_questions.symmetric_tree;

// Symmetric Tree
// Given the root of a binary tree, check whether it is a mirror of itself
//
// The number of nodes in the tree is in the range [1, 1000].
// -100 <= Node.val <= 100
public class LearnSymmetricTree {

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // O(n) 算法将会递归完所有的节点
    // O(n) 线程栈的空间开销
    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    private boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        // TODO. 对称条件的本质: 两节点相等，且两节点的子节点“镜像相等”
        return t1.val == t2.val && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }
}
