package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
import java.io.File ;
import java.io.IOException ;
/**
 *  An interface defining the concept of a record reader, the sort of
 *  thing capable of reading a <CODE>SortableRecord</CODE> from a
 *  file.
 *
 *  @see SortableRecord
 *  @see SortableRecordWriter
 *  @version 1.0 1997.05.19
 *  @author Russel Winder
 */
public interface SortableRecordReader {
    /**
     *  A <CODE>SortableRecord</CODE> must be readable.
     */
    SortableRecord readSortableRecord() throws IOException ;
    /**
     *  A <CODE>SortableRecordReader</CODE> must be closeable.
     */
    void close() throws IOException ;
    /**
     *  Mark an input stream.
     *
     *  @see java.io.BufferedReader#mark
     */
    void mark(int lookAheadLimit) throws IOException ;
    /**
     *  Move back to the mark.
     *
     *  @see java.io.BufferedReader#reset
     */
    void reset() throws IOException ;
}