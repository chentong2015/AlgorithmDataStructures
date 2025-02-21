package nonlinear.tree.tree_questions;

import nonlinear.tree.bean.TreeLinkNode;
import nonlinear.tree.bean.TreeNode;

public class LearnTrees1 {

    // 1 + 2 + 4 + 8 ... n = a1(2^n -1) => 2^n
    // Maximum Depth of Binary Tree 2叉树的深度
    // Given the root of a binary tree, return its maximum depth
    public int maxDepth(TreeNode root) {
        // 测试理解：1. 使用递归算法(1 + 左右子树的深度的最大值), 和具体的节点的值没有关系
        //            O(n) O(n) 递归方法造成栈空间的开销, 最差的情况是n个节点排在n层 !!
        if (root == null) {
            return 0;
        } else if (root.getLeft() == null) {
            return maxDepth(root.getRight()) + 1;
        } else if (root.getRight() == null) {
            return maxDepth(root.getLeft()) + 1;
        } else {
            return Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight())) + 1;
        }
    }

    // Same Tree
    // Given the roots of two binary trees p and q, write a function to
    // check if they are the same or not
    // They are structurally identical, and the nodes have the same value
    //  1      1     -> false
    // 2  1   1  2   -> 往下层递归到左右子树
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // 测试理解：1. 直接递归每个节点，比较判断
        //            O(n+m) 所有的节点都会判断 O(max(L1, L2)) -> O(max(n,m)) 空间复杂度取决于最大的递归深度, 可能在中间一层返回false
        if (p == null) return q == null;
        if (q == null) return false;
        if (p.getVal() != q.getVal()) return false;
        // if (isLeafNode(p) && isLeafNode(q)) { // 如果是叶子节点，则无需在判断左右子树的情况
        //    return true;                       // 可以不需要，因为最终递归到null时，会判断是否为true !!
        // }
        return isSameTree(p.getLeft(), q.getLeft()) && isSameTree(p.getRight(), q.getRight());
    }

    private boolean isLeafNode(TreeNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    // TODO: 不使用队列实现广度优先遍历，同时设定node的.next指向 !!
    // Populating Next Right Pointers in Each Node II
    // Populate each next pointer to point to its next right node.
    // If there is no next right node, the next pointer should be set to NULL
    // Initially, all next pointers are set to NULL
    // root = [1,2,3,4,5,null,7] -> [1,#,2,3,#,4,5,7,#] 找到所有节点的next右边节点，然后设置node的.next指向
    // dummy = pre
    //                   1 -> null
    // dummy ->     2  ->    3 -> null
    // dummy ->  4 ->  5  ->     7 -> null
    // dummy -> ...
    public void connect(TreeLinkNode root) {
        TreeLinkNode dummyHead = new TreeLinkNode(0);
        TreeLinkNode pre = dummyHead;
        while (root != null) {
            if (root.left != null) {
                pre.next = root.left;  // dummy.next = 2    4   4.next -> 5
                pre = pre.next;        // pre = 2           4   pre = 5
            }
            if (root.right != null) {
                pre.next = root.right; // 2.next -> 3       5   5.next -> 7
                pre = pre.next;        // pre = 3           5   pre = 7
            }
            root = root.next;          // null              2   root = 3
            if (root == null) {
                pre = dummyHead;       // 恢复到开始设置，执行每一层的左边节点的前面
                root = dummyHead.next; // root设置成每一层左边的第一个节点
                dummyHead.next = null; // 避免在最后一层出现循环, root始终不为null, 导致无法跳出循环 !!
            }
        }
    }
}
