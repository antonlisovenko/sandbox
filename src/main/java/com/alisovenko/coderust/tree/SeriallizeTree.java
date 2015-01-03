package com.alisovenko.coderust.tree;

import com.alisovenko.base.Node;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 5
 * |\
 * 3 8
 * | |\
 * 1 7 9
 *   |  \
 *   6    12
 *
 * @author alisovenko 30.11.14
 */
public class SeriallizeTree {
    private static void seriallize(Node root, DataOutputStream out) throws IOException {
        out.writeInt(root.key);

        if (root.left == null) {
            out.writeByte(0);
        }
        else {
            out.writeByte(1);
            seriallize(root.left, out);
        }

        if (root.right == null) {
            out.writeByte(0);
        }
        else {
            out.writeByte(1);
            seriallize(root.right, out);
        }
    }

    private static Node deseriallize(DataInputStream in) throws IOException {
        int i = in.readInt();
        Node n = new Node(null, i);

        byte b = in.readByte();
        if (b > 0) {
            n.left = deseriallize(in);
        }

        b = in.readByte();
        if (b > 0) {
            n.right = deseriallize(in);
        }

        return n;
    }

    public static void main(String[] args) throws IOException {
        Node root = new Node(null, 5);
        root.add(3);
        root.add(1);
        root.add(8);
        root.add(7);
        root.add(9);
        root.add(12);
        root.add(6);

        root.print();

        OutputStream temp = Files.newOutputStream(Paths.get("temp"), StandardOpenOption.CREATE);

        seriallize(root, new DataOutputStream(temp));

        InputStream io = Files.newInputStream(Paths.get("temp"));

        deseriallize(new DataInputStream(io)).print();
    }
}
