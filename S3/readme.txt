/**********************************************************************
 *  readme.txt template                                                   
 *  Kd-tree
**********************************************************************/

Name 1: Grimur Arnar Ámundason   
kt 1: 130103-2430

Name 2: Bryndís Gunnarsdóttir
kt 2: 0311022350

/**********************************************************************
 *  Briefly describe the Node data type you used to implement the
 *  2d-tree data structure.
 **********************************************************************/

The Node that we implemented has the standard left and right nodes, which are
null by default, then they each save their point as a key, they also save a 
rectangle which determines the area they cover, as well as their level
or depth, essentially how far away they are from the root.

/**********************************************************************
 *  Describe your method for range search in a kd-tree.
 **********************************************************************/

In range search we followed instructions and whenever a node is checked,
it first checks whether or not its rectangle intersects with the given
rectangle. If not then do the same search for the left and right nodes
recursively.

/**********************************************************************
 *  Describe your method for nearest neighbor search in a kd-tree.
 **********************************************************************/

This code finds the nearest neighbor to a given point p in a KdTree data structure. 
It starts at the root node and recursively explores the tree. If the tree is empty, 
it returns null. If the current node is null, it returns the current closest point 
found so far. It checks if the current node's rectangle is null, which would indicate 
an error. It also checks if the current subtree cannot contain a closer point, 
based on the distance between the point p and the current closest point. 
If a closer point is found, it updates the closest point. It then calculates the 
squared distances from p to the left and right children's rectangles, and it 
determines which subtree to explore first based on these distances. 
Finally, it returns the closest point found after exploring both subtrees.

/**********************************************************************
 *  Give the total memory usage in bytes (using tilde notation and 
 *  the standard 64-bit memory cost model) of your 2d-tree data
 *  structure as a function of the number of points N. Justify your
 *  answer below.
 *
 *  Include the memory for all referenced objects (deep memory),
 *  including memory for the nodes, points, and rectangles.
 **********************************************************************/

bytes per Point2D: 32 bytes

bytes per RectHV: 48 bytes

Total Memory = KdTree Overhead + Internal Node Memory + Point2D Memory + RectHV Memory

bytes per KdTree of N points (using tilde notation):   ~ 16 bytes + 16 * M (internal nodes) bytes + N (Point2D objects) * 32 bytes + P (RectHV objects) * 48 bytes
[include the memory for any referenced Node, Point2D and RectHV objects]


/**********************************************************************
 *  Give the expected running time in seconds (using tilde notation)
 *  to build a 2d-tree on N random points in the unit square.
 *  Use empirical evidence by creating a table of different values of N
 *  and the timing results. (Do not count the time to generate the N 
 *  points or to read them in from standard input.)
 **********************************************************************/



/**********************************************************************
 *  How many nearest neighbor calculations can your brute-force
 *  implementation perform per second for input100K.txt (100,000 points)
 *  and input1M.txt (1 million points), where the query points are
 *  random points in the unit square? Explain how you determined the
 *  operations per second. (Do not count the time to read in the points
 *  or to build the 2d-tree.)
 *
 *  Repeat the question but with the 2d-tree implementation.
 **********************************************************************/

                     calls to nearest() per second
                     brute force           2d-tree

input100K.txt

input1M.txt



/**********************************************************************
 *  Known bugs / limitations.
 **********************************************************************/


/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and d�mat�mar, but do
 *  include any help from people (including course staff, 
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/


/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/


