package com.alisovenko.careerup;

import com.alisovenko.base.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author alisovenko 26.09.14
 */
public class NonRecursiveTreeHeightResolver {
  public static int height(Node root) {
    LinkedList<Node> stack = new LinkedList<>();
    stack.add(root);
    Set<Node> marked = new HashSet<>();

    int maxHeight = 0;
    while (!stack.isEmpty()) {
      Node last = stack.getLast();
      if (last.left != null && !marked.contains(last.left)) {
        stack.addLast(last.left);
        marked.add(last.left);
      } else if (last.right != null && !marked.contains(last.right)) {
        stack.addLast(last.right);
        marked.add(last.right);
      } else {
        maxHeight = Math.max(maxHeight, stack.size());
        stack.removeLast();
      }
    }

    return maxHeight;
  }

  public static void main(String[] args) {
    Node root = new Node(null, 8);
    root.add(4);
    root.add(2);
    root.add(6);
    root.add(7);
    root.add(15);
    root.add(17);
    root.add(19);
    root.add(11);

    System.out.println(height(root));
  }
}
