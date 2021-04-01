package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<Node> aHeap = new ArrayList<>();                // array heap
    private HashMap<T, Integer> itemMap = new HashMap<>();            // store the items and pos pairs

    public ArrayHeapMinPQ() {
        aHeap.add(null);
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException("the item already exists.");
        }
        aHeap.add(new Node(item, priority));
        itemMap.put(item, size());
        swim(size());
    }

    /* swap node at index a and node at index b. */
    private void swap(int a, int b) {
        Node newB = aHeap.get(a);
        Node newA = aHeap.get(b);
        aHeap.remove(a);
        aHeap.add(a, newA);
        itemMap.put(aHeap.get(a).getItem(), a);
        aHeap.remove(b);
        aHeap.add(b, newB);
        itemMap.put(aHeap.get(b).getItem(), b);
    }

    /* Get the Node to proper position. */
    private void swim(int k) {
        if (k > 1 && aHeap.get(parent(k)).compareTo(aHeap.get(k)) > 0) {
            swap(k, parent(k));
            swim(parent(k));
        }
    }


    private int parent(int k) {
        return k / 2;
    }

    private int lNode(int k) {
        return k * 2;
    }

    private int rNode(int k) {
        return k * 2 + 1;
    }

    /**
     * @param k index of the node
     * @return  the smaller child of the given index node,
     *          if the node has no child, return itself.
     */
    private int smallerChild(int k) {
        if (size() < lNode(k)) {
            return k;
        }
        if (size() == lNode(k) || aHeap.get(lNode(k)).compareTo(aHeap.get(rNode(k))) < 0) {
            return lNode(k);
        }
        return rNode(k);
    }

    @Override
    public boolean contains(T item) {
        return itemMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("No items exist.");
        }
        return aHeap.get(1).getItem();
    }

    @Override
    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("No items exist.");
        }
        T item = getSmallest();
        swap(1, size());
        aHeap.remove(size());
        itemMap.remove(item);
        if (size() > 1) {
            sink(1);
        }
        return item;
    }

    /* Get the less priority node to the bottom. */
    private void sink(int k) {
        int smallerKid = smallerChild(k);
        if (aHeap.get(smallerKid).compareTo(aHeap.get(k)) < 0) {
            swap(k, smallerKid);
            sink(smallerKid);
        }
    }

    @Override
    public int size() {
        return itemMap.size();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("No such item in the queue.");
        }
        int index = itemMap.get(item);
        double oldPriority = aHeap.get(index).getPriority();
        aHeap.get(index).setPriority(priority);
        if (priority > oldPriority) {
            sink(index);
        } else {
            swim(index);
        }
    }


    /* Store the item and its priority. */
    private class Node implements Comparable<Node> {
        private double priority;
        private T item;

        Node(T i, double p) {
            item = i;
            priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double p) {
            priority = p;
        }

        @Override
        public int compareTo(Node o) {
            if (o == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), o.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            return this.getItem().equals(((Node)o).getItem());
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
