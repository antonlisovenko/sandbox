package com.alisovenko.algorithms.externalsort;

/**
 *  All random access iterators over containers in ADS conform to this
 *  interface.
 *
 *  @version 1.1 2001.04.23
 *  @author Russel Winder
 */
public interface RandomAccessIterator extends BiDirectionalIterator {
    /**
     *  Deliver up the index of the current position.
     */
    int index() ;
}