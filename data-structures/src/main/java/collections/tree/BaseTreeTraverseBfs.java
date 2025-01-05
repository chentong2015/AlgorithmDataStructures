package collections.tree;

import collections.tree.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

// TODO: 非递归的"BFS - Template I"广度优先遍历
public class BaseTreeTraverseBfs {

    // 使用queue队列"一层一层"遍历, 先入队列的先出来, 类似排队
    // O(n) 如果是完全二叉树，队列中对多存储的node是最下面一层，具有和O(n/2)相当的空间复杂度
    // O(n)
    public void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.getVal());

            if (node.getLeft() != null) {
                queue.add(node.getLeft());  // 先添加左边node到队列
            }
            if (node.getRight() != null) {
                queue.add(node.getRight()); // 后添加右边node到队列
            }
        }
    }

    // BFS遍历强化：每轮Queue队列中存储的是一层node的信息，利用levelSize可以通过size统一批次读取 O(n) O(n)
    public List<List<Integer>> levelTraverse2(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        Queue<TreeNode> queue2 = new ArrayDeque<>();
        queue2.add(root);
        while (!queue2.isEmpty()) {
            int levelSize = queue2.size();
            List<Integer> levelValues = new ArrayList<>();
            while (levelSize > 0) {
                // 利用每队列的size(), 每一轮处理一层结点的信息
                TreeNode node = queue2.poll();
                levelValues.add(node.getVal());
                if (node.getLeft() != null) {
                    queue2.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue2.add(node.getRight());
                }
                levelSize--;
            }
            results.add(levelValues);
        }
        return results;
    }
}
