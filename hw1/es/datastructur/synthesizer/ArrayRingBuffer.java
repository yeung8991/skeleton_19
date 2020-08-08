package es.datastructur.synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> implements BoundedQueue<T>  {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /* Provide a iterator class. */
    private class ARBIterator implements Iterator<T> {
        private int pos;

        public ARBIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < fillCount;
        }

        public T next() {
            T returnValue;
            if ((first + pos) >= rb.length) {
                returnValue = rb[first + pos - rb.length];
            } else {
                returnValue = rb[first + pos];
            }
            pos += 1;
            return returnValue;
        }
    }

    /* Return an iterator */
    @Override
    public Iterator<T> iterator() {
        return new ARBIterator();
    }

    /* Override the equal method. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> that = (ArrayRingBuffer<T>) o;
        if (this.fillCount != that.fillCount) {
            return false;
        }
        for (int i = 0; i < fillCount; i++) {
            if (rb[first + i] != that.rb[that.first + i]) {
                return false;
            }
        }
        return true;
    }


    /** Return the number of items in the array. */
    @Override
    public int fillCount() {
        return fillCount;
    }

    /** Return the size of the array. */
    @Override
    public int capacity() {
        return rb.length;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (fillCount == 0) {
            rb[last] = x;
        } else {
            last += 1;
            if (last == rb.length) {
                last = 0;
            }
            rb[last] = x;
        }
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        int tmp = first;
        first += 1;
        if (first == rb.length) {
            first = 0;
        }

        fillCount -= 1;
        if (fillCount == 0) {
            first = last;
        }
        return rb[tmp];
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }
}
