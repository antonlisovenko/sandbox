package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  The exception used in the ADS package when one of the container
 *  types is asked to undertake an activity but is devoid of content.
 *
 *  @version 1.0 2.7.1997
 *  @author Russel Winder
 */
public class AccessEmptyContainerException extends RuntimeException {
    /**
     *  The exception without a message.
     */
    public AccessEmptyContainerException() {
        super() ;
    }
    /**
     *  The exception with a message.
     */
    public AccessEmptyContainerException(final String s) {
        super(s) ;
    }
}