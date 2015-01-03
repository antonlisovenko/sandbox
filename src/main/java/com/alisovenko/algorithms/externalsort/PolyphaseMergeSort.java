package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/*
 *  This code is from the book:
 *
 *    Winder, R and Roberts, G (2000) Developing Java
 *    Software, second edition, John Wiley & Sons.
 *
 *  It is copyright (c) 2000 Russel Winder and Graham Roberts.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A function object delivering a polyphase merge sort of a file on the filestore.
 *
 * @author Russel Winder
 * @version 1.1 1999.11.29
 * @see SortableRecord
 * @see SortableRecordReader
 * @see SortableRecordWriter
 * @see SortableRecordInformation
 * @see SortableRecordCopyFile
 */
public final class PolyphaseMergeSort {
    /**
     * The sort operation with some defaults.
     *
     * @param fileName the <CODE>String</CODE> giving the name of the file to be sorted.
     * @param r        the <CODE>SortableRecordInformation</CODE> factory object for creating
     *                 <CODE>SortableRecordReader</CODE>s and <CODE>SortableRecordWriters</CODE>s and able to deliver a
     *                 <CODE>Comparator</CODE>.
     */
    public static void sort(final String fileName,
                            final SortableRecordInformation r)
            throws FileNotFoundException, IOException {
        sort(fileName, 20, 2, r);
    }

    /**
     * The sort operation.
     *
     * @param fileName      the <CODE>String</CODE> giving the name of the file to be sorted.
     * @param blockSize     the number of data items in the initial sorted blocks.
     * @param numberOfFiles the number of files to use for initial dispersion.
     * @param r             the <CODE>SortableRecordInformation</CODE> factory object for creating
     *                      <CODE>SortableRecordReader</CODE>s and <CODE>SortableRecordWriters</CODE>s and able to
     *                      deliver a <CODE>Comparator</CODE>.
     */
    public static void sort(final String fileName,
                            final int approximateBlockSize,
                            int numberOfFiles,
                            final SortableRecordInformation rInfo)
            throws FileNotFoundException, IOException {
        //  Forceably stick to 3 files for now instead of just having one
        //  more than the number of input files.
//        numberOfFiles++ ;
        numberOfFiles = 3;
        //  Create all the files needed for the sorting.
        File file = new File(fileName);
        File[] temp = new File[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            temp[i] = new File("tmp_" + i);
        }
        //  Calculate the block size.  Must get things into the Fibonacci
        //  series for it to work properly.  Have to run through the file
        //  to find the number of records.  We need to copy the file
        //  anyway so this is not a wasted activity.  We use the array F
        //  to calculate the Fibonacci numbers as we go, calculating the
        //  initialBlockSize to best fit the nearest Fibonacci number.
        int indexOfNumberOfBlocks = 1;
        int initialBlockSize = 1;
        int numberOfRecords =
                SortableRecordCopyFile.copy(file, temp[0], rInfo);
        int[] F = new int[numberOfRecords];
        for (int i = 0; i < numberOfFiles; i++) {
            F[i] = 1;
        }
        for (int i = numberOfFiles; i < numberOfRecords; i++) {
            F[i] = 0;
            for (int j = i - 1; j > i - numberOfFiles; j--) {
                F[i] += F[j];
            }
            initialBlockSize = numberOfRecords / F[i];
            if (initialBlockSize < approximateBlockSize) {
                indexOfNumberOfBlocks = i - 1;
                break;
            }
        }
        while (true) {
            if (++initialBlockSize * F[indexOfNumberOfBlocks] >
                    numberOfRecords) {
                break;
            }
        }
        //  Ceate the support arrays containing current block size and
        //  block count in the various files.
        int[] blockSizes = new int[numberOfFiles];
        int[] blockCounts = new int[numberOfFiles];
        blockSizes[0] = 0;
        blockCounts[0] = 0;
        for (int i = 1, j = indexOfNumberOfBlocks - 1;
             i < numberOfFiles;
             i++, j--) {
            blockSizes[i] = initialBlockSize;
            blockCounts[i] = F[j];
        }

        System.out.println("Initial block size: " + initialBlockSize);
        //  Create the files of blocks of sorted records.
        distributeSortedBlocks(temp, 0, initialBlockSize, blockCounts, rInfo);
        //  Set up the file readers for all the files.
        SortableRecordReader[] readers =
                new SortableRecordReader[numberOfFiles];
        for (int i = 0; i < numberOfFiles; i++) {
            readers[i] = rInfo.newSortableRecordReader(temp[i]);
        }
        while (true) {
            //  Check what work there is to do.  If there is, find out which
            //  is the empty file.
            int toIndex = -1;
            int numberOfNonEmptyFiles = 0;
            int indexOfNonEmptyFile = -1;
            for (int i = 0; i < numberOfFiles; i++) {
                if (blockCounts[i] == 0) {
                    toIndex = i;
                } else {
                    indexOfNonEmptyFile = i;
                    numberOfNonEmptyFiles++;
                }
            }
            //  Exit if everthing is done but close all the files
            //  and copy the result back before exiting.
            if (numberOfNonEmptyFiles <= 1) {
                for (int i = 0; i < numberOfFiles; i++) {
                    readers[i].close();
                }
                SortableRecordCopyFile.copy(temp[indexOfNonEmptyFile],
                        file,
                        rInfo);
                for (int i = 0; i < numberOfFiles; i++) {
                    temp[i].delete();
                }
                break;
            }
            //  Perform the next round of merging.
            readers[toIndex].close();
            SortableRecordWriter writer =
                    rInfo.newSortableRecordWriter(temp[toIndex]);
            merge(readers, writer, toIndex, blockSizes, blockCounts, rInfo);
            writer.close();
            readers[toIndex] = rInfo.newSortableRecordReader(temp[toIndex]);
        }
    }

