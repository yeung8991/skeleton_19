package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> pointSet;

    public NaivePointSet(List<Point> points) {
        pointSet = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point thisPoint = new Point(x, y);
        Point bestPoint = pointSet.get(0);
        double bestDistance = Double.MAX_VALUE;

        for (Point p : pointSet) {
            double distance = Point.distance(p, thisPoint);
            if (distance < bestDistance) {
                bestPoint = p;
                bestDistance = distance;
            }
        }
        return bestPoint;
    }
}
