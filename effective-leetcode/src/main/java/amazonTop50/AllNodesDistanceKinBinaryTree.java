package amazonTop50;

import java.util.*;

// All Nodes Distance K in Binary Tree
// Given the root of a binary tree, the value of a target node target, and an integer k,
// return an array of the values of all nodes that have a distance k from the target node.
//
// 0 <= Node.val <= 500, 0 <= k <= 1000
// All the values Node.val are unique
// target is the value of one of the nodes in the tree
// 目标节点一定能找到且唯一 !!
public class AllNodesDistanceKinBinaryTree {

    // TODO. 将Tree转换成Graph图形问题: 补充child->parent的链接
    // root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2 -> [7,4,1]
    //           3
    //       5      1
    //    6   2   0   8
    //       7 4
    //
    // O(N+N) 两次遍历全部节点
    // O(N)   HashMap存储N个节点的父级关联

    // TODO. 递归找到Node->Parent指向父节点的映射关系
    private void findParent(TreeNode node, TreeNode par, Map<TreeNode, TreeNode> parent) {
        if (node == null) {
            return;
        }
        parent.put(node, par);
        findParent(node.left, node, parent);
        findParent(node.right, node, parent);
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        findParent(root, null, parent); // 把向上的父类路径找出来 !!

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target); // 从特定的Node节点出发查找
        visited.add(target); // 存储遍历过的Node节点, 避免无限循环

        int distance = 0;
        while (!queue.isEmpty()) {
            if (distance == k) {
                List<Integer> list = new ArrayList<>();
                for (TreeNode node : queue) {
                    list.add(node.val);
                }
                return list; // 当前队列中节点的值就是结果
            }

            for (int i = 0; i < queue.size(); i++) { // BFS 逐层遍历节点
                TreeNode node = queue.poll();
                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.offer(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.offer(node.right);
                }
                // 第三个方向: 往上层父类节点遍历
                if (parent.get(node) != null && !visited.contains(parent.get(node))) {
                    visited.add(parent.get(node));
                    queue.offer(parent.get(node));
                }
            }
            distance++; // 增加移动的距离
        }
        return new ArrayList<>();
    }

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}