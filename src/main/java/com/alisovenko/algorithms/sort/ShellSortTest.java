package com.alisovenko.algorithms.sort;


/**
 * TBD: add comments for ShellAltorithmTest.java. Created: 08.10.2010
 * @author <a href="mailto:anton.lisovenko@db.com">Anton Lisovenko</a>
 */
public class ShellSortTest extends BaseSortTest {

    /**
     * TBD
     * @param j
     * @param i
     */
    private void prettyPrint(int j, int i) {
        System.out.print("After:  ");
        for (int idx = 0; idx < array.length; idx++) {
            int element = array[idx];
            if (idx == j || idx == i) {
                System.out.printf("%1$-4s", ">" + element + "<");
            }
            else {
                System.out.printf("%1$-4s", " " + element);
            }
        }

        System.out.println();
    }

    /**
     * TBD
     * @param j
     * @param i
     */
    private void prettyPrintBefore(int j, int i) {
        System.out.print("Before: ");
        for (int idx = 0; idx < array.length; idx++) {
            int element = array[idx];
            if (idx == j || idx == i) {
                System.out.printf("%1$-4s", ">" + element + "<");
            }
            else {
                System.out.printf("%1$-4s", " " + element);
            }
        }

        System.out.println();
    }

    
    public void sort(Integer[] array) {
        int gap = array.length/2;
        
        int i,j;
        int k, e;
        while (gap >= 1) {
            i = 0;
            j = i + gap;
            
            System.out.printf("GAP: %1$s", gap);
            System.out.println();
            
            for (; j < array.length; i++, j++) {
                for (k = j, e = i; e >= 0; e-=gap, k-=gap) {
                    prettyPrintBefore(e, k);
                    if (array[k] < array[e]) {
                        int temp = array[e];
                        array[e] = array[k];
                        array[k] = temp;
                        prettyPrint(e, k);    
                    }
                    else {
                        break;
                    }
                }                
            }
            gap /=2.2;
        }
    }
    /**
     * @see com.alisovenko.algorithms.sort.BaseSortTest#sort()
     * 
     * 
     */
    //@Override
    public void sort2(Integer[] array) {
        for (int gap = array.length / 2; gap > 0; gap = gap == 2 ? 1 : (int) (gap / 2.2)) {
            System.out.println("        Gap: " + gap);
            for (int i = gap; i < array.length; i++) {
                Integer tmp = array[i];
                int j = i;

                prettyPrintBefore(j, j - gap);

                for (; j >= gap && tmp.compareTo(array[j - gap]) < 0; j -= gap) {
                    if (j != i)
                        prettyPrintBefore(i, j - gap);
                    array[j] = array[j - gap];
                    prettyPrint(i, j - gap);
                }
                array[j] = tmp;
                prettyPrint(j, -1);
            }
        }
    }
}
