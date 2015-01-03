import org.junit.Test;

/**
 * <p/>
 * alisovenko 14.04.13
 */
public class SeamCarverTest {
	@Test
	public void testEnergy() {
		System.out.println("seamCarving/3x7.png");
		SeamCarver sc = new SeamCarver(new Picture("seamCarving/3x7.png"));

		for (int i = 0; i < sc.width(); i++) {
			for (int j = 0; j < sc.height(); j++) {
				System.out.printf("%-20.2f ", sc.energy(i, j));
			}
			System.out.println();
		}



		sc = new SeamCarver(new Picture("seamCarving/5x6.png"));
		System.out.println("seamCarving/5x6.png");
		for (int i = 0; i < sc.width(); i++) {
			for (int j = 0; j < sc.height(); j++) {
				System.out.printf("%-20.2f ", sc.energy(i, j));
			}
			System.out.println();
		}
	}

	@Test
	public void testSeam() {
		PrintSeams.main(new String[]{"seamCarving/3x7.png"});
		PrintSeams.main(new String[]{"seamCarving/5x6.png"});
	}

	@Test
	public void testSeamBig() {
		PrintSeams.main(new String[]{"seamCarving/HJocean.png"});
	}

	@Test
	public void testFindRemoveSeam() {
		ResizeDemo.main(new String[]{"seamCarving/6x5.png", "2", "1"});
	}

	@Test
	public void testRemoveVerticalSeam() {
		PrintSeams.main(new String[]{"seamCarving/10x12.png"});
//		ResizeDemo.main(new String[]{"seamCarving/10x12.png", "1", "0"});
	}

}
