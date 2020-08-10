package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {
    @Test
    public void testCorner() {
        Percolation p = new Percolation(1);
        p.open(0, 0);
        assertEquals(true, p.isOpen(0, 0));
        assertEquals(true, p.percolates());
    }
}
