import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationFailed {
    public static int grid[][];
    public static int open = 1;
    public static int closed = 0;
    public static int open_counter = 0;
    public static int length = 0;

    public PercolationFailed(int N) {
    // create N-by-N grid, with all sites initially blocked
        if(N <= 0){
            throw new java.lang.IllegalArgumentException("Percolation does not accept variables below or equal to 0");
        }
        length = N;
        grid = new int[length][length];

        for(int i = 0; i < length; i++){
            String gridOut = "";
            for(int j = 0; j < length; j++){
                gridOut = gridOut.concat(Integer.toString(grid[i][j]) + " ");
            }
            System.out.println(gridOut); 
        }
    }; 
    public void open(int row, int col) {
    // open the site (row, col) if it is not open already
        if(PercolationFailed.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column was out of bounds");
        }
        if(grid[row][col] == closed){
            grid[row][col] = open;
            open_counter ++;
        }
    }; 
    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?
        if(PercolationFailed.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column was out of bounds");
        }
        if(grid[row][col] == open){
            return true;
        }
        return false;
    }; 
    public boolean isFull(int row, int col) {
    // is the site (row, col) full?
        if(PercolationFailed.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column was out of bounds");
        }
        if(grid[row][col] == closed){
            return true;
        }
        return false;
    }; 
    public static boolean isOutOfBounds(int row, int col){
        if(row >= length || col >= length){
            return true;
        }
        return false;
    }
    public int numberOfOpenSites() {
    // number of open sites
        return open_counter;
    }; 
    public boolean percolates() {
    // does the system percolate?
        
        return true;
    }; 
    public boolean connected() {
        
        return true;
    }
    public static void main(String[] args) {
    // unit testing (required)
        Percolation per = new Percolation(10);
        //WeightedQuickUnionUF wqf = new WeightedQuickUnionUF(length);
        //int con = wqf.count();
        //System.out.println(con);
        
    }; 
}