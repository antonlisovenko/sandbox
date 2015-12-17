package com.alisovenko.hackerrank;

import java.util.*;

/**
 * @author alisovenko
 *         11/22/15.
 */
public class CommonManager {
    private static class Tree {
        Node root;

        Map<String, Node> dict = new HashMap<>();

        public void addEdge(String parent, String child) {
            if (!dict.containsKey(parent)) {
                dict.put(parent, new Node(parent));
            }
            if (!dict.containsKey(child)) {
                dict.put(child, new Node(child));
            }
            Node p = dict.get(parent);
            Node c = dict.get(child);

            if (p.left == null) {
                p.left = c;
            }
            else {
                p.right = c;
            }

            if (root == null) {
                root = p;
            }
        }
    }
    private static class Node {
        private final String val;
        Node left;
        Node right;

        private Node(String val) {
            this.val = val;
        }
    }
    static void OutputCommonManager(int count, Scanner in) {
        Set<String> employees = new HashSet<>();

        String emp1 = in.next();
        String emp2 = in.next();

        Tree tree = new Tree();

        // While we have not read all employees
        while (employees.size() != count) {
            String manager = in.next();
            String employee = in.next();

            tree.addEdge(manager, employee);
            employees.add(manager);
            employees.add(employee);
        }

        String commonManager = findCommonManager(tree.root, emp1, emp2);
        System.out.println(commonManager);

    }

    private static String findCommonManager(Node root, String emp1, String emp2) {
        assert root != null : "Seems data is corrupted!";

        if (root.val.equals(emp1)) {
            return emp1;
        }
        if (root.val.equals(emp2)) {
            return emp2;
        }

        boolean e1 = contains(root.left, emp1);
        boolean e2 = contains(root.left, emp2);

        if (e1 != e2) {
            // employees are in different hierarchies
            return root.val;
        }
        return findCommonManager(e1 ? root.left : root.right, emp1, emp2);
    }

    private static boolean contains(Node root, String emp) {
        if (root == null) {
            return false;
        }
        if (root.val.equals(emp)) {
            return true;
        }
        return contains(root.left, emp) || contains(root.right, emp);
    }


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int _count;
        _count = Integer.parseInt(in.nextLine());

        OutputCommonManager(_count, in);

    }
}
