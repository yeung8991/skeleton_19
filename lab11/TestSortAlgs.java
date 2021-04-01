import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import java.awt.event.ItemListener;

import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<Integer> a = new Queue<>();
        a.enqueue(1);
        a.enqueue(10);
        a.enqueue(2);
        a.enqueue(5);
        a.enqueue(4);
        a.enqueue(6);
        a.enqueue(7);
        a.enqueue(8);
        a.enqueue(1);
        System.out.println(a);
        Queue<Integer> q = QuickSort.quickSort(a);
        System.out.println(q);
        System.out.println(a);
    }

    @Test
    public void testMergeSort() {
        Queue<Integer> a = new Queue<>();
        a.enqueue(1);
        a.enqueue(10);
        a.enqueue(2);
        a.enqueue(5);
        a.enqueue(4);
        a.enqueue(6);
        a.enqueue(7);
        a.enqueue(8);
        a.enqueue(11);
        System.out.println(a);
        Queue<Integer> q = MergeSort.mergeSort(a);
        System.out.println(q);
        System.out.println(a);
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
