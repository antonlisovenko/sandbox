import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
//import java.util.Stack;

/**
 * <p/>
 * alisovenko 13.04.13
 */
public class SeamCarver {
	private static final double MAX_ENERGY = 195075.0;
	private Picture p;
	private int width;
	private int height;
	private double[][] energyMatrix;
	//	private EdgeWeightedDigraph verticalGraph;
	//	private EdgeWeightedDigraph horizontalGraph;

	//	Map<Integer, Double> energyMap = new HashMap<Integer, Double>();

	public SeamCarver(Picture picture) {
		this.p = picture;
		this.width = picture.width();
		this.height = picture.height();

		recalculateEnergy();
		//		recreateGraphs();
	}

	private void recalculateEnergy() {
		energyMatrix = new double[p.width()][p.height()];
		for (int i = 0; i < p.width(); i++) {
			for (int j = 0; j < p.height(); j++) {
				if (i == 0 || i == p.width() - 1 || j == 0 || j == p.height() - 1) {
					energyMatrix[i][j] = MAX_ENERGY;
				} else {
					// calculate x difference
					Color left = p.get(i - 1, j);
					Color right = p.get(i + 1, j);
					double xDiff = (Math.pow(left.getRed() - right.getRed(), 2) +
							Math.pow(left.getGreen() - right.getGreen(), 2) +
							Math.pow(left.getBlue() - right.getBlue(), 2));

					// calculate y difference
					Color top = p.get(i, j - 1);
					Color bottom = p.get(i, j + 1);
					double yDiff = (Math.pow(top.getRed() - bottom.getRed(), 2) +
							Math.pow(top.getGreen() - bottom.getGreen(), 2) +
							Math.pow(top.getBlue() - bottom.getBlue(), 2));

					// Total energy is the sum of differences
					energyMatrix[i][j] = xDiff + yDiff;
				}
			}
		}
	}

	// current p
	public Picture picture() {
		return this.p;
	}

	// width of current p
	public int width() {
		return this.width;
	}

	// height of current p
	public int height() {
		return this.height;
	}

	// energy of pixel at column x and row y
	public double energy(int x, int y) {
		return energyMatrix[x][y];
	}

	// sequence of indices for vertical seam
	public int[] findVerticalSeam() {
		return findSsp(energyMatrix);

	}

	// sequence of indices for horizontal seam
	public int[] findHorizontalSeam() {
		// Transposing the matrix
		double[][] transpose = new double[energyMatrix[0].length][energyMatrix.length];
		for (int i = 0; i < energyMatrix.length; i++) {
			for (int j = 0; j < energyMatrix[i].length; j++) {
				transpose[j][i] = energyMatrix[i][j];
			}
		}

		return findSsp(transpose);
	}

	// remove horizontal seam from p
	public void removeHorizontalSeam(int[] a) {
		if (a.length != p.width()) {
			throw new RuntimeException("Wrong seam");
		}
		Picture pict = new Picture(p.width(), p.height() - 1);

		for (int x = 0; x < p.width(); x++) {
			boolean found = false;
			for (int y = 0; y < this.p.height(); y++) {
				if (a[x] == y) {
					// We got to the pixel that should be removed. Ignore it and assume that other pixels have the decreased index
					found = true;
				} else {
					// Creating the new picture - just copying the pixels to the new picture
					pict.set(x, (found ? y - 1 : y), this.p.get(x, y));
				}
			}
		}
		p = pict;
		this.height = pict.height();

		recalculateEnergy();
	}

	// remove vertical seam from p
	public void removeVerticalSeam(int[] a) {
		if (a.length != p.height()) {
			throw new RuntimeException("Wrong seam");
		}
		Picture pict = new Picture(p.width() - 1, p.height());

		for (int y = 0; y < p.height(); y++) {
			boolean found = false;
			for (int x = 0; x < p.width(); x++) {
				if (a[y] == x) {
					// We got to the pixel that should be removed. Ignore it and assume that other pixels have the decreased index
					found = true;
				} else {
					// Creating the new picture - just copying the pixels to the new picture
					pict.set((found ? x - 1 : x), y, p.get(x, y));
				}
			}
		}
		p = pict;
		this.width = pict.width();

		recalculateEnergy();
	}

	private int[] findSsp(double[][] energyMatrix) {
		// Iterate through all vertices at the top line
		int[] ssp = new int[energyMatrix.length];
		double minLength = Double.POSITIVE_INFINITY;

		for (int i = 0; i < energyMatrix.length; i++) {
			int[] path = new int[energyMatrix[0].length];
//			System.out.println("Processing " + i + " column of " + energyMatrix.length);
			double weight = ssp(energyMatrix, i, path);
			if (weight < minLength) {
				ssp = path;
				minLength = weight;
			}
		}
		return ssp;
	}

