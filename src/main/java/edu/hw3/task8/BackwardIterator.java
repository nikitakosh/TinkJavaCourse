package edu.hw3.task8;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BackwardIterator<T> implements Iterator<T> {

    private final Iterator<T> backwardIterator;

    public BackwardIterator(List<T> elements) {
        Collections.reverse(elements);
        this.backwardIterator = elements.iterator();
    }


    @Override
    public boolean hasNext() {
        return backwardIterator.hasNext();
    }

    @Override
    public T next() {
        return backwardIterator.next();
    }


}
