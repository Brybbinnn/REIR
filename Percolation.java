public class Percolation {
    public Percolation(int N) {
    // create N-by-N grid, with all sites initially blocked
        int grid[][];
        grid = new int[N][N];

        for(int i = 0; i < N; i++){
            String gridOut = "";
            for(int j = 0; j < N; j++){
                gridOut = gridOut.concat(Integer.toString(grid[i][j]));
            }
            System.out.println(gridOut);
        }
    }; 
    public void open(int row, int col) {
    // open the site (row, col) if it is not open already

    }; 
    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?

        return true;
    }; 
    public boolean isFull(int row, int col) {
    // is the site (row, col) full?

        return true;
    }; 
    public int numberOfOpenSites() {
    // number of open sites

        return 1;
    }; 
    public boolean percolates() {
    // does the system percolate?

        return true;
    }; 
    public static void main(String[] args) {
    // unit testing (required)
        new Percolation(10);
    }; 
}

