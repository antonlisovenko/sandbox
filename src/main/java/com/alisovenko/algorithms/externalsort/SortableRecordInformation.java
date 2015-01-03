package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
import java.io.File ;
import java.io.IOException ;
/**
 *  An interface defining a factory object encapsulating
 *  <CODE>SortableRecord</CODE> management information.
 *
 *  <P> When dealing with files, there have to be records. This
 *  interface defines that which needs to be known in order to use any
 *  method object performing a file-based sort.  The user must supply a
 *  <CODE>SortableRecordInformation</CODE> conformant object in order
 *  to provide all the tools needed.
 *
 *  @see SortableRecord
 *  @see SortableRecordReader
 *  @see SortableRecordWriter
 *  @version 1.0 1997.05.19
 *  @author Russel Winder
 */
public interface SortableRecordInformation {
    /**
     *  Deliver up an order relation <CODE>Comparator</CODE> so that we
     *  can test the order of records.  Usually this will be an ordering
     *  defined by some key in the record.
     */
    Comparator getComparator() ;
    /**
     *  Deliver up a Comparator that can test for value equality of the
     *  sort key.
     */
    Comparator getEqualToComparator() ;
    /**
     *  We must be able to get a <CODE>BufferedReader</CODE> so that
     *  we can read records from a file.
     */
    SortableRecordReader newSortableRecordReader(File f)
            throws IOException ;
    /**
     *  We must be able to get a <CODE>BufferedWriter</CODE> so that
     *  we can write records to a file.
     */
    SortableRecordWriter newSortableRecordWriter(File f)
            throws IOException ;
}