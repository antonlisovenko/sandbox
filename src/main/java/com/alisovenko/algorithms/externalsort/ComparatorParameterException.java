package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  A class to represent the exception of attempting to compare types
 *  of data that the <CODE>Comparator</CODE> was not designed for.
 *
 *  @see Comparator
 *  @version 1.0 1997.07.02
 *  @author Russel Winder
 */
public class ComparatorParameterException
        extends RuntimeException {
    /**
     *  The exception without a message.
     */
    public ComparatorParameterException() {
        super() ;
    }
    /**
     *  The exception with a message.
     */
    public ComparatorParameterException(final String s) {
        super(s) ;
    }
}