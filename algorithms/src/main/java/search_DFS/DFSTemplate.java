package search_DFS;

import org.w3c.dom.Node;

import java.util.Set;

// Find a path from the root node A to the target node G by DFS
//   -> B  ->  E
// A -> C  ->  F -> G
//   -> D
// 先将root结点入栈，然后选择它的其中一个子结点往下遍历，直到叶子结点
// 如果前面的路径中没有找到，则从叶子节点"回退"到上一层，然后找它的后续节点
// 在回退(Backtracking回溯算法)的过程中，遍历了每一条完整的通路
public class DFSTemplate {

    // TODO: DFS Template I - Recursion 递归方式
    //  递归的层级过大会造成stack overflow栈溢出 !!
    // Don't have to use any stacks when we implement DFS recursively
    // 递归DFS找到指定的节点Node / 找指定的路径通路，同时标记已经visited的节点
    public boolean DFS(Node cur, Node target, Set<Node> visited) {
        //  if cur is target {
        //     return true;
        //  }
        //  for (next : each neighbor of cur) {
        //      if (next is not in visited) {
        //          add next to visited;
        //          if DFS(next, target, visited) == true {
        //             return true;
        //          }
        //      }
        //  }
        return false;
    }

    // TODO: DFS - Template II 使用stack栈数据结构
    // 应用场景：二叉树的前序，中序，后续遍历
    boolean DFS(int root, int target) {
        // Set<Node> visited;
        // Stack<Node> stack;
        // add root to stack;
        // while (stack is not empty) {
        //     Node cur = the top element in stack;     peek()
        //     remove the cur from the stack;           pop();
        //     return true if cur is target;
        //     for (Node next : the neighbors of cur) { 查找相邻的node结点
        //         if (next is not in visited) {
        //             add next to visited;
        //             add next to stack;
        //         }
        //     }
        // }
        return false;
    }
}
