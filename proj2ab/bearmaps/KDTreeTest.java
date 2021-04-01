package bearmaps;

import org.junit.Test;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNearest() {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0);   // returns p2
        assertEquals(3.3, ret.getX(), 0.0);   // evaluates to 3.3
        assertEquals(4.4, ret.getY(), 0.0);   // evaluates to 4.4
    }

    @Test
    public void testNearest2() {
        Point p1 = new Point(2.0, 3.0);
        Point p2 = new Point(4.0, 2.0);
        Point p3 = new Point(4.0, 5.0);
        Point p4 = new Point(3.0, 3.0);
        Point p5 = new Point(1.0, 5.0);
        Point p6 = new Point(4.0, 4.0);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point best = kd.nearest(0, 7);

        assertEquals(1.0, best.getX(), 0.0);   // evaluates to 3.3
        assertEquals(5.0, best.getY(), 0.0);   // evaluates to 4.4
    }

    @Test
    public void testNearestRandomly() {
        List<Point> points = new LinkedList<>();
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            points.add(new Point(r.nextDouble(), r.nextDouble()));
        }
        KDTree kd = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);
        for (int i = 0; i < 10000; i++) {
            double x = r.nextDouble();
            double y = r.nextDouble();
            assertEquals(nn.nearest(x, y), kd.nearest(x, y));
        }
    }

    @Test
    public void testRuntime() {
        List<Point> points = new LinkedList<>();
        Random r = new Random();
        for (int i = 0; i < 100000; i++) {
            points.add(new Point(r.nextDouble(), r.nextDouble()));
        }
        KDTree kd = new KDTree(points);
        NaivePointSet nn = new NaivePointSet(points);

        Random r2 = new Random(1);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double x = r2.nextDouble();
            double y = r2.nextDouble();
            nn.nearest(x, y);
        }
        long end = System.currentTimeMillis();
        System.out.println("(naive) Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        Random r3 = new Random(1);
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double x = r3.nextDouble();
            double y = r3.nextDouble();
            kd.nearest(x, y);
        }
        end = System.currentTimeMillis();
        System.out.println("(kd tree) Total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }
}
