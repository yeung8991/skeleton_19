package hw2;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolationStats {
    @Test
    public void testStats() {
        PercolationStats ps = new PercolationStats(20, 10, new PercolationFactory());
        System.out.println(ps.stddev());
        System.out.println(ps.mean());
    }
}
