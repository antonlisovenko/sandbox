package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
import java.io.File ;
import java.io.FileNotFoundException ;
import java.io.IOException ;
/**
 *  A method object to copy a file containing <CODE>Record</CODE>s.
 *
 *  @see SortableRecord
 *  @see SortableRecordReader
 *  @see SortableRecordWriter
 *  @see SortableRecordInformation
 *  @see BalancedMultiwayMergeSort
 *  @see PolyphaseMergeSort
 *  @version 1.1 1999.11.27
 *  @author Russel Winder
 */
public final class SortableRecordCopyFile {
    /**
     *  The copying method.
     *
     *  @param from the file to read <CODE>SortableRecord</CODE>s from.
     *  @param to the file to write <CODE>SortableRecord</CODE>s from.
     *  @param rInfo the factory object (builder?) required to be able
     *  to construct the <CODE>Reader</CODE> and <CODE>Writer</CODE> of
     *  <CODE>SortableRecord</CODE>s.
     *  @return the number of records copied.
     */
    public static int copy(final File from,
                           final File to,
                           final SortableRecordInformation rInfo)
            throws FileNotFoundException, IOException {
        //  Set up the Reader and the Writer.
        SortableRecordReader source = rInfo.newSortableRecordReader(from);
        SortableRecordWriter target = rInfo.newSortableRecordWriter(to) ;
        //  Copy all the SortableRecords from the Reader to the Writer.
        int count = 0 ;
        while (true) {
            SortableRecord r = source.readSortableRecord() ;
            if (r == null)
                break ;
            target.writeSortableRecord(r) ;
            count++ ;
        }
        //  Close the files and ensure the flush.
        source.close() ;
        target.close() ;
        return count ;
    }
}