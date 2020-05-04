public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;

    /** Creates an empty list. */
    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    /** Creates a list with the given item as the first object. */
    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[other.size() * 2];

        for (int i = 0; i < other.size(); i++) {
            items[i] = (T) other.get(i);
        }

        size = other.size();
        nextLast = size;
        nextFirst = items.length - 1;
    }

    /** Resizing the list. */
    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];

        if (nextFirst < nextLast) {
            // go bigger.
            System.arraycopy(items, nextFirst + 1, a, 0, size);
        } else {
            // go smaller.
            int firstSize = items.length - nextFirst - 1;
            int secondSize = size - firstSize;
            System.arraycopy(items, nextFirst + 1, a, 0, firstSize);
            System.arraycopy(items, 0, a, firstSize, secondSize);
        }

        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /** Finds the actual index of the given index in the array. */
    private int indexCounter(int index) {
        int actualIndex = nextFirst + index + 1;

        if (actualIndex >= items.length) {
            actualIndex -= items.length;
        }

        return actualIndex;
    }

    /** Counts the usage of the list. */
    private double usageCounter() {
        return (double) size / items.length;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(T item) {
        if (nextFirst == nextLast) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        size += 1;


        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
    }

    /** Adds an item to the end of the list. */
    public void addLast(T item) {
        if (nextFirst == nextLast) {
            resize(size * 2);
        }

        items[nextLast] = item;
        size += 1;

        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the size of the deque. */
    public int size() {
        return size;
    }

    /** Prints the deque from first to last, separated
     * by a space. And print out a new line once printed
     * out all the items.
     */
    public void printDeque() {
        int i = 0;

        while (i < size - 1) {
            System.out.print(items[indexCounter(i)] + " ");
            i += 1;
        }
        System.out.print(items[indexCounter(i)] + "\n");
    }

    /** Removes and returns the first item of the list. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        nextFirst += 1;
        if (nextFirst == items.length) {
            nextFirst = 0;
        }

        if (items.length > 16 && usageCounter() < 0.25) {
            resize(size * 2);
        }

        return items[nextFirst];
    }

    /** Removes and returns the last item of the list. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        nextLast -= 1;
        if (nextLast < 0) {
            nextLast += items.length;
        }

        if (items.length > 16 && usageCounter() < 0.25) {
            resize(size * 2);
        }

        return items[nextLast];
    }


    /** Get the item at the given index. If no such item
     * exists, returns null.
     */
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        return items[indexCounter(index)];
    }
}
