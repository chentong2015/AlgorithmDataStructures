package amazonTop50.btree_right_view;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// Binary Tree Right Side View
// Given the root of a binary tree, imagine yourself standing on the right side of it,
// return the values of the nodes you can see ordered from top to bottom.
//
// 0 < N < 100
// -100 <= Node.val <= 100
public class BTreeRightSideView {

    // TODO. BFS遍历二叉树每层的最右侧(最后一个值)
    // root = [1,2,3,null,5,null,4] -> [1,3,4]
    //     1-
    //  2    3-
    //   5     4-
    //  6-
    //
    // O(N)        必须遍历全部节点
    // O(N + N/2)  结果可能存储节点数，Queue最多存储一层的节点数
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int index = 0; index < size; index++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }

                // 当层最右侧的Node值就是右测示图的值
                if (index == size - 1) {
                    result.add(node.val);
                }
            }
        }
        return result;
    }

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
}