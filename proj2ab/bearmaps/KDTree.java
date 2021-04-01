package bearmaps;

import java.util.Comparator;
import java.util.List;

public class KDTree {
    private Node first;
    private int height;
    Comparator<Node> nodeComparator = (i, j) -> {
        if (i.getDepth() % 2 == 0) {
            return Double.compare(i.getX(), j.getX());
        } else {
            return Double.compare(i.getY(), j.getY());
        }
    };

    public KDTree(List<Point> points) {
        for (Point p : points) {
            first = insert(p, first, 0);
        }
    }

    private Node insert(Point p, Node start, int depth) {
        if (start == null) {
            if (depth > height) {
                height = depth;
            }
            return new Node(p, depth);
        }
        if (nodeComparator.compare(start, new Node(p, 0)) > 0) {
            start.left = insert(p, start.left, depth + 1);
        } else {
            start.right = insert(p, start.right, depth + 1);
        }
        return start;
    }

    public Point nearest(double x, double y) {
        return nearest(first, new Point(x, y), first).getPoint();
    }

    private Node nearest(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        if (n.distance(goal) < best.distance(goal)) {
            best = n;
        }
        Node goodSide, badSide;
        if (nodeComparator.compare(n, new Node(goal, 0)) > 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearest(goodSide, goal, best);

        double bestBadSide;
        if (n.getDepth() % 2 == 0) {
            bestBadSide = Math.pow(n.getX() - goal.getX(), 2);
        } else {
            bestBadSide = Math.pow(n.getY() - goal.getY(), 2);
        }

        if (bestBadSide < best.distance(goal)) {
            best = nearest(badSide, goal, best);
        }

        /* Inefficient way */
/*      if (n.left != null) {
            best = nearest(n.left, goal, best);
        }
        if (n.right != null) {
            best = nearest(n.right, goal, best);
        }*/
        return best;
    }

    private class Node {
        Point p;
        int depth, size;
        Node left, right;

        Node(Point p, int depth) {
            this.p = p;
            this.depth = depth;
        }

        double distance(Point n) {
            return Point.distance(this.getPoint(), n);
        }

        double getX() {
            return p.getX();
        }

        double getY() {
            return p.getY();
        }

        int getDepth() {
            return depth;
        }

        Point getPoint() {
            return p;
        }
    }
}
