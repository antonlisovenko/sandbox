package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  An interface defining the necessary properties to be deemed to be
 *  a <CODE>Container</CODE>.
 *
 *  @version 1.1 1999.08.23
 *  @author Russel Winder
 */
public interface Container extends Cloneable {
    /**
     *  Remove the entire contents of the <CODE>Container</CODE>.
     */
    void makeEmpty() ;
    /**
     *  Determine whether the <CODE>Container</CODE> is an empty one.
     */
    boolean isEmpty() ;
    /**
     *  Return the number of items in the <CODE>Container</CODE>.
     */
    int size() ;
    /**
     *  Compare this <CODE>Container</CODE> with another and determine
     *  whether they represent the same value, i.e. have the same item
     *  values in the <CODE>Container</CODE>.
     *
     *  <P> <CODE>equals</CODE> is made into semantic equality
     *  rather than an object identity equality.  Equality for
     *  <CODE>Object</CODE>s is defined to be that the two
     *  <CODE>Object</CODE>s are the same <CODE>Object</CODE>.  For
     *  <CODE>Container</CODE>s, we want to define equality to be that
     *  they contain exactly the same values.
     */
    boolean equals(Object o) ;
    /**
     *  Determine whether the value represented by the
     *  parameter <CODE>Object</CODE> is in the <CODE>Container</CODE>.
     */
    boolean contains(Object o) ;
    /**
     *  Add an element to the <CODE>Container</CODE>.
     */
    void add(Object o) ;
    /**
     *  Remove an element of a given value from the
     *  <CODE>Container</CODE> if it is in the <CODE>Container</CODE>.
     *
     *  @return whether the item was actually in the
     *  <CODE>Container</CODE>.
     */
    boolean remove(Object o) ;
    /**
     *  Deliver up a complete shallow copy of the
     *  <CODE>Container</CODE>.
     */
    Object clone() ;
    /**
     *  Deliver up a <CODE>String</CODE> representation of the
     *  <CODE>Container</CODE>.
     */
    String toString() ;
    /**
     *  Deliver up an input iterator over the
     *  <CODE>Container</CODE>.
     */
    Iterator iterator() ;
    /**
     *  Containers can have synchronized forms provided by this class.
     */
    public static class Synchronized implements Container {
        /**
         *  The <CODE>Container</CODE> that holds the data.
         */
        protected final Container theContainer ;
        /**
         *  The <CODE>Object</CODE> on which to do all locking.
         */
        protected final Object theLock ;
        /**
         *  The default constructor.  Gives an <CODE>Container</CODE> with
         *  default initial size and increment.
         */
        public Synchronized(final Container c) {
            theContainer = c ;
            theLock = this ;
        }
        /**
         *  The constructor that supplies an object to synchronize on.
         */
        public Synchronized(final Container c, final Object o) {
            theContainer = c ;
            theLock = o ;
        }
        /**
         *  Remove the entire contents of the <CODE>Container</CODE>.
         */
        public final void makeEmpty() {
            synchronized (theLock) { theContainer.makeEmpty() ; }
        }
        /**
         *  Determine whether the <CODE>Container</CODE> is an empty one.
         */
        public final boolean isEmpty() {
            synchronized (theLock) { return theContainer.isEmpty() ; }
        } ;
        /**
         *  Return the number of items in the <CODE>Container</CODE>.
         */
        public final int size() {
            synchronized (theLock) { return theContainer.size() ; }
        } ;
        /**
         *  Compare this <CODE>Container</CODE> with another and determine
         *  whether they represent the same value, i.e. have the same item
         *  values in the <CODE>Container</CODE>.
         */
        public final boolean equals(final Object o) {
            synchronized (theLock) { return theContainer.equals(o) ; }
        } ;
        /**
         *  Determine whether the value represented by the parameter
         *  <CODE>Object</CODE> is in the <CODE>Container</CODE>.
         */
        public final boolean contains(final Object o) {
            synchronized (theLock) { return theContainer.contains(o) ; }
        } ;
        /**
         *  Add an element to the <CODE>Container</CODE>.
         */
        public final void add(final Object o) {
            synchronized (theLock) { theContainer.add(o) ; }
        } ;
        /**
         *  Remove an element of a given value from the
         *  <CODE>Container</CODE> if it is in the <CODE>Container</CODE>.
         *
         *  @return whether the item was actually in the
         *  <CODE>Container</CODE>.
         */
        public final boolean remove(final Object o) {
            synchronized (theLock) { return theContainer.remove(o) ; }
        } ;
        /**
         *  Deliver up a complete shallow copy of the
         *  <CODE>Container</CODE>.
         */
        public final Object clone() {
            synchronized (theLock) { return theContainer.clone() ; }
        } ;
        /**
         *  Deliver up a <CODE>String</CODE> representation the
         *  <CODE>Container</CODE>.
         */
        public final String toString() {
            synchronized (theLock) { return theContainer.toString() ; }
        } ;
        /**
         *  Deliver up an iterator over all the items in the
         *  <CODE>Container</CODE>.
         */
        public final Iterator iterator() {
            return theContainer.iterator() ;
        }
    }
}