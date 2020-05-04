public class LinkedListDeque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T first, Node pre, Node nxt) {
            item = first;
            prev = pre;
            next = nxt;
        }
    }

    private int size;
    private Node sentinel = new Node(null, null, null);

    /** Creates an empty list. */
    public LinkedListDeque() {
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    /** Creates a list with the given item as the first object. */
    public LinkedListDeque(LinkedListDeque other) {
        size = other.size();
        Node lastNode = sentinel;

        for (int i = 0; i < size; i++) {
            lastNode.next = new Node((T) other.get(i), lastNode, sentinel);
            lastNode = lastNode.next;
            sentinel.prev = lastNode;
        }
    }

    /** Adds an item to the front of the list. */
    public void addFirst(T item) {
        size += 1;
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    /** Adds an item to the end of the list. */
    public void addLast(T item) {
        size += 1;
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        return sentinel.next == sentinel;
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
        Node printed = sentinel.next;
        while (printed.next != sentinel){
            System.out.print(printed.item + " ");
            printed = printed.next;
        }
        System.out.print(printed.item + "\n");
    }

    /** Removes and returns the first item of the list. */
    public T removeFirst() {
        size -= 1;
        Node deleted = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        T item = deleted.item;
        deleted.prev = null;
        deleted.next = null;
        return item;
    }

    /** Removes and returns the last item of the list. */
    public T removeLast() {
        size -= 1;
        Node deleted = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        T item = deleted.item;
        deleted.prev = null;
        deleted.next = null;
        return item;
    }

    /** Get the item at the given index. If no such item
      * exists, returns null.
      */
    public T get(int index) {
        if (index > size - 1)
            return null;

        Node getNode;
        for (getNode = sentinel.next; index > 0; index--) {
            getNode = getNode.next;
        }
        return getNode.item;
    }

    /** Recursive version of get method. */
    public T getRecursive(int index) {
        if (index > size - 1)
            return null;

        return recursiveHelper(sentinel.next, index);
    }

    public T recursiveHelper(Node node, int index) {
        if (index == 0) {
            return (T) node.item;
        }
        else {
            return recursiveHelper(node.next, index - 1);
        }
    }
}
