package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  The exception used in the ADS package when an iterator is used
 *  when invalid.  Invalid here means either initialized to an empty
 *  container or run to the end of its iteration.
 *
 *  @version 1.0 1999.09.14
 *  @author Russel Winder
 */
public class InvalidIteratorException extends RuntimeException {
    /**
     *  The exception without a message.
     */
    public InvalidIteratorException() {
        super() ;
    }
    /**
     *  The exception with a message.
     */
    public InvalidIteratorException(final String s) {
        super(s) ;
    }
}