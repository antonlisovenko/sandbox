package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
public class Array extends AbstractSequence {
    /**
     *  The array that holds the data.
     */
    private Object[] datum ;
    /**
     *  The value of the initial size.  The smallest size of the
     *  underlying array for creating new <CODE>Array</CODE>s.
     */
    private final int initialSize ;
    /**
     *  The value of the size increment.  Whenever we need more array
     *  space we increment the size of the array by this amount.
     */
    private final int incrementSize ;
    /**
     *  This is the current size of the filled portion of the array.
     *  Any space in the array above this is not actually there as far
     *  as the application is concerned.
     */
    private int theSize = 0 ;
    /**
     *  The default constructor.  Gives an <CODE>Array</CODE> with
     *  default initial size and increment.
     */
    public Array() {
        this(10, 10) ;
    }
    /**
     *  The constructor which specifies the initial size of the
     *  <CODE>Array</CODE> using the default increment size.
     */
    public Array(final int initialSizeValue) {
        this(initialSizeValue, 10) ;
    }
    /**
     *  The constructor that specifies both the initial size and the
     *  increment size.
     */
    public Array(final int initialSizeValue,
                 final int incrementSizeValue) {
        initialSize = initialSizeValue ;
        incrementSize = incrementSizeValue ;
        datum = new Object [initialSizeValue] ;
    }
    /**
     *  Construct an <CODE>Array</CODE> by copying the
     *  <CODE>Sequence</CODE>.
     */
    public Array(final Sequence s) {
        this(s.size(), 10) ;
        copy(s, 0, s.size()) ;
    }
    /**
     *  Construct an <CODE>Array</CODE> by copying the sub-sequence
     *  defined by two indexes into a <CODE>Sequence</CODE>s.
     */
    public Array(final Sequence s, final int b, final int e) {
        this(e-b+1, 10) ;
        copy(s, b, e) ;
    }
    /**
     *  Construct an <CODE>Array</CODE> by copying the sequence defined
     *  by two <CODE>Iterator</CODE>s.
     *
     *  @exception InvalidIteratorException if the two
     *  <CODE>Iterator</CODE>s are not working the same
     *  <CODE>SLList</CODE>.
     */
    public Array(final com.alisovenko.algorithms.externalsort.Iterator b, final com.alisovenko.algorithms.externalsort.Iterator e) {
        this(10, 10) ;
        copy(b, e) ;
    }
    /**
     *  Return the element at a given position in the
     *  <CODE>Array</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Array</CODE>.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Array</CODE>.
     */
    public final Object get(final int index) {
        sanityCheck(index) ;
        return datum[index] ;
    }
    /**
     *  Assign a value to a position in the <CODE>Array</CODE>.
     *  This overwrites whatever value was there.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Array</CODE> and the index is not 0.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Array</CODE>.
     */
    public final void set(final int index, final Object o) {
        try {
            sanityCheck(index) ;
        }
        catch (AccessEmptyContainerException e) {
            if (index == 0) {
                add(o) ;
                return ;
            } else {
                throw e ;
            }
        }
        catch (IndexOutOfBoundsException f) {
            if (index == 0) {
                add(o) ;
                return ;
            } else {
                throw f ;
            }
        }
        datum[index] = o ;
    }
    /**
     *  Excise an element from the <CODE>Array</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Array</CODE>.
     */
    public final void remove(final int index) {
        sanityCheck(index) ;
        for (int i = index ; i < theSize - 1 ; ++i) {
            datum[i] = datum[i+1] ;
        }
        --theSize ;
    }
    /**
     *  Insert a new item in the <CODE>Array</CODE>.  Insertion
     *  happens before, i.e. the new item becomes the index'th item
     *  with the old index'th item becoming the index+1'th item, etc.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE> and index is not 0.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>Array</CODE>.
     */
    public final void insert(final int index, final Object o) {
        try {
            sanityCheck(index) ;
        }
        catch (AccessEmptyContainerException e) {
            if (index == 0) {
                add(o) ;
                return ;
            } else {
                throw e ;
            }
        }
        if (theSize == datum.length) {
            //  The array must be extended.  Create a new larger one.  Copy
            //  up to the insertion point insert the new element, copy the
            //  rest of the array.
            Object[] newDatum = new Object [datum.length + incrementSize] ;
            System.arraycopy(datum, 0, newDatum, 0, index) ;
            newDatum[index] = o ;
            System.arraycopy(datum, index,
                    newDatum, index + 1,
                    datum.length - index) ;
            datum = newDatum ;
        } else {
            for (int i = theSize ; i > index ; --i) {
                datum[i] = datum[i-1] ;
            }
            datum[index] = o ;
        }
        ++theSize ;
    }
    /**
     *  Remove the entire contents of the <CODE>Array</CODE>.  This does
     *  not affect the size of the underlying array, it only removes the
     *  elements.
     */
    public final void makeEmpty() {
        theSize = 0 ;
    }
    /**
     *  Determine whether the <CODE>Array</CODE> is an empty one.
     */
    public final boolean isEmpty() {
        return theSize == 0 ;
    }
    /**
     *  Return the number of items in the <CODE>Array</CODE>.
     */
    public final int size() {
        return theSize ;
    }
    /**
     *  Append an element to the end of the <CODE>Array</CODE>.
     */
    public final void add(final Object o) {
        if (theSize == datum.length) {
            Object[] newDatum = new Object [datum.length + incrementSize] ;
            System.arraycopy(datum, 0, newDatum, 0, datum.length) ;
            datum = newDatum ;
        }
        datum[theSize++] = o ;
    }
    /**
     *  Remove an element of a given value from the
     *  <CODE>Array</CODE> if it is in the <CODE>Array</CODE>.
     *
     *  @return whether the item was actually in the <CODE>Array</CODE>.
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>Sequence</CODE>.
     */
    public final boolean remove(final Object o) {
        sanityCheck() ;
        int i = indexOf(o) ;
        if (i < 0)
            return false ;
        remove(i) ;
        return true ;
    }
    /**
     *  Deliver up a complete shallow copy of the <CODE>Array</CODE>.
     *  NB <CODE>CloneNotSupportedException</CODE> is caught in
     *  <CODE>AbstractContainer</CODE> so we don't need to worry about
     *  it here.
     */
    public final Object clone() {
        return super.clone() ;
    }
    /**
     *  Deliver up an iterator positioned at the beginning of the
     *  <CODE>Array</CODE>.
     */
    public final ForwardIterator begin() {
        return new Iterator () ;
    }
    /**
     *  Deliver up an iterator positioned at the end of the
     *  <CODE>Array</CODE>.
     */
    public final ForwardIterator end() {
        Iterator i = new Iterator () ;
        i.position = size() - 1 ;
        return i ;
    }
    /**
     *  The iterator over <CODE>Array</CODE>s.  This is an inner class
     *  (as opposed to being a nested top-level class) so that it
     *  automatically has a reference to it's parent object, i.e. the
     *  object it is an iterator for.
     */
    public class Iterator implements RandomAccessIterator {
        /**
         *  The current location of the iterator.  Since indexing is
         *  efficient for this data structure, we keep only the current
         *  index position.
         */
        private int position = 0 ;
        /**
         *  Deliver the value we are currently referring to.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         *  @exception InvalidIteratorException if the iterator is not
         *  referring to a valid element.
         */
        public final Object get() {
            sanityCheck() ;
            return Array.this.datum[position] ;
        }
        /**
         *  Amend the value of the element we are currently referring to.
         *
         *  @exception AccessEmptyContainerException if there are no
         *  items in the <CODE>Sequence</CODE>.
         *  @exception InvalidIteratorException if the iterator is not
         *  referring to a valid element.
         */
        public final void set(final Object o) {
            sanityCheck() ;
            Array.this.datum[position] = o ;
        }
        /**
         *  Move forward one element in the container.
         */
        public final void advance() {
            if (! atEnd()) {
                ++position ;
            }
        }
        /**
         *  Move forward <CODE>increment</CODE> elements in the container.
         *  <CODE>increment</CODE> may be positive or negative.  If the
         *  increment attempts to move the iterator outside the boundaries
         *  of the <CODE>Array</CODE> then it will be positioned at the
         *  appropriate extremity.
         */
        public final void advance(final int increment) {
            if (!atEnd()) {
                position += increment ;
                if (position > theSize) {
                    position = theSize ;
                }
                if (position < 0) {
                    position = 0 ;
                }
            }
        }
        /**
         *  Move backward one element in the container.
         */
        public final void retreat() {
            if (atBegin()) {
                --position ;
            }
        }
        /**
         *  Move backward <CODE>increment</CODE> elements in the
         *  container.  <CODE>increment</CODE> may be positive or
         *  negative.  If the increment attempts to move the iterator
         *  outside the boundaries of the <CODE>Array</CODE> then it will
         *  be positioned at the appropriate extremity.
         */
        public final void retreat(final int increment) {
            if (atBegin()) {
                position -= increment ;
                if (position > theSize) {
                    position = theSize ;
                }
                if (position < 0) {
                    position = 0 ;
                }
            }
        }
        /**
         *  Return the index od the iterator.
         */
        public final int index() {
            return position ;
        }
        /**
         *  Calculate the distance between this iterator and the parameter iterator.
         *
         *  @exceptions InvalidIteratorException if this iterator and the
         *  parameter iterator are not working the same
         *  <CODE>Container</CODE>.
         */
        public final int distance(final ForwardIterator i) {
            if (getContainer() != i.getContainer())
                throw new InvalidIteratorException () ;
            return position - ((Iterator)i).position ;
        }
        /**
         *  Have we reached the beginning of the iteration?
         */
        public final boolean atBegin() {
            return position == 0 ;
        }
        /**
         *  Have we reached the end of the iteration?
         */
        public final boolean atEnd() {
            return position == theSize ;
        }
        /**
         *  Are two iterators equal, i.e. do two iterators refer to the
         *  same item in the same data structure.
         */
        public boolean equals(final com.alisovenko.algorithms.externalsort.Iterator i) {
            if (! (i instanceof Iterator))
                return false ;
            return (getContainer() == i.getContainer()) &&
                    (position == ((Iterator)i).position) ;
        }
        /**
         *  Return a reference to the <CODE>Container</CODE> of this
         *  <CODE>Iterator</CODE>.
         */
        public final Container getContainer() {
            return Array.this ;
        }
        /**
         *  Clone this <CODE>Iterator</CODE>.
         */
        public final Object clone() {
            try {
                Iterator i = (Iterator)super.clone() ;
                i.position = position ;
                return i ;
            } catch (CloneNotSupportedException e) {
                throw new InternalError ()  ;
            }
        }
        /**
         *  The distance between two iterators.
         *
         *  The two iterators must clearly be working the same
         *  <CODE>Array</CODE>.
         */
        public final int separation(final RandomAccessIterator i) {
            if (!(i instanceof Iterator) || (getContainer() != i.getContainer()))
                throw new RuntimeException ("Incompatible iterators.") ;
            return position - ((Iterator)i).position ;
        }
        /**
         *  Check for the correctness of the state.  This checks both that
         *  the <CODE>Array</CODE> actually has elements and that the
         *  proferred index is a legal one.
         */
        private final void sanityCheck() {
            if (Array.this.theSize == 0)
                throw new AccessEmptyContainerException () ;
            if ((position < 0) || (position >= Array.this.size()))
                throw new InvalidIteratorException () ;
        }
    }
}