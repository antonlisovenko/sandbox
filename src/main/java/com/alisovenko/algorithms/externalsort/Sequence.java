package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  This interface is the general abstraction of a sequential
 *  container.
 *
 *  @version 1.1 1999.09.11
 *  @author Russel Winder
 */
public interface Sequence extends Container {
    /**
     *  Return the first item in the <CODE>Sequence</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     */
    Object first() ;
    /**
     *  Return the last element in the <CODE>Sequence</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     */
    Object last() ;
    /**
     *  Return the element at a given position in the
     *  <CODE>Sequence</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     *
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Sequence</CODE>.
     */
    Object get(int index) ;
    /**
     *  Assign a value to a position in the <CODE>Sequence</CODE>.
     *  This overwrites whatever value was there.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     *
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Sequence</CODE>.
     */
    void set(int index, Object o) ;
    /**
     *  Excise an element from the <CODE>Sequence</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     *
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Sequence</CODE>.
     */
    void remove(int index) ;
    /**
     *  Insert a new item in the <CODE>Sequence</CODE>.  Insertion
     *  happens before, i.e. the new item becomes the index'th item
     *  with the old index'th item becoming the index+1'th item, etc.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     *
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Sequence</CODE>.
     */
    void insert(int index, Object o) ;
    /**
     *  Find out the position of an <CODE>Object</CODE> in the
     *  <CODE>Sequence</CODE>.
     *
     *  @return the "index" of the sought item.  Return
     *  <CODE>-1</CODE> (which being negative is an illegal index) if
     *  the value <CODE>o</CODE> is not part of the
     *  <CODE>Sequence</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     */
    int indexOf(Object o) ;
    /**
     *  Deliver up an iterator positioned at the beginning of the
     *  <CODE>Sequence</CODE>.
     */
    ForwardIterator begin() ;
    /**
     *  Deliver up an iterator positioned at the end of the
     *  <CODE>Sequence</CODE>.
     */
    ForwardIterator end() ;
    /**
     *  The class that provides a synchronized interface for use in
     *  multi-threaded applications.
     */
    public static class Synchronized
            extends Container.Synchronized implements Sequence {
        /**
         *  The default constructor.
         */
        public Synchronized(final Sequence s) {
            super(s) ;
        }
        /**
         *  The constructor that supplies an object to synchronize on.
         */
        public Synchronized(final Sequence s, final Object o) {
            super(s, o) ;
        }
        /**
         *  Return the first item in the <CODE>Sequence</CODE>.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         */
        public final Object first() {
            synchronized (theLock) {
                return ((Sequence)theContainer).first() ;
            }
        }
        /**
         *  Return the last element in the <CODE>Sequence</CODE>.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         */
        public final Object last() {
            synchronized (theLock) {
                return ((Sequence)theContainer).last() ;
            }
        }
        /**
         *  Return the element at a given position in the
         *  <CODE>Sequence</CODE>.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         *
         *  @exception IndexOutOfBoundsException if the requested
         *  <CODE>index</CODE> is not valid due to the size of the
         *  <CODE>Sequence</CODE>.
         */
        public final Object get(final int index) {
            synchronized (theLock) {
                return ((Sequence)theContainer).get(index) ;
            }
        }
        /**
         *  Assign a value to a position in the <CODE>Sequence</CODE>.
         *  This overwrites whatever value was there.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         *
         *  @exception IndexOutOfBoundsException if the requested
         *  <CODE>index</CODE> is not valid due to the size of the
         *  <CODE>Sequence</CODE>.
         */
        public final void set(final int index, final Object o) {
            synchronized (theLock) {
                ((Sequence)theContainer).set(index, o) ;
            }
        }
        /**
         *  Excise an element from the <CODE>Sequence</CODE>.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         *
         *  @exception IndexOutOfBoundsException if the requested
         *  <CODE>index</CODE> is not valid due to the size of the
         *  <CODE>Sequence</CODE>.
         */
        public final void remove(final int index) {
            synchronized (theLock) {
                ((Sequence)theContainer).remove(index) ;
            }
        }
        /**
         *  Insert a new item in the <CODE>Sequence</CODE>.  Insertion
         *  happens before, i.e. the new item becomes the index'th item
         *  with the old index'th item becoming the index+1'th item, etc.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         *
         *  @exception IndexOutOfBoundsException if the requested
         *  <CODE>index</CODE> is not valid due to the size of the
         *  <CODE>Sequence</CODE>.
         */
        public final void insert(final int index, final Object o) {
            synchronized (theLock) {
                ((Sequence)theContainer).insert(index, o) ;
            }
        }
        /**
         *  Find out the position of an <CODE>Object</CODE> in the
         *  <CODE>Sequence</CODE>.  Uses linear search.
         *
         *  @return the "index" of the sought item.  Return
         *  <CODE>-1</CODE> (which being negative is an illegal index) if
         *  the value <CODE>o</CODE> is not part of the
         *  <CODE>Sequence</CODE>.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         */
        public final int indexOf(final Object o) {
            synchronized (theLock) {
                return ((Sequence)theContainer).indexOf(o) ;
            }
        }
        /**
         *  Deliver up an iterator positioned at the beginning of the
         *  <CODE>Sequence</CODE>.
         */
        public final ForwardIterator begin() {
            synchronized (theLock) {
                return ((Sequence)theContainer).begin() ;
            }
        }
        /**
         *  Deliver up an iterator positioned at the end of the
         *  <CODE>Sequence</CODE>.
         */
        public final ForwardIterator end() {
            synchronized (theLock) {
                return ((Sequence)theContainer).end() ;
            }
        }
    }
}