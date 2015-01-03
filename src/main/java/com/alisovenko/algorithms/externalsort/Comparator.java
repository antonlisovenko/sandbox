package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  An interface defining the necessary properties to be deemed
 *  to be a <CODE>Comparator</CODE>.
 *
 *  @version 1.0 1997.07.02
 *  @author Russel Winder
 */
public interface Comparator {
    /**
     *  The relation that this <CODE>Comparator</CODE> represents.
     */
    boolean relation(Object a, Object b) ;
}