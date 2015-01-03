package com.alisovenko.algorithms.tree;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;

import org.junit.Test;

import java.util.LinkedList;

import java.util.Deque;

import java.util.List;

/**
 * Non-recursive solution to the problem: http://www.fulcrumweb.com.ua/archives/991
 * 
 * We need to print all nodes at the specified level. I added the parent field to calculate the level. Other ways to get
 * the level: store nodes with their levels in hashmap. Store the level in the inner field.
 * 
 * <p>
 * Created: 23.07.2012
 * </p>
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class SpecificLevelTree {
    private static class Node {
        
        /**
         * @param value
         */
        public Node(int value) {
            super();
            this.value = value;
        }

        Node parent;
        Node left;
        Node right;
        int value;
        
        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return Integer.toString(value);
        }
    }
    
    @Test
    public void test() {
        Node tree = generateTree();
        
        // Non-recursive solution (needs additional field like "parent" to get the level of the node)
        List<Node> result = new LinkedList<SpecificLevelTree.Node>();
        getAllNodesAtLevel(3, tree, result);
        
        System.out.println("Result: " + Arrays.toString(result.toArray()));
        
        // Recursive solution
        List<Node> secondResult = new LinkedList<SpecificLevelTree.Node>();
        getAllNodesAtLevelRecursively(3, 0, tree, secondResult);
        
        System.out.println("Second result: " + Arrays.toString(result.toArray()));
        assertEquals(result, secondResult);
    }
    
    public void getAllNodesAtLevel(int level, Node tree, List<Node> nodes) {
        Deque<Node> queue = new LinkedList<Node>();
        queue.offer(tree);
        
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int curLevel =getLevel(node, 0); 
            if (curLevel < (level - 1)) {
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            else if (curLevel == (level - 1)) {
                // Processing children (they are at necessary level)
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }        
    }
    
    public void getAllNodesAtLevelRecursively(int level, int curLevel, Node curNode, List<Node> nodes) {
        if (curLevel == level - 1) {
            // Ok, we found the parent
            // Processing children (they are at necessary level)
            if (curNode.left != null) {
                nodes.add(curNode.left);
            }
            if (curNode.right != null) {
                nodes.add(curNode.right);
            }
            
            return;
        }
        
        curLevel++;
        if (curNode.left != null && curLevel < level) {
         getAllNodesAtLevelRecursively(level, curLevel, curNode.left, nodes);   
        }
        if (curNode.right != null && curLevel < level) {
            getAllNodesAtLevelRecursively(level, curLevel, curNode.right, nodes);   
        }
    }
    
    private int getLevel(Node node, int curLevel) {
        if (node.parent != null) {
            return getLevel(node.parent, ++curLevel);
        }
        return curLevel;
    }
    
    /**
     *                  5
     *        2                   7
     *    9        15       2           6
     *           8    12
     * @return
     */
    public Node generateTree() {
        Node root = new Node(5);        
        Node n1 = new Node(2);        
        Node n2 = new Node(7);        
        Node n3 = new Node(9);        
        Node n4 = new Node(15);        
        Node n5 = new Node(2);        
        Node n6 = new Node(6);        
        Node n7 = new Node(8);        
        Node n8 = new Node(12);
        
        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n4.left = n7;
        n4.right = n8;
        n1.parent = root;
        n2.parent = root;
        n3.parent = n1;
        n4.parent = n1;
        n5.parent = n2;
        n6.parent = n2;
        n7.parent = n4;
        n8.parent = n4;
        
        return root;
    }

}
