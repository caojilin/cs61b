// import edu.princeton.cs.algs4.QuickFindUF;
// import edu.princeton.cs.algs4.QuickUnionUF;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    boolean[][] system;
    WeightedQuickUnionUF set;
    int topOpenIndex;
    int bottomOpenIndex;
    int size;
    int count;

    /* Creates an N-by-N grid with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        size = N;
        system = new boolean[N][N];
        set = new WeightedQuickUnionUF(N * N + 2);
        count = 0;
        topOpenIndex = N * N;
        bottomOpenIndex = N * N + 1;
    }

    /* Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (row > system.length - 1 || row < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (col > system[0].length - 1 || col < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (!isOpen(row, col)) {
            if (row == 0) {
                set.union(topOpenIndex, xyTo1D(row, col));
            }
            if (row == system.length - 1) {
                set.union(bottomOpenIndex, xyTo1D(row, col));
            }
            system[row][col] = true;
            count += 1;
        }

        int currentIndex = xyTo1D(row, col);

        if (row > 0 && isOpen(row - 1, col)) {
            set.union(currentIndex, xyTo1D(row - 1, col));
        }
        if (row < system.length - 1 && isOpen(row + 1, col)) {
            set.union(currentIndex, xyTo1D(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            set.union(currentIndex, xyTo1D(row, col - 1));
        }
        if (col < system.length - 1 && isOpen(row, col + 1)) {
            set.union(currentIndex, xyTo1D(row, col + 1));
        }

    }

    /* Returns true if the site at (row, col) is open. */
    public boolean isOpen(int row, int col) {
        if (row > system.length - 1 || row < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (col > system[0].length - 1 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        return system[row][col];
    }

    /* Returns true if the site (row, col) is full. */
    public boolean isFull(int row, int col) {
        if (row > system.length - 1 || row < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (col > system[0].length - 1 || col < 0) {
            throw new IndexOutOfBoundsException();
        }

        return set.connected(xyTo1D(row, col), topOpenIndex);
    }

    /* Returns the number of open sites. */
    public int numberOfOpenSites() {

        return count;
    }

    /* Returns true if the system percolates. */
    public boolean percolates() {
        //delete redundant indices
        return set.connected(topOpenIndex, bottomOpenIndex);
    }

    /* Converts row and column coordinates into a number. This will be helpful
       when trying to tie in the disjoint sets into our NxN grid of sites. */
    private int xyTo1D(int row, int col) {
        return row * system.length + col;
    }

    /* Returns true if (row, col) site exists in the NxN grid of sites.
       Otherwise, return false. */
    private boolean valid(int row, int col) {
        if ((row >= 0 && row < system.length) && (col >= 0 && col < system.length)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 1);
        p.open(1, 0);
        p.open(1, 2);
        p.open(2, 1);
        p.open(1, 1);

    }
}
