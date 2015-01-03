package com.alisovenko.algorithms.graphs;

import com.alisovenko.algorithms.graphs.model.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: alisovenko
 * Date: 03.01.13
 * Time: 3:09
 * To change this template use File | Settings | File Templates.
 */
public class Utils {
    /**
     * TBD
     * @return
     * @throws java.io.IOException
     * @throws NumberFormatException
     */
    public static Graph readGraph(String path) throws NumberFormatException, IOException {
        Graph gr = new Graph();

        PrimMSTTest test = new PrimMSTTest();
        InputStream input = test.getClass().getResourceAsStream(path);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));

        String line = reader.readLine();

        int numberOfNodes = Integer.valueOf(line.split(" ")[0]);
        int numberOfEdges = 0;
        if (line.split(" ").length > 1){
            numberOfEdges = Integer.valueOf(line.split(" ")[1]);
        }
        System.out.println("Expected: Nodes: " + numberOfNodes + ", edges: " + numberOfEdges);
        while ((line = reader.readLine()) != null) {
            String[] chunks = line.split(" ");
            gr.add(parseInt(chunks[0]), parseInt(chunks[1]), parseInt(chunks[2]));
        }
        System.out.println("In fact: Nodes: " + gr.size() + ", edges: " + gr.edgesSize());

        assert (gr.size() == numberOfNodes);
        assert (gr.edgesSize() == numberOfEdges);

        return gr;
    }

    private static int parseInt(String str) {
        return Integer.parseInt(str);
    }
}