	private double ssp(double[][] energyMatrix, int x, int[] ssp) {
		// 1. Topologically sort the matrix from i. Stack contains two-element arrays for x,y coordinates
		//Stack<Integer[]> order = new Stack<Integer[]>();
		java.util.Stack<Integer[]> order = new java.util.Stack<Integer[]>();
		Set<List<Integer>> memory = new HashSet<List<Integer>>();

		long start = System.currentTimeMillis();
		sortTopologicaly(energyMatrix, order, memory, x, 0);

//		System.out.println(
//				((double) (System.currentTimeMillis() - start) / 1000) + " seconds for topological sort, size: " +
//						order.size());

		// 2. Relax the sorted root
		double[][] distTo = new double[energyMatrix.length][energyMatrix[0].length];
		int[][] parentOf = new int[energyMatrix.length][energyMatrix[0].length];
		for (int v = 0; v < energyMatrix.length; v++)
			for (int i = 0; i < energyMatrix[0].length; i++) {
				distTo[v][i] = Double.POSITIVE_INFINITY;
			}
		distTo[x][0] = 0.0;

		// visit vertices in topological order
		/*for (Integer[] v : order) {
			for (int[] ch : getChildren(energyMatrix, v)) {
				if (ch.length > 0) {
					relax(energyMatrix, distTo, parentOf, v[0], v[1], ch[0], ch[1]);
				}
			}
		}*/

		Integer[] next;
		while (!order.isEmpty()){
			next = order.pop();
			for (int[] ch : getChildren(energyMatrix, next)) {
				if (ch.length > 0) {
					relax(energyMatrix, distTo, parentOf, next[0], next[1], ch[0], ch[1]);
				}
			}
		}





		// 3. Check all bottom-line vertices and find the one with least distance
		double minDistance = Double.POSITIVE_INFINITY;
		int minIdx = -1;
		for (int i = 0; i < energyMatrix.length; i++) {
			if (distTo[i][energyMatrix[0].length - 1] < minDistance) {
				minDistance = distTo[i][energyMatrix[0].length - 1];
				minIdx = i;
			}
		}

		if (minIdx < 0) {
			throw new RuntimeException("Failed to find path");
		}

		// 4. We found the minimal ssp to the bottom line! Let's gather the whole ssp
		for (int i = energyMatrix[0].length - 1, t = minIdx; i >= 0; i--) {
			ssp[i] = t;
			t = parentOf[t][i];
		}

		return minDistance;

	}


	private int[][] getChildren(double[][] energyMatrix, Integer[] v) {
		assert v.length == 2;
		int x = v[0];
		int y = v[1];
		if (y >= energyMatrix[0].length - 1) {
			// We got to bottom - no children
			return new int[][]{{}};
		}
		y = y + 1;
		// If there are all three children
		if (x > 0 && x < energyMatrix.length - 1) {
			return new int[][]{{x - 1, y}, {x, y}, {x + 1, y}};
		}
		// There is no leftmost child
		else if (x <= 0) {
			return new int[][]{{x, y}, {x + 1, y}};
		}
		// There is no rightmost child
		else {
			return new int[][]{{x - 1, y}, {x, y}};
		}
	}

	private void relax(double[][] energyMatrix, double[][] distTo, int[][] parentOf, int x, int y, int x2, int y2) {
		if (distTo[x2][y2] > distTo[x][y] + energyMatrix[x2][y2]) {

			distTo[x2][y2] = distTo[x][y] + energyMatrix[x2][y2];
			parentOf[x2][y2] = x;
		}
	}

	private void sortTopologicaly(double[][] energyMatrix, java.util.Stack<Integer[]> order, Set<List<Integer>> memory, int x,
	                              int y) {
		memory.add(Arrays.asList(x, y));
		// moving to left-bottom
		if (y < (energyMatrix[0].length - 1)) {
			if (!memory.contains(Arrays.asList(x - 1, y + 1))) {
				if (x > 0) {
					sortTopologicaly(energyMatrix, order, memory, x - 1, y + 1);
				}
			}
			// moving to right-bottom
			if (!memory.contains(Arrays.asList(x + 1, y + 1))) {
				if (x < (energyMatrix.length - 1)) {
					sortTopologicaly(energyMatrix, order, memory, x + 1, y + 1);
				}
			}
			// moving to center-bottom
			if (!memory.contains(Arrays.asList(x, y + 1))) {
				sortTopologicaly(energyMatrix, order, memory, x, y + 1);
			}
		}

		order.push(new Integer[]{x, y});
	}

}
