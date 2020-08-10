package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolation {
    @Test
    public void testCornerCases() {
        Percolation p = new Percolation(2);
        p.open(1, 0);
        p.open(1, 1);
        p.open(0, 1);
        assertEquals(true, p.percolates());

        Percolation p2 = new Percolation(1);
        p.open(0, 0);
        assertEquals(true, p.percolates());
    }
}
