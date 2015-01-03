package com.alisovenko.interviews.tree;

import com.alisovenko.base.Node;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 * <p>
 * - сохраняем следующий узел в итераторе (возвращаем на next())
 * - если у узла есть левый ребенок - он следующий
 * - если у узла есть правый ребенок - он следующий
 * - в противном случае мы достигли leaf - нужно вернуться к следующему непосещенному правому ребенку (идем вверх
 * до тех пор, пока мы являемся правым ребенком или пока правый ребенок пустой)
 *
 * @author alisovenko 25.10.14
 */
public class PreOrderIteratorParent {
    private Node current;

    public PreOrderIteratorParent(Node root) {
        current = root;
    }

    public Node next() {
        if (current == null) {
            return null;
        }
        Node result = current;
        Node cur = current;

        if (cur.left != null) {
            current = cur.left;
        } else if (cur.right != null) {
            current = cur.right;
        } else {
            // We've just visited a leaf node. Go back up the tree until we find a node
            // with a right child that we haven't seen yet.
            Node parent = cur.parent;
            while (parent != null && (parent.right == cur || parent.right == null)) {
                cur = parent;
                parent = parent.parent;
            }
            if (parent == null) {
                current = null;
            } else {
                current = parent.right;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        Node n = new Node(null, 5);
        n.add(3);
        n.add(1);
        n.add(8);
        n.add(7);
        n.add(9);
        n.print();

        PreOrderIteratorParent it = new PreOrderIteratorParent(n);
        Node next;
        while ((next = it.next()) != null) {
            System.out.println(next.key);
        }
    }
}
