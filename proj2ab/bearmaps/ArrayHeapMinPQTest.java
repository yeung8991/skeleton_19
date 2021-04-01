package bearmaps;

import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testContains() {
        ExtrinsicMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("1", 1.0);
        heap.add("2", 2.0);
        assertTrue(heap.contains("1"));
        assertTrue(heap.contains("2"));
        assertFalse(heap.contains("3"));
    }

    @Test
    public void testGetSmallest() {
        ExtrinsicMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("1", 1.0);
        heap.add("2", 2.0);
        heap.add("3", 0.1);
        assertEquals("3", heap.getSmallest());
    }

    @Test
    public void testRemoveSmallest() {
        ExtrinsicMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("1", 1.0);
        heap.add("2", 2.0);
        heap.add("3", 0.1);
        assertEquals("3", heap.removeSmallest());
        assertEquals("1", heap.removeSmallest());
        assertEquals("2", heap.removeSmallest());
    }

    @Test
    public void testChangePriority() {
        ExtrinsicMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("1", 1.0);
        heap.add("2", 2.0);
        heap.add("3", 0.1);
        assertEquals("3", heap.getSmallest());
        heap.changePriority("3", 2.1);
        assertEquals("1", heap.getSmallest());
        heap.changePriority("3", 0.9);
        assertEquals("3", heap.getSmallest());
    }

    @Test
    public void testSize() {
        ExtrinsicMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("1", 1.0);
        heap.add("2", 2.0);
        heap.add("3", 0.1);
        assertEquals(3, heap.size());
    }

    @Test
    public void testRemove() {
        ExtrinsicMinPQ<String> heap = new ArrayHeapMinPQ<>();
        heap.add("1", 1.0);
        heap.add("2", 2.0);
        heap.add("3", 0.1);
        heap.add("4", 0.8);
        assertEquals("3", heap.removeSmallest());
        assertEquals("4", heap.getSmallest());
    }

/*    @Test
    public void testRuntime() {
        ExtrinsicMinPQ<String> heap = new ArrayHeapMinPQ<>();
        ExtrinsicMinPQ<String> heap2 = new NaiveMinPQ<>();
        Random r = new Random();

        String s = "1";
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i += 1) {
            heap.add(s + i, r.nextDouble());
        }
        long end = System.currentTimeMillis();
        System.out.println("(heap add) Total time elapsed: " + (end - start) / 1000.0 + " seconds.");

        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i += 1) {
            heap.changePriority(s + r.nextInt(100000), r.nextDouble());
        }
        end = System.currentTimeMillis();
        System.out.println("(heap changePriority) Total time elapsed: " + (end - start) / 1000.0 + " seconds.");


        String s2 = "1";
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i += 1) {
            heap2.add(s2 + i, r.nextDouble());
        }
        end = System.currentTimeMillis();
        System.out.println("(naive add) Total time elapsed: " + (end - start) / 1000.0 + " seconds.");


        start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i += 1) {
            heap2.changePriority(s + r.nextInt(100000), r.nextDouble());
        }
        end = System.currentTimeMillis();
        System.out.println("(naive changePriority) Total time elapsed: " + (end - start) / 1000.0 + " seconds.");
    }*/
}
