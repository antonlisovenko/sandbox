package com.alisovenko.algorithms.np;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Dynamic programming solution to the knapsack problem.
 * {@see http://commons.wikimedia.org/wiki/File:%D0%94%D0%B8%D0%BD%D0%B0%D0%BC%D0%B8%D1%87%D0%B5%D1%81%D0%BA%D0%BE%D0%B5_%D0%BF%D1%80%D0%BE%D0%B3%D1%80%D0%B0%D0%BC%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D0%B5.png?uselang=ru}
 * alisovenko 10.02.13
 */
public class KnapsackProblem {
	public static void main(String[] args) throws IOException {
		// 1 Read file
		KnapsackProblem test = new KnapsackProblem();
		InputStream input = test.getClass().getResourceAsStream("data/knapsack/knapsack1.txt");
//		InputStream input = test.getClass().getResourceAsStream("data/knapsack/knapsack2.txt");
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));

		String line = reader.readLine();

		int W = Integer.valueOf(line.split(" ")[0]);
		int N = 0;
		if (line.split(" ").length > 1){
			N = Integer.valueOf(line.split(" ")[1]);
		}
		System.out.printf("Weight: %d, size: %d\n", W, N);
		int[] v = new int[N];
		int[] w = new int[N];

		int p = 0;
		while ((line = reader.readLine()) != null) {
			String[] chunks = line.split(" ");
			v[p] = Integer.valueOf(chunks[0]);
			w[p++] = Integer.valueOf(chunks[1]);
		}
		System.out.println("In fact size: " + v.length);

		// 2 Do knapsack
		int[][] m = new int[N][];
		for (int j = 0; j < N; j++) {
			m[j] = new int[W];
		}
		for (int j = 0; j < W; j++) {
			m[0][j] = 0;
		}

		for (int i = 1; i < N; i++) {
			for (int k = 0; k < W; k++) {
				if (k >= w[i]) {
					m[i][k] = Math.max(m[i - 1][k], m[i-1][k-w[i]] + v[i]);
				}
				else {
					m[i][k] = m[i-1][k];
				}
			}
		}

		System.out.println(m[N-1][W-1]);
	}


}
