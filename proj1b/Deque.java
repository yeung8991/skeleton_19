public interface Deque<T> {
    int size();
    void addFirst(T item);
    void addLast(T item);
    T removeFirst();
    T removeLast();
    T get(int index);

    default void printDeque() {
        for (int i = 0; i < size(); i += 1) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    default boolean isEmpty() {
        return size() == 0;
    }
}
