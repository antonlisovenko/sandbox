package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  A class to represent the greater than order relation between
 *  <CODE>Integer</CODE> types.
 *
 *  @see Integer
 *  @version 1.0 1997.07.02
 *  @author Russel Winder
 */
public class IntegerGreaterThanComparator implements Comparator {
    /**
     *  The relation that this <CODE>Comparator</CODE> represents.
     *  Compares the two parameters according to the relation.
     *
     *  @exception ComparatorParameterException if the types of
     *  the parameters are not compatible with <CODE>Integer</CODE>.
     */
    public final boolean relation(final Object a, final Object b) {
        if (! (a instanceof Integer) || ! (b instanceof Integer))
            throw new ComparatorParameterException () ;
        return execute((Integer)a, (Integer)b) ;
    }
    /**
     *  The static form of the relation that this
     *  <CODE>Comparator</CODE> represents.  Compares the two
     *  parameters according to the relation.
     */
    public static boolean execute(final Integer a, final Integer b) {
        return a.intValue() > b.intValue() ;
    }
}