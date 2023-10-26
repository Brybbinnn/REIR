
/*************************************************************************
 *************************************************************************/
import java.util.Arrays;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class KdTree {
    // construct an empty set of points
    
    private Node root;             // root of BST

    private class Node {
        private Point2D key;           // sorted by key
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree
        private RectHV rect;
        private int level;
    
        public Node(Point2D key, int size, int level, RectHV rect) {
            this.key = key;
            this.size = size;
            this.rect = rect;
            this.level = level;
        }
    }

    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // number of points in the set
    public int size() {
        return size(root);
    }

    private int size(Node node){
        if(node == null) return 0;
        else return node.size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException("Point to be inserted cannot be null.");
        }

        root = insert(root, p, true); // Start with level 0 (comparing x-coordinates)
    }
    
    private Node insert(Node node, Point2D point, boolean compareX, RectHV rect) {
        if (node == null) {
            return new Node(point, 1, 0, new RectHV(0, 0, 1, 1));
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
            leftRect = new RectHV(rect.xmin(), cmp, cmp, cmp);
            node.left = insert(node.left, point, !compareX, leftRect);
        } else {
            rightRect = new RectHV(cmp, cmp, cmp, cmp)
            node.right = insert(node.right, point, !compareX, rightRect);
        }
    
        node.size = 1 + size(node.left) + size(node.right);
        node.level ++;
        return node;
    }

    // does the set contain the point p?
    public boolean contains(Point2D p) {
        if(get(p) != null) return true;
        return false;
    }

    public Point2D get(Point2D p) {
        return get(root, p, true);
    }

    private Point2D get(Node node, Point2D point, boolean compareX) {
        //if (point == 0) throw new IllegalArgumentException("calls get() with 0 as a key");
        
        if (node == null) return null;         //When to stop searching

        int cmp = point.compareTo(node.key);   //Check if the current node matches the point we are looking for
        
        if (cmp < 0) {
            return get(node.left, point, !compareX);
        } 
        else if (cmp > 0) {
            return get(node.right, point, !compareX);
        }
        else {
            return node.key;
        }
    }

    // draw all of the points to standard draw
    public void draw() {
    }

    // all points in the set that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        return null;
    }

    private Node range(Node node, RectHV rect){
        if(node == null) return null;
        //if(rect.contains(node.key) == false) return null;
        RectHV rec = new RectHV(0, 0, 0, 0);
        return node;
    }

    // a nearest neighbor in the set to p; null if set is empty
   public Point2D nearest(Point2D p) {
        if (isEmpty()) {
            return null;
        }
        return nearest_recursive(root, p, root.key);
    }

    private Point2D nearest_recursive(Node node, Point2D queryPoint, Point2D champion) {
        if (node == null) {
            return champion;
        }

        double championDistance = queryPoint.distanceSquaredTo(champion);
        double currentRectDistance = node.rect.distanceSquaredTo(queryPoint);

        if (currentRectDistance < championDistance) {
            if (node.key.distanceSquaredTo(queryPoint) < championDistance) {
                champion = node.key;
            }

            if (nodeIsLeftOfPoint(queryPoint, node)) {
                champion = nearest_recursive(node.left, queryPoint, champion);
                champion = nearest_recursive(node.right, queryPoint, champion);
            } else {
                champion = nearest_recursive(node.right, queryPoint, champion);
                champion = nearest_recursive(node.left, queryPoint, champion);
            }
        }

        return champion;
    }

    private boolean nodeIsLeftOfPoint(Point2D point, Node node) {
        if (node.level % 2 == 0) {
            return point.x() < node.key.x();
        } else {
            return point.y() < node.key.y();
        }
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
