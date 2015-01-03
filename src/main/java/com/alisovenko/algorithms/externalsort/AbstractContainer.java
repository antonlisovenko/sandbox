package com.alisovenko.algorithms.externalsort;

/**
 *  An abstract <CODE>Container</CODE>.
 *
 *  @version 1.2 2001.04.07
 *  @author Russel Winder
 */
public abstract class AbstractContainer implements Container {
    /**
     *  Remove the entire contents of the <CODE>Container</CODE>.
     */
    public abstract void makeEmpty() ;
    /**
     *  Determine whether the <CODE>Container</CODE> is an empty one.
     */
    public boolean isEmpty() {
        return size() <= 0 ;
    }
    /**
     *  Return the number of items in the <CODE>Container</CODE>.
     */
    public int size() {
        int count = 0 ;
        for (Iterator i = iterator() ; !i.atEnd() ; i.advance(), ++count)
            ;
        return count ;
    } ;
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
     *  they contain exactly the same elements.
     */
    public boolean equals(Object o) {
        //  We are always equal to ourself!
        if (this == o)
            return true ;
        //  If o is null or it is not some form of Container or if it is a
        //  Container but it's size is different from ourselves then it
        //  cannot be equal to us.  Rely on left to right evaluation of
        //  the or expression.
        if ((o == null) ||
                ! (o instanceof Container) ||
                (size() != ((Container)o).size()))
            return false ;
        //  We know that we are both Containers of the same size, so check
        //  whether our elements are all the same.  By iterating over
        //  them.
        Iterator i1 = iterator() ;
        Iterator i2 = ((Container)o).iterator() ;
        for ( ; !i1.atEnd() && !i2.atEnd() ; i1.advance(), i2.advance()) {
            Object o1 = i1.get() ;
            Object o2 = i2.get() ;
            if ((o1 == null) || (o2 == null) || !o1.equals(o2))
                return false ;
        }
        return i1.atEnd() && i2.atEnd() ;
    } ;
    /**
     *  Determine whether we have the value represented by the
     *  parameter <CODE>Object</CODE> in us.  Uses linear search.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Container</CODE>.
     */
    public boolean contains(Object o) {
        if (o == null)
            return false ;
        for (Iterator i = iterator() ; !i.atEnd() ; i.advance()) {
            if (i.get().equals(o))
                return true ;
        }
        return false ;
    } ;
    /**
     *  Add an element to the <CODE>Container</CODE>.
     */
    public abstract void add(Object o) ;
    /**
     *  Remove an element of a given value from the
     *  <CODE>Container</CODE> if it is in the <CODE>Container</CODE>.
     *
     *  @return whether the item was actually in the
     *  <CODE>Container</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Container</CODE>.
     */
    public abstract boolean remove(Object o) ;
    /**
     *  Deliver up a complete shallow copy of the
     *  <CODE>Container</CODE>.
     */
    public Object clone() {
        try {
            return super.clone() ;
        } catch (CloneNotSupportedException e) {
            throw new InternalError () ;
        }
    }
    /**
     *  Deliver up a <CODE>String</CODE> representation of the
     *  <CODE>Container</CODE>.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer () ;
        sb.append("[ ") ;
        for (Iterator i = iterator() ; !i.atEnd() ; i.advance()) {
            sb.append(i.get().toString() + ' ') ;
        }
        sb.append(']') ;
        return sb.toString() ;
    }
    /**
     *  Deliver up an iterator over all the items in the
     *  <CODE>Container</CODE>.
     */
    public abstract Iterator iterator() ;
}