package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
import java.io.File ;
import java.io.IOException ;
/**
 *  An interface defining the concept of a record writer, the sort of
 *  thing capable of writing a <CODE>SortableRecord</CODE> to a file.
 *
 *  @see SortableRecord
 *  @see SortableRecordReader
 *  @version 1.0 1997.05.19
 *  @author Russel Winder
 */
public interface SortableRecordWriter {
    /**
     *  A <CODE>SortableRecord</CODE> must be writeable.
     */
    void writeSortableRecord(SortableRecord r) throws IOException ;
    /**
     *  A <CODE>SortableRecordWriter</CODE> must be closeable.
     */
    void close() throws IOException ;
}