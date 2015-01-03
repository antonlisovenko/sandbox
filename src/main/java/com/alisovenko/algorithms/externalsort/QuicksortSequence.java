package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
public class QuicksortSequence {
    /**
     *  The sort operation on <CODE>Array</CODE>s.
     *
     *  @param a the <CODE>Array</CODE> to be sorted.
     *  @param cr the <CODE>Comparator</CODE> used to compare the
     *  <CODE>Object</CODE>s during the sort process.
     *  @param ce the <CODE>Comparator</CODE> defining value equality.
     */
    public static void sort(final Array a,
                            final Comparator cr,
                            final Comparator ce) {
        sort(a, 0, a.size()-1, cr) ;
    }
    /**
     *  The sort operation on <CODE>DLList</CODE>s.
     *
     *  @param d the <CODE>DLList</CODE> to be sorted.
     *  @param cr the <CODE>Comparator</CODE> used to compare the
     *  <CODE>Object</CODE>s during the sort process.
     *  @param ce the <CODE>Comparator</CODE> defining value equality.
     */
    public static void sort(final DLList d,
                            final Comparator cr,
                            final Comparator ce) {
        sort((BiDirectionalIterator)d.begin(),
                (BiDirectionalIterator)d.end(),
                cr,
                ce) ;
    }
    /**
     *  The sort operation for handling a slice of an
     *  <CODE>Array</CODE>.
     *
     *  @param a the <CODE>Array</CODE> out of which to take a slice.
     *  @param lower the lower bound of this slice.
     *  @param upper the upper bound of this slice.
     *  @param c the <CODE>Comparator</CODE> to be used to define the
     *  order.
     */
    private static void sort(final Array a,
                             final int lower,
                             final int upper,
                             final Comparator c) {
        int sliceLength = upper-lower+1 ;
        if (sliceLength > 1) {
            if (sliceLength == 2) {
                if (c.relation(a.get(upper), a.get(lower))) {
                    swap (a, lower, upper) ;
                }
            } else {
                //  This partition implementation does not guarantee that the
                //  split point contains the pivot value so we cannot assume
                //  that the pivot is between the two slices.
                int pivotIndex = partition(a, lower, upper, c) ;
                sort(a, lower, pivotIndex, c) ;
                sort(a, pivotIndex+1, upper, c) ;
            }
        }
    }
    /**
     *  The sort operation for <CODE>BiDirectionalIterator</CODE>s.
     *
     *  @param b iterator positioned at the bottom of the slice.
     *  @param t iterator positioned at the top of the slice.
     *  @param cr the <CODE>Comparator</CODE> to be used to define the
     *  order.
     *  @param ce the <CODE>Comparator</CODE> defining value equality.
     */
    private static void sort(final BiDirectionalIterator b,
                             final BiDirectionalIterator t,
                             final Comparator cr,
                             final Comparator ce) {
        if (! b.equals(t)) {
            BiDirectionalIterator pivot = partition(b, t, cr, ce) ;
            if (! pivot.equals(b)) {
                pivot.retreat() ;
                sort(b, pivot, cr, ce) ;
                pivot.advance() ;
            }
            if (! pivot.equals(t)) {
                pivot.advance() ;
                sort(pivot, t, cr, ce) ;
            }
        }
    }
    /**
     *  Given the array and two indices, swap the two items in the
     *  array.
     */
    private static void swap(final Array a,
                             final int x,
                             final int y) {
        Object temp = a.get(x) ;
        a.set(x, a.get(y)) ;
        a.set(y, temp) ;
    }
    /**
     *  Given two iterators swap the two items.
     */
    private static void swap(final BiDirectionalIterator a,
                             final BiDirectionalIterator b) {
        Object temp = a.get() ;
        a.set(b.get()) ;
        b.set(temp) ;
    }
    /**
     *  Partition an <CODE>Array</CODE> in two using the pivot value
     *  that is at the centre of the array being partitioned.
     *
     *  <P> This partition implementation based on that in Winder, R
     *  (1993) "Developing C++ Software", Wiley, p.395.  NB. This
     *  implementation (unlike most others) does not guarantee that
     *  the split point contains the pivot value.  Unlike other
     *  implementations, it requires only < (or >) relation and not
     *  both < and <= (or > and >=).  Also, it seems easier to program
     *  and to comprehend.
     *
     *  @param a the <CODE>Array</CODE> out of which to take a slice.
     *  @param lower the lower bound of this slice.
     *  @param upper the upper bound of this slice.
     *  @param c the <CODE>Comparator</CODE> to be used to define the
     *  order.
     */
    private static int partition(final Array a,
                                 int lower,
                                 int upper,
                                 final Comparator c) {
        Object pivotValue = a.get((upper+lower+1)/2) ;
        while (lower <= upper) {
            while (c.relation(a.get(lower), pivotValue)) {
                lower++ ;
            }
            while (c.relation(pivotValue, a.get(upper))) {
                upper-- ;
            }
            if (lower <= upper) {
                if (lower < upper) {
                    swap(a, lower, upper) ;
                }
                lower++ ;
                upper-- ;
            }
        }
        return upper ;
    }
    /**
     *  Partition an array in two using the pivot value that is at the
     *  beginning of the array being partitioned.
     *
     *  @param b iterator positioned at the bottom of the slice.
     *  @param t iterator positioned at the top of the slice.
     *  @param cr the <CODE>Comparator</CODE> to be used to define the
     *  order.
     *  @param ce the <CODE>Comparator</CODE> defining value equality.
     */
    private static BiDirectionalIterator partition(
            final BiDirectionalIterator lower,
            final BiDirectionalIterator upper,
            final Comparator cr,
            final Comparator ce) {
        //  **MUST** treat the parameters as immutable so make clones.
        //  This has the advantage of preparing the object that we will
        //  return.  For some bizarre reason only Iterators and not
        //  subinterfaces can be cloned.  Is this a bug in Java or just in
        //  the interface inheritance hierarchy?
        final BiDirectionalIterator b =
                (BiDirectionalIterator)((Iterator)lower).clone() ;
        final BiDirectionalIterator t =
                (BiDirectionalIterator)((Iterator)upper).clone() ;
        final BiDirectionalIterator pivot =
                (BiDirectionalIterator)((Iterator)b).clone() ;
        final Object pivotValue = pivot.get() ;
        while (true) {
            while ((cr.relation(b.get(), pivotValue) ||
                    ce.relation(b.get(), pivotValue)) &&
                    ! b.equals(t)) {
                b.advance() ;
            }
            while (cr.relation(pivotValue, t.get()) && !t.equals(b)) {
                t.retreat() ;
            }
            if (! b.equals(t)) {
                swap(b, t) ;
            } else {
                while (cr.relation(pivotValue, t.get())) {
                    t.retreat() ;
                }
                break ;
            }
        }
        swap(pivot, t) ;
        return t ;
    }
}