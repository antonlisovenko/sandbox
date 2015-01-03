package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  All bidirectional iterators over containers in ADS conform to this
 *  interface.
 *
 *  @version 1.1 2001.04.23
 *  @author Russel Winder
 */
public interface BiDirectionalIterator extends ForwardIterator {
    /**
     *  Move back to the previous element in the container.
     */
    void retreat() ;
    /**
     *  Move backward to the nth item after the current one in this
     *  container.  If n is larger than the number of items prior to
     *  this in the container the iterator is positioned at the
     *  beginning.
     */
    void retreat(int n) ;
}