    /**
     * Perform the initial dispersion of the data.
     */
    private static void distributeSortedBlocks(
            final File[] files,
            final int fromIndex,
            final int blockSize,
            final int[] blockCounts,
            final SortableRecordInformation rInfo)
            throws FileNotFoundException, IOException {
        //  Create a Reader for the original data and a set of Writers
        //  for the output files.
        SortableRecordReader reader =
                rInfo.newSortableRecordReader(files[fromIndex]);
        SortableRecordWriter[] writers =
                new SortableRecordWriter[files.length];
        for (int i = 0; i < files.length; i++) {
            writers[i] = i == fromIndex
                    ? null
                    : rInfo.newSortableRecordWriter(files[i]);
        }
        for (int i = 0; i < writers.length; i++) {
            if (i != fromIndex) {
                for (int j = 0; j < blockCounts[i]; j++) {
                    //  Pull in a few records, put them into an Array that is
                    //  where we are performing the internal sort that creates
                    //  us the sorted block.  Do the sort then write out the
                    //  block.
                    Array a = new Array();
                    for (int k = 0; k < blockSize; k++) {
                        SortableRecord r = reader.readSortableRecord();
                        if (r == null) {
                            break;
                        }
                        a.add(r);
                    }
                    QuicksortSequence.sort(a,
                            rInfo.getComparator(),
                            rInfo.getEqualToComparator());
                    for (Iterator iter = a.iterator();
                         !iter.atEnd();
                         iter.advance()) {
                        writers[i].writeSortableRecord((SortableRecord) iter.get());
                    }
                }
            }
        }
        //  Be tidy and close all the files.  Actually this is
        //  essential to ensure we get a flush.
        for (int i = 0; i < writers.length; i++) {
            if (i != fromIndex) {
                writers[i].close();
            }
        }
        reader.close();
    }

    /**
     * Undertake a round of merging.
     */
    private static void merge(final SortableRecordReader[] readers,
                              final SortableRecordWriter writer,
                              final int toIndex,
                              final int[] blockSizes,
                              final int[] blockCounts,
                              final SortableRecordInformation rInfo)
            throws FileNotFoundException, IOException {
        SortableRecord[] items = new SortableRecord[readers.length];
        int[] counts = new int[readers.length];
        int numberOfBlocksMerged = 0;
        while (true) {
            boolean allDone = false;
            for (int i = 0; i < readers.length; i++) {
                counts[i] = 0;
                if (i == toIndex) {
                    items[i] = null;
                } else {
                    readers[i].mark(64);
                    items[i] = readers[i].readSortableRecord();
                    if (items[i] == null) {
                        for (int j = 0; j < i; j++) {
                            if (j != toIndex) {
                                readers[j].reset();
                            }
                        }
                        allDone = true;
                        break;
                    } else {
                        counts[i] = 1;
                    }
                }
            }
            if (allDone) {
                break;
            }
            numberOfBlocksMerged++;
            while (true) {
                int i = findAppropriate(items, toIndex, rInfo.getComparator());
                if (i < 0) {
                    break;
                }
                writer.writeSortableRecord(items[i]);
                if (counts[i] < blockSizes[i]) {
                    items[i] = readers[i].readSortableRecord();
                    if (items[i] != null) {
                        counts[i]++;
                    }
                } else {
                    items[i] = null;
                }
            }
        }
        blockSizes[toIndex] = 0;
        for (int i = 0; i < readers.length; i++) {
            if (i != toIndex) {
                blockSizes[toIndex] += blockSizes[i];
            }
        }
        for (int i = 0; i < readers.length; i++) {
            blockCounts[i] -= numberOfBlocksMerged;
        }
        blockCounts[toIndex] = numberOfBlocksMerged;
    }

    /**
     * Determine which <CODE>SortableRecord</CODE> is the one to be output next.
     *
     * @param items   the array of <CODE>SortableRecords</CODE> from which to select the next according to the order
     *                relation defined by <CODE>c</CODE>.
     * @param toIndex the index into the array of the target.  The others are assumed to be sources.
     * @param c       the <CODE>Comparator</CODE> defining the required order relation on the
     *                <CODE>SortableRecord</CODE>s.
     * @return the index in the array of the item that should be chosen next.
     */
    private static int findAppropriate(final SortableRecord[] items,
                                       final int toIndex,
                                       final Comparator c) {
        //  Assume no output is to be done and then find the first
        //  non-empty entry.
        int index = -1;
        for (int i = 0; i < items.length; i++) {
            if (i != toIndex) {
                if (items[i] != null) {
                    index = i;
                    break;
                }
            }
        }
        //  If there were no non-empty entries then do nothing, we are
        //  finshied.  Otherwise...
        if (index >= 0) {
            //  ...do a linear search through the items to see which is the
            //  next one to select.
            SortableRecord value = items[index];
            for (int i = index + 1; i < items.length; i++) {
                if (i != toIndex) {
                    if (items[i] != null) {
                        if (c.relation(items[i], value)) {
                            index = i;
                            value = items[i];
                        }
                    }
                }
            }
        }
        return index;
    }
}