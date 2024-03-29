
/*************************************************************************
 *************************************************************************/
import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class KdTree {
    private int size;
    private Node root; // root of BST

    private class Node {
        private Point2D key; // sorted by key
        private Node left, right; // left and right subtrees (left and below, right and above)
        private RectHV rect;

        public Node(Point2D key, RectHV rect) {
            this.key = key;
            this.rect = rect;
        }
    }

    public KdTree() {
        this.size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return this.size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point to be inserted cannot be null.");
        }

        root = insert(root, p, true, new RectHV(0, 0, 1, 1)); // Start with level 0 (comparing x-coordinates)
    }

    private Node insert(Node node, Point2D point, boolean compareX, RectHV rect) {
        if (node == null) {
            this.size++;
            return new Node(point, rect);
        }

        if (point.equals(node.key)) {
            return node; // Avoid inserting duplicates
        }

        int cmp;
        if (compareX) {
            cmp = Double.compare(point.x(), node.key.x());
        } else {
            cmp = Double.compare(point.y(), node.key.y());
        }

        RectHV leftRect = null, rightRect = null;
        if (cmp < 0) {
            if (compareX)
                leftRect = new RectHV(rect.xmin(), rect.ymin(), node.key.x(), rect.ymax());
            else
                leftRect = new RectHV(rect.xmin(), rect.ymin(), rect.xmax(), node.key.y());

            node.left = insert(node.left, point, !compareX, leftRect);

        } else {
            if (compareX)
                rightRect = new RectHV(node.key.x(), rect.ymin(), rect.xmax(), rect.ymax());
            else
                rightRect = new RectHV(rect.xmin(), node.key.y(), rect.xmax(), rect.ymax());

            node.right = insert(node.right, point, !compareX, rightRect);
        }

        return node;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        if (p == null)
            throw new IllegalArgumentException("Point cannot be null");
        return contains(root, p, true);
    }

    private boolean contains(Node node, Point2D point, boolean compareX) {
        if (node == null)
            return false; // When to stop searching
        if (node.key.compareTo(point) == 0)
            return true; // Check if the current node matches the point we are looking for

        int cmp;
        if (compareX) {
            cmp = Double.compare(point.x(), node.key.x());
        } else {
            cmp = Double.compare(point.y(), node.key.y());
        }

        if (cmp < 0)
            return contains(node.left, point, !compareX);
        else
            return contains(node.right, point, !compareX);
    }

    // draw all of the points to standard draw
    // draw all of the points to standard draw
    public void draw() {
        draw(root, true);
    }

    private void draw(Node node, boolean compareX) {
        if (node == null) return;
        // Draw point
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.02);
        node.key.draw();

        // Draw line
        // vertical line
        if (compareX) { 
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(node.key.x(), node.rect.ymin(), node.key.x(), node.rect.ymax());

        } else 
        // horizontal
        { 
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.rect.xmin(), node.key.y(), node.rect.xmax(), node.key.y());
        }

        draw(node.left, !compareX);
        draw(node.right, !compareX);
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        Queue<Point2D> points = new Queue<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node node, RectHV rect, Queue<Point2D> queue) {
        if (node == null)
            return;
        if (node.rect.intersects(rect) == false)
            return;

        if (rect.contains(node.key))
            queue.enqueue(node.key);

        if (node.rect.intersects(rect)) {
            range(node.left, rect, queue);
            range(node.right, rect, queue);
        }
    }

    // a nearest neighbor in the set to p; null if set is empty
    public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return nearest(root, p, root.key);
    }

    private Point2D nearest(Node node, Point2D p, Point2D nearest) {
        // Base case - if the current node is null, return the current closest point
        if (node == null)
            return nearest;

        // Check if the current node's rectangle is null (an error condition)
        if (node.rect == null) {
            throw new IllegalStateException("Invalid state: Node rectangle is null");
        }

        // Check if the current subtree cannot contain a closer point
        if (node.rect.distanceSquaredTo(p) >= nearest.distanceSquaredTo(p)) {
            return nearest;
        }

        // If the current point is closer, update the closest point
        if (node.key.distanceSquaredTo(p) < nearest.distanceSquaredTo(p)) {
            nearest = node.key;
        }

        // Calculate the squared distances from p to left and right children's
        // rectangles
        double left = node.left != null ? node.left.rect.distanceSquaredTo(p) : Double.POSITIVE_INFINITY;
        double right = node.right != null ? node.right.rect.distanceSquaredTo(p) : Double.POSITIVE_INFINITY;

        // Determine which subtree is closer and explore it first
        if (left < right) {
            // Explore the closer subtree
            nearest = nearest(node.left, p, nearest);
    
            // Check if we need to explore the other subtree
            if (right < nearest.distanceSquaredTo(p)) {
                nearest = nearest(node.right, p, nearest);
            }
        } else {
            // Explore the closer subtree
            nearest = nearest(node.right, p, nearest);
    
            // Check if we need to explore the other subtree
            if (left < nearest.distanceSquaredTo(p)) {
                nearest = nearest(node.left, p, nearest);
            }
        }

        // Return the closest point found after exploring both subtrees
        return nearest;
    }

    /*******************************************************************************
     * Test client
     ******************************************************************************/
    public static void main(String[] args) {
        In in = new In();
        Out out = new Out();
        int nrOfRecangles = in.readInt();
        int nrOfPointsCont = in.readInt();
        int nrOfPointsNear = in.readInt();
        RectHV[] rectangles = new RectHV[nrOfRecangles];
        Point2D[] pointsCont = new Point2D[nrOfPointsCont];
        Point2D[] pointsNear = new Point2D[nrOfPointsNear];
        for (int i = 0; i < nrOfRecangles; i++) {
            rectangles[i] = new RectHV(in.readDouble(), in.readDouble(),
                    in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsCont; i++) {
            pointsCont[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        for (int i = 0; i < nrOfPointsNear; i++) {
            pointsNear[i] = new Point2D(in.readDouble(), in.readDouble());
        }
        KdTree set = new KdTree();
        for (int i = 0; !in.isEmpty(); i++) {
            double x = in.readDouble(), y = in.readDouble();
            set.insert(new Point2D(x, y));
        }
        for (int i = 0; i < nrOfRecangles; i++) {
            // Query on rectangle i, sort the result, and print
            Iterable<Point2D> ptset = set.range(rectangles[i]);
            int ptcount = 0;
            for (Point2D p : ptset)
                ptcount++;
            Point2D[] ptarr = new Point2D[ptcount];
            int j = 0;
            for (Point2D p : ptset) {
                ptarr[j] = p;
                j++;
            }
            Arrays.sort(ptarr);
            out.println("Inside rectangle " + (i + 1) + ":");
            for (j = 0; j < ptcount; j++)
                out.println(ptarr[j]);
        }
        out.println("Contain test:");
        for (int i = 0; i < nrOfPointsCont; i++) {
            out.println((i + 1) + ": " + set.contains(pointsCont[i]));
        }

        out.println("Nearest test:");
        for (int i = 0; i < nrOfPointsNear; i++) {
            out.println((i + 1) + ": " + set.nearest(pointsNear[i]));
        }

        out.println();
    }
}
