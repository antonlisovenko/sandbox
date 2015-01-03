package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
public interface Iterator extends Cloneable {
    /**
     *  Deliver the value this iterator is currently referring to.
     */
    Object get() ;
    /**
     *  Move on to the next item in the container.
     */
    void advance() ;
    /**
     *  Move forward to the nth item after the current one in this
     *  container.  If n is larger than the number of items left in the
     *  container the iterator is positioned at the end.
     */
    void advance(int increment) ;
    /**
     *  Is this iterator positioned at the first element of the
     *  container.
     */
    boolean atBegin() ;
    /**
     *  Is this iterator positioned after the last item in the
     *  container.
     */
    boolean atEnd() ;
    /**
     *  Are two iterators equal, i.e. do two iterators refer to the
     *  same item in the same data structure.
     */
    boolean equals(Iterator i) ;
    /**
     *  Return a reference to the <CODE>Container</CODE> of this
     *  <CODE>Iterator</CODE>.
     */
    Container getContainer() ;
    /**
     *  Clone this <CODE>Iterator</CODE>.
     */
    Object clone() ;
}