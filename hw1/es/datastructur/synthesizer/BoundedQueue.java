package es.datastructur.synthesizer;

import java.util.Iterator;

public interface BoundedQueue<T> extends Iterable<T> {
    /** Return size of the buffer. */
    int capacity();

    /** Return number of items currently in the buffer. */
    int fillCount();

    /** Add item x to the end. */
    void enqueue(T x);

    /** Delete and return item from the front. */
    T dequeue();

    /** Return but do not delete item from the front. */
    T peek();

    Iterator<T> iterator();

    /** Is the buffer empty. */
    default boolean isEmpty() {
        if (fillCount() == 0) {
            return true;
        }
        return false;
    }

    /** Is the buffer full. */
    default boolean isFull() {
        if (fillCount() == capacity()) {
            return true;
        }
        return false;
    }
}
