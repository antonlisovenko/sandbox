package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  An abstract sequence to act as a superclass for all the concrete
 *  sequences.  Provides all the default implementations that are
 *  representation independent.
 *
 *  @version 1.1 2001.04.23
 *  @author Russel Winder
 */
public abstract class AbstractSequence
        extends AbstractContainer implements Sequence {
    /**
     *  Return the first item in the <CODE>Sequence</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     */
    public Object first() {
        return get(0) ;
    }
    /**
     *  Return the last element in the <CODE>Sequence</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     */
    public Object last() {
        return get(size()-1) ;
    }
    /**
     *  Return the element at a given position in the
     *  <CODE>Sequence</CODE>.
     */
    public abstract Object get(final int index) ;
    /**
     *  Assign a value to a position in the <CODE>Sequence</CODE>.
     *  This overwrites whatever value was there.
     */
    public abstract void set(final int index, final Object o) ;
    /**
     *  Excise an element from the <CODE>Sequence</CODE>.
     */
    public abstract void remove(final int index) ;
    /**
     *  Insert a new item in the <CODE>Sequence</CODE>.  Insertion
     *  happens before, i.e. the new item becomes the index'th item
     *  with the old index'th item becoming the index+1'th item, etc.
     */
    public abstract void insert(final int index, final Object o) ;
    /**
     *  Find out the position of an <CODE>Object</CODE> in the
     *  <CODE>Sequence</CODE>.  Uses linear search.
     */
    public int indexOf(final Object o) {
        sanityCheck() ;
        int count = 0 ;
        for (Iterator i = iterator() ;
             ! i.atEnd() ;
             i.advance(), ++count) {
            if (i.get().equals(o))
                return count ;
        }
        return -1 ;
    }
    /**
     *  Compare this <CODE>Sequence</CODE> with another
     *  <CODE>Sequence</CODE> and determine whether they represent the
     *  same value, i.e. have the same item values in the same order
     *  in the <CODE>Sequence</CODE>.
     */
    public boolean equals(final Object o) {
        if (! (o instanceof Sequence))
            return false ;
        return super.equals(o) ;
    }
    /**
     *  Determine whether we have the value represented by the
     *  parameter <CODE>Object</CODE> in us.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     */
    public boolean contains(final Object o) {
        sanityCheck() ;
        return indexOf(o) >= 0 ;
    }
    /**
     *  Deliver up an input iterator over the
     *  <CODE>Container</CODE>.
     */
    public Iterator iterator() {
        return begin() ;
    }
    /**
     *  Copy from a sub-sequence defined by indexes into a
     *  <CODE>Sequence</CODE> into the current <CODE>Sequence</CODE> The
     *  copy is shallow in that we do not clone the data but just copy
     *  references to the data.
     *
     *  <P>Parameter <CODE>b</CODE> must be less than parameter
     *  <CODE>c</CODE>. If it is not then the two values will be
     *  swapped.</P>
     */
    public final void copy(final Sequence s, int b, int e) {
        //  Don't actually use indexing, use an iterator since indexing
        //  into a list is horrendously inefficient whilst arrays can
        //  handle iterators and indexing more or less equally
        //  efficiently.
        if (b > e) {
            int t = b ;
            b = e ;
            e = t ;
        }
        Iterator i = s.iterator() ;
        i.advance(b) ;
        for (int j = b ; j < e ; ++j, i.advance()) {
            add(i.get()) ;
        }
    }
    /**
     *  Copy from the sequence defined by two <CODE>Iterator</CODE>s.
     *  The copy is shallow in that we do not clone the data but just
     *  copy references to the data.
     *
     *  @exception InvalidIteratorException if the two
     *  <CODE>Iterator</CODE>s are not working the same
     *  <CODE>SLList</CODE>.
     */
    public final void copy(final Iterator b, final Iterator e) {
        //  b and e must be working the same Container.
        if (b.getContainer() != e.getContainer())
            throw new InvalidIteratorException () ;
        //  the iterator e should be after b in the Container.  We can
        //  only check this for ForwardIterators.  For input iterators we
        //  cannot make any checks.
        if ((b instanceof ForwardIterator) &&
                (e instanceof ForwardIterator) &&
                (((ForwardIterator)b).distance((ForwardIterator)e) < 0))
            throw new InvalidIteratorOperationException () ;
        //  Can now actually do the copying.
        for (Iterator i = (Iterator)b.clone() ;
             ! i.atEnd() && ! i.equals(e) ;
             i.advance()) {
            add(i.get()) ;
        }
    }
    /**
     *  Check for the correctness of the state.  This just check that
     *  the <CODE>Sequence</CODE> actually has elements.
     */
    protected void sanityCheck() {
        if (size() == 0)
            throw new AccessEmptyContainerException () ;
    }
    /**
     *  Check for the correctness of the state.  This checks both that
     *  the <CODE>Sequence</CODE> actually has elements and that the
     *  proferred index is a legal one.
     */
    protected void sanityCheck(final int index) {
        if (size() == 0)
            throw new AccessEmptyContainerException () ;
        if ((index < 0) || (index >= size()))
            throw new IndexOutOfBoundsException () ;
    }
}