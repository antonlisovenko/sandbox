package com.alisovenko.algorithms.externalsort;

/**
 * @author alisovenko 19.06.14
 */
/**
 *  This class implements a doubly-linked list as a concrete
 *  representation of the <CODE>Sequence</CODE> abstraction.
 *
 *  <P> This implementation uses a dummy <CODE>Node</CODE> to act as
 *  both ends of the list of doubly-linked elements. For each
 *  <CODE>Node</CODE>, previous points from the tail to the head and
 *  next points from the head to the tail.
 *
 *  @see Container
 *  @version 2.4 2001.04.23
 *  @author Russel Winder
 */
public class DLList extends AbstractSequence {
    /**
     *  This is the class from which linked-list nodes are
     *  instantiated.
     */
    //  This node knows how to undertake various editing
    //  operations. This is possible because the doubly linked nature of
    //  the list means that any node has all the information to do the
    //  editing.
    protected static class Node {
        /**
         *  A <CODE>Node</CODE> has some datum that it is storing.
         */
        protected Object datum ;
        /**
         *  A <CODE>Node</CODE> has a next, a reference to another
         *  <CODE>Node</CODE>.
         */
        protected Node next ;
        /**
         *  A <CODE>Node</CODE> has a previous, a reference to
         *  another <CODE>Node</CODE>.
         */
        protected Node previous ;
        /**
         *  Constructor for an unconnected <CODE>Node</CODE>.
         *
         *  @param o The datum for this <CODE>Node</CODE>.
         */
        public Node(final Object o) {
            this(o, null, null) ;
        }
        /**
         *  Constructor for a <CODE>Node</CODE> with a given list of
         *  <CODE>Node</CODE>s to become the head of.
         *
         *  @param o The datum for this <CODE>Node</CODE>.
         *  @param n The <CODE>Node</CODE> to be the tail of this one.
         *  @param p The <CODE>Node</CODE> to be the previous of this
         *  one.
         */
        public Node(final Object o, final Node n, final Node p) {
            datum = o ;
            next = n ;
            previous = p ;
        }
        /**
         *  Append an element after ourself.
         */
        public void add(final Node n) {
            n.previous = this ;
            n.next = next ;
            next.previous = n ;
            next = n ;
        }
        /**
         *  Excise ourself from the <CODE>DLList</CODE>.
         */
        public final void remove() {
            next.previous = previous ;
            previous.next = next ;
            next = null ;
            previous = null ;
        }
        /**
         *  Insert an element before ourself.
         */
        public void insert(final Node n) {
            n.next = this ;
            n.previous = previous ;
            previous.next = n ;
            previous = n ;
        }
        /**
         *  Merge a <CODE>DLList</CODE> after ourself.
         */
        public void merge(final DLList l) {
            next.previous = l.endNode.previous ;
            l.endNode.previous.next = next ;
            next = l.endNode.next ;
            l.endNode.next.previous = this ;
        }
    }
    /**
     *  The <CODE>Node</CODE> that does not have any data but acts as
     *  both the head and the tail of the list of element.
     */
    //  Can't be final because of the clone operation.
    protected /*final*/ Node endNode = new Node (null) ; {
        makeEmpty() ;
    }
    /**
     *  The count of the number of elements in the <CODE>DLList</CODE>.
     *  This is not absolutely necessary but having it makes a lot of
     *  operations a lot easier.  A good efficiency measure.
     */
    protected int elementCount = 0 ;
    /**
     *  Default constructor.  Only needed since we need to define the
     *  following constructors.
     */
    public DLList() {}
    /**
     *  Construct a <CODE>DLList</CODE> by copying the
     *  <CODE>Sequence</CODE>.
     */
    public DLList(final Sequence s) {
        copy(s, 0, s.size()) ;
    }
    /**
     *  Construct a <CODE>DLList</CODE> by copying the sub-sequence
     *  defined by two indexes into a <CODE>Sequence</CODE>.
     */
    public DLList(final Sequence s, final int b, final int e) {
        copy(s, b, e) ;
    }
    /**
     *  Construct an <CODE>DLList</CODE> by copying the sequence defined
     *  by two <CODE>Iterator</CODE>s.
     *
     *  @exception InvalidIteratorException if the two
     *  <CODE>Iterator</CODE>s are not working the same
     *  <CODE>DLList</CODE>.
     */
    public DLList(final Iterator b, final Iterator e) {
        copy(b, e) ;
    }
    /**
     *  Return the first item in the <CODE>DLList</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>DLList</CODE>.
     */
    public final Object first() {
        sanityCheck() ;
        return endNode.next.datum ;
    }
    /**
     *  Return the last element in the <CODE>DLList</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>DLList</CODE>.
     */
    public final Object last() {
        sanityCheck() ;
        return endNode.previous.datum ;
    }
    /**
     *  Return the element at a given position in the
     *  <CODE>DLList</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>DLList</CODE>.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>DLList</CODE>.
     */
    public final Object get(final int index) {
        sanityCheck(index) ;
        return nodeAt(index).datum ;
    }
    /**
     *  Assign a value to a position in the <CODE>DLList</CODE>.
     *  This overwrites whatever value was there.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>DLList</CODE>.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>DLList</CODE>.
     */
    public void set(final int index, final Object o) {
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
        nodeAt(index).datum = o ;
    }
    /**
     *  Excise an element from the <CODE>DLList</CODE>.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>DLList</CODE>.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>DLList</CODE>.
     */
    public final void remove(final int index) {
        sanityCheck(index) ;
        nodeAt(index).remove() ;
        elementCount-- ;
    }
    /**
     *  Insert a new item in the <CODE>DLList</CODE>.  Insertion
     *  happens before, i.e. the new item becomes the index'th item
     *  with the old index'th item becoming the index+1'th item, etc.
     *
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>DLList</CODE> and the index is not 0.
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>DLList</CODE>.
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
        nodeAt(index).insert(new Node (o)) ;
        elementCount++;
    }
    /**
     *  Make the <CODE>DLList</CODE> empty by disconnecting the
     *  <CODE>Node</CODE>s that comprise the internal data structure.
     *  Assume that garbage collection will tidy up whenever necessary..
     *
     *  <P>The clone and method relies on the fact that this method does
     *  not actually do anything with the <CODE>Node</CODE>s.</P>
     */
    public final void makeEmpty() {
        endNode.next = endNode ;
        endNode.previous =  endNode;
        elementCount = 0 ;
    }
    /**
     *  Return the number of items in the <CODE>DLList</CODE>.
     */
    public int size() {
        return elementCount ;
    }
    /**
     *  Append an element to the end of the <CODE>DLList</CODE>.
     */
    public void add(final Object o) {
        endNode.previous.add(new Node (o)) ;
        elementCount++ ;
    }
    /**
     *  Remove an element of a given value from the
     *  <CODE>DLList</CODE> if it is in the <CODE>DLList</CODE>.
     *
     *  @return whether the item was actually in the
     *  <CODE>DLList</CODE>.
     *  @exception AccessEmptyContainerException if there are no
     *  items in the <CODE>DLList</CODE>.
     */
    public final boolean remove(final Object o) {
        sanityCheck() ;
        for (Iterator i = (Iterator)iterator() ;
             ! i .atEnd() ;
             i.advance()) {
            if (i.get().equals(o)) {
                i.remove() ;
                return true ;
            }
        }
        return false ;
    }
    /**
     *  Deliver up a complete shallow copy of the <CODE>DLList</CODE>.
     *  NB <CODE>CloneNotSupportedException</CODE> is caught in
     *  <CODE>AbstractContainer</CODE> so we don't need to worry about
     *  it here.
     */
    public Object clone() {
        DLList l = (DLList)super.clone() ;
        l.endNode = new Node (null) ;
        l.makeEmpty() ;
        for (com.alisovenko.algorithms.externalsort.Iterator i = iterator() ; ! i.atEnd() ; i.advance()) {
            l.add(i.get()) ;
        }
        return l ;
    }
    /**
     *  Deliver up an iterator positioned at the beginning of the
     *  <CODE>DLList</CODE>.
     */
    public final ForwardIterator begin() {
        return new Iterator () ;
    }
    /**
     *  Deliver up an iterator positioned at the end of the
     *  <CODE>DLList</CODE>.
     */
    public final ForwardIterator end() {
        Iterator i = new Iterator () ;
        i.current = endNode.previous ;
        return i ;
    }
    /**
     *  Merge a <CODE>DLList</CODE> after a given index.  This moves
     *  (not copies) all the <CODE>Nodes</CODE> from the
     *  <CODE>DLList</CODE> that is the parameter into us.
     */
    public final void merge(final int index, final DLList l) {
        nodeAt(index).merge(l) ;
        elementCount += l.elementCount ;
        l.makeEmpty() ;
    }
    /**
     *  Append a <CODE>DLList</CODE> to ourself.  The moves (not copies)
     *  all the <CODE>Nodes</CODE> from the <CODE>DLList</CODE> that is
     *  the parameter into us.
     */
    public final void merge(final DLList l) {
        endNode.previous.merge(l) ;
        elementCount += l.elementCount ;
        l.makeEmpty() ;
    }
    /**
     *  Find a <CODE>Node</CODE> given an index.
     *
     *  @exception IndexOutOfBoundsException if the requested
     *  <CODE>index</CODE> is not valid due to the size of the
     *  <CODE>DLList</CODE>.
     */
    private final Node nodeAt(final int index) {
        //  The index is already known to be reasonable, choose the end
        //  which is nearest the node we want and then walk the list to
        //  the appropriate Node.
        Node current = endNode.next ;
        if (index >= size() / 2) {
            current = endNode.previous ;
            for (int i = size() - 1 ; i > index ; i--) {
                current = current.previous ;
            }
        } else {
            for (int i = 0 ; i < index ; i++) {
                current = current.next ;
            }
        }
        return current ;
    }
    /**
     *  The iterator over <CODE>DLList</CODE>s.
     *
     *  This is an inner class (as opposed to being a nested top-level
     *  class) so that it automatically has a reference to it's parent
     *  object, i.e. the object it is an iterator for.
     */
    public class Iterator implements BiDirectionalIterator {
        /**
         *  The current location of the iterator.
         */
        private Node current = endNode.next ;
        /**
         *  Deliver the value we are currently referring to.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final Object get() {
            validityCheck() ;
            return  current.datum;
        }
        /**
         *  Amend the value of the element we are currently referring to.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final void set(final Object o) {
            validityCheck() ;
            current.datum = o ;
        }
        /**
         *  Move on to the next element in the container.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final void advance() {
            validityCheck() ;
            current = current.next ;
        }
        /**
         *  Move on n items in the container.  <CODE>increment</CODE> may
         *  be positive or negative.  If the increment attempts to move
         *  the iterator outside the boundaries of the <CODE>DLList</CODE>
         *  then it will be positioned at the appropriate extremity.
         *
         *  @exception InvalidIteratorException if the iterator is not
         *  valid.
         */
        public final void advance(int increment) {
            if (increment < 0) {
                retreat (-increment) ;
            } else {
                for ( ; increment > 0 ; --increment) {
                    if (atEnd())
                        break ;
                    advance() ;
                }
            }
        }
        /**
         *  Move back to the previous item in the container.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final void retreat() {
            validityCheck() ;
            current = current.previous ;
        }
        /**
         *  Move back n items in the container.  <CODE>increment</CODE>
         *  may be positive or negative.  If the increment attempts to
         *  move the iterator outside the boundaries of the
         *  <CODE>DLList</CODE> then it will be positioned at the
         *  appropriate extremity.
         *
         *  @exception InvalidIteratorException if the iterator is not
         *  valid.
         */
        public final void retreat(int increment) {
            if (increment < 0) {
                advance(-increment) ;
            } else {
                for ( ; increment > 0 ; --increment) {
                    if (atBegin())
                        break ;
                    retreat() ;
                }
            }
        }
        /**
         *  Have we reached the beginning of the iteration?
         */
        public final boolean atBegin() {
            return current == endNode.next ;
        }
        /**
         *  Have we reached the end of the iteration?
         */
        public final boolean atEnd() {
            return current == endNode ;
        }
        /**
         *  Determine the distance between this iterator and the parameter
         *  iterator.
         *
         *  @exception InvalidIteratorException if the iterator i is not
         *  in the same sequence as this iterator.
         */
        public int distance(ForwardIterator i) {
            if (getContainer() != i.getContainer())
                throw new InvalidIteratorOperationException () ;
            int d = 0 ;
            for (Node t = current ; t != null ; ++d, t = t.next) {
                if (t == ((Iterator)i).current)
                    return d ;
            }
            d = 0 ;
            for (Node t = current ; t != null ; --d, t = t.previous) {
                if (t == ((Iterator)i).current)
                    return d ;
            }
            throw new InvalidIteratorOperationException () ;
        }
        /**
         *  Are two iterators equal, i.e. do two iterators refer to the
         *  same item in the same data structure.
         */
        public boolean equals(final com.alisovenko.algorithms.externalsort.Iterator i) {
            if (! (i instanceof Iterator))
                return false ;
            return current == ((Iterator)i).current ;
        }
        /**
         *  Return a reference to the <CODE>Container</CODE> of this
         *  <CODE>Iterator</CODE>.
         */
        public final Container getContainer() {
            return DLList.this ;
        }
        /**
         *  Clone this <CODE>Iterator</CODE>.
         */
        public final Object clone() {
            try {
                return super.clone() ;
            } catch (CloneNotSupportedException e) {
                throw new InternalError ()  ;
            }
        }
        /**
         *  Insert a new item.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final void insert(final Object o) {
            validityCheck() ;
            current.insert(new Node (o)) ;
            elementCount++ ;
        }
        /**
         *  Insert a new item.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final void add(final Object o) {
            validityCheck() ;
            current.add(new Node (o)) ;
            elementCount++ ;
        }
        /**
         *  Remove this item move the iterator on one so that it is still
         *  valid.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final void remove() {
            validityCheck() ;
            Node n = current ;
            current = current.next ;
            n.remove() ;
            elementCount-- ;
        }
        /**
         *  Can do merging of <CODE>DLList</CODE>s easily.
         *
         *  @exception InvalidIteratorException if the iterator is
         *  invalid.
         */
        public final void merge(final DLList l) {
            validityCheck() ;
            current.merge(l) ;
            elementCount += l.elementCount ;
            l.makeEmpty() ;
        }
        /**
         *  Check to see if the iterator is valid.
         */
        private final void validityCheck() {
            if (current == null)
                throw new InvalidIteratorException () ;
        }
    }
}