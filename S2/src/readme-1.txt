/**********************************************************************
 *  Pattern Recognition readme.txt template
 **********************************************************************/

Name: Grímur Arnar Ámundason            
Login:            

Partner name: Bryndís Gunnarsdóttir
Partner login:    

Hours to complete assignment (optional): 20 hours



/**********************************************************************
 *  Step 1.  Explain *briefly* how you implemented brute force.
 *           Describe how you implemented compareTo() and the
 *           slopeTo() methods in the Point data type.
 **********************************************************************/
The brute force method iterates through all combinations of four points and checks if they are collinear based on the slopes between them.
If four points are collinear, they are added to a segment queue, which is then added to the main queue to represent a collinear line segment.
The program reads input points, creates an instance of Brute, and prints out the collinear line segments found using the brute-force algorithm.

compareTo(Point that): This method compares two points based on their y-coordinates and, in case of a tie, their x-coordinates. 
We check first if yi is smaller than yi+1 and if so return -1, if its the other way around, return 1, if its equal then we check the same for 
xi and xi+1. If both y and x of both points are equal we return 0.

slopeTo(Point that): This method calculates the slope between the calling point and that. 
The formula for the slope is (y2 - y1) / (x2 - x1). 

/**********************************************************************
 *  Step 2.  Explain *briefly* how you implemented the sorting solution.
 *           What steps did you do to avoid outputting permutations
 *           and subsegments?
 **********************************************************************/




/**********************************************************************
 *  Empirical    Fill in the table below with actual running times in
 *  Analysis     seconds when reasonable (say 180 seconds or less).
 *               You can round to the nearest tenth of a second.
 *
 *  Estimate (using tilde notation) the running time (in seconds) of
 *  your two main functions as a function of the number of points N.
 *
 *  Explain how you derive any exponents.
 **********************************************************************/

    
      N       brute       sorting
 ---------------------------------
    150
    200
    300
    400
    800
   1600
   3200
   6400
  12800


Brute:    ~

Sorting:  ~




/**********************************************************************
 *  Theoretical   Give the order of growth of the worst-case running
 *                time of your programs as a function of N. Justify
 *                your answer briefly.
 **********************************************************************/

Brute:

Sorting:



/**********************************************************************
 *  Known bugs / limitations. For example, if your program prints
 *  out different representations of the same line segment when there
 *  are 5 or more points on a line segment, indicate that here.
 **********************************************************************/

slopeTo() does not check if we are dividing by zero and other special cases.

/**********************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 **********************************************************************/



/**********************************************************************
 *  Describe any serious problems you encountered.                    
 **********************************************************************/




/**********************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 **********************************************************************/

