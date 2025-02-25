package nonlinear.tree_questions;

import nonlinear.tree.bean.TreeLinkNode;

public class PopulateNextRightPointers {

    // TODO: 不使用队列实现广度优先遍历，同时设定node的.next指向 !!
    // Populating Next Right Pointers in Each Node II
    // Populate each next pointer to point to its next right node.
    // If there is no next right node, the next pointer should be set to NULL
    // Initially, all next pointers are set to NULL
    //
    // 找到所有节点的next右边节点，然后设置node的.next指向
    // root = [1,2,3,4,5,null,7] -> [1,#,2,3,#,4,5,7,#]
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
