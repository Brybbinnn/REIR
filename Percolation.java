import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    public static boolean grid[][];
    public static int open_counter = 0;
    public static int length;
    public static int head;
    public static int tail;
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
        uf = new WeightedQuickUnionUF(N*N+2);
        Percolation.setParents();
    };

    public static void setParents(){
        for(int i = 0; i < length; i++){
            uf.union(head, input2dto1d(0, i));
        }

        for(int i = 0; i < length; i++){
            uf.union(tail, input2dto1d(length-1, i));
        }
    }

    public static int input2dto1d(int row, int col){
        return row + col * length;
    }

    public void open(int row, int col) {
    // open the site (row, col) if it is not open already
        if(Percolation.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column is out of bounds");
        }
        if(grid[row][col] == false){
            grid[row][col] = true;
            open_counter ++;
        }
        union_cords(row-1, col, row, col);
        union_cords(row, col+1, row, col);
        union_cords(row+1, col, row, col);
        union_cords(row, col-1, row, col);

    };

    public static void union_cords(int new_row, int new_col, int old_row, int old_col){
        try{
            if(isOpenStatic(new_row, new_col) == true){
                uf.union(input2dto1d(old_row, old_col), input2dto1d(new_row, new_col));
            }
        }
        catch(IndexOutOfBoundsException e){   
        }
    }

    public static boolean isOpenStatic(int row, int col) {
    // is the site (row, col) open?
        if(Percolation.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column is out of bounds");
        }
        if(grid[row][col] == true){
            return true;
        }

        return false;
    };

    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?
        if(Percolation.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column is out of bounds");
        }
        if(grid[row][col] == true){
            return true;
        }
        return false;
    };

    public boolean isFull(int row, int col) {
    // is the site (row, col) full?
        if(Percolation.isOutOfBounds(row, col) == true){
            throw new java.lang.IndexOutOfBoundsException("The row or column is out of bounds");
        }
        if((grid[row][col] == connected(head, input2dto1d(row, col))) && isOpen(row, col) == true){
            return true;
        }
        return false;
    }; 

    public static boolean isOutOfBounds(int row, int col){
        if(row >= length || col >= length){
            return true;
        }
        else if(row < 0 || col < 0){
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
        return connected(head, tail);
    };

    public boolean connected(int p, int q) {
        return uf.find(p) == uf.find(q);
    }

    public static void main(String[] args) {
    // unit testing (required)
        //Percolation per = new Percolation(10);
    }; 
}