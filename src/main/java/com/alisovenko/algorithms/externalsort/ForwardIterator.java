package com.alisovenko.algorithms.externalsort;

/**
 *  All forward iterators over containers in ADS conform to
 *  this interface.
 *
 *  @version 1.1 2001.04.23
 *  @author Russel Winder
 */
public interface ForwardIterator extends Iterator {
    /**
     *  Set the value at the current position.
     */
    void set(Object o) ;
    /**
     *  Calculate the distance between the current iterator and the
     *  iterator that is the parameter.
     */
    int distance(ForwardIterator i) ;
}