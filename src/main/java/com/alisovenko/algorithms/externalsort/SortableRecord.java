package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
public interface SortableRecord {
    /**
     *  A <CODE>SortableRecord</CODE> must have a key that the records
     *  can be ordered on.
     */
    int key() ;
    /**
     *  A <CODE>SortableRecord</CODE> must have a printed form.
     */
    String toString() ;
}