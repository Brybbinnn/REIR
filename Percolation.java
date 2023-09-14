import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    public static boolean grid[][];
    public static boolean open = true;
    public static boolean closed = false;
    public static int open_counter = 0;
    public static int length = 0;

    public static int head = 0;
    public static int tail = 0;
    public static WeightedQuickUnionUF uf;

    public Percolation(int N) {
    // create N-by-N grid, with all sites initially blocked
        if(N <= 0){
            throw new java.lang.IllegalArgumentException("Percolation does not accept variables below or equal to 0");
        }
        length = N;
        grid = new boolean[length][length];
        head = N*N;
        tail = N*N+1;
        System.out.println(tail);
        uf = new WeightedQuickUnionUF(N*N+2);
        Percolation.setParents();
    };

    public static void setParents(){
        for(int i = 0; i < length; i++){
            uf.union(head, i);
            System.out.println(uf.find(i));
        }
        
        for(int j = length*length-length; j < (length^2); j++){
            uf.union(tail, j);
            System.out.println(uf.find(j));
        }
    }

    public int input2dto1d(int row, int col){
        return row + col * length;
    }

    public void open(int row, int col) {
    // open the site (row, col) if it is not open already
        if(Percolation.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column is out of bounds");
        }
        if(grid[row][col] == closed){
            grid[row][col] = open;
            open_counter ++;
        }
    };

    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?
        if(Percolation.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column is out of bounds");
        }
        if(grid[row][col] == open){
            return true;
        }

        return false;
    };

    public boolean isFull(int row, int col) {
    // is the site (row, col) full?
        if(Percolation.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column is out of bounds");
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
        return uf.find(head) == uf.find(tail);
    };
    
    public static void main(String[] args) {
    // unit testing (required)
        Percolation per = new Percolation(10);
    }; 
}