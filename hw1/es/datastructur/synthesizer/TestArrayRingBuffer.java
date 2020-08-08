package es.datastructur.synthesizer;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testFundamental() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer<Double>(10);
        arb.enqueue(1.0);
        arb.enqueue(5.0);
        assertEquals(1.0, arb.dequeue(), 0.01);
        assertEquals(5.0, arb.peek(), 0.01);

        arb.dequeue();
        arb.enqueue(3.0);
        arb.enqueue(2.0);
        assertEquals(2, arb.fillCount());
        assertEquals(10, arb.capacity());
    }

    @Test
    public void testIterator() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<Integer>(10);
        arb.enqueue(1);
        arb.enqueue(2);
        arb.dequeue();
        arb.enqueue(5);
        for (int i : arb) {
            System.out.println(i);
        }
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<Integer>(10);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<Integer>(10);

        arb1.enqueue(1);
        arb1.dequeue();
        arb1.enqueue(2);
        arb1.enqueue(3);

        arb2.enqueue(2);
        arb2.enqueue(3);

        assertEquals(true, arb1.equals(arb2));
    }
}
