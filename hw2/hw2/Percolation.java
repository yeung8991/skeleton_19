package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] neighbours = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    private int[][] grid;
    private int gridSize;
    private int openSitesNum;
    private WeightedQuickUnionUF uF;

    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N must greater than 0.");
        }

        grid = new int[N][N];
        uF = new WeightedQuickUnionUF(N * N + 2);
        gridSize = N;
        openSitesNum = 0;

        // 0 represents the site is blocked. 1 represents open site.
        for (int[] r : grid) {
            for (int c : r) {
                c = 0;
            }
        }
    }

    /*
     * Open the sites, set the given pos of the grid to 1,
     * and union its neighbours.
     */
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = 1;
        openSitesNum += 1;
        if (row == 0) {
            uF.union(0, xyTo1D(row, col));
        }
        if (isFull(row, col) && row == gridSize - 1) {
            uF.union(1, xyTo1D(row, col));
        }
        unionNeighbours(row, col);
    }

    /*
     * Return true if the given pos is an open site.
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        if (grid[row][col] == 1) {
            return true;
        }
        return false;
    }

    /*
     * Return true if the given pos is an full site.
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uF.connected(0, xyTo1D(row, col));
    }

    /*
     * Return the number of open sites.
     */
    public int numberOfOpenSites() {
        return openSitesNum;
    }

    /*
     * Return true if the grid is percolated.
     */
    public boolean percolates() {
        if (uF.connected(0, 1)) {
            return true;
        }
        return false;
    }

    /**
     * Validate if the row and col is inside the range.
     */
    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row >= gridSize || col >= gridSize) {
            throw new IndexOutOfBoundsException("Index out of bounds.");
        }
    }

    /**
     * Convert 2D position to 1D.
     */
    private int xyTo1D(int row, int col) {
        return row * gridSize + col + 2;
    }

    /**
     * Union neighbours in the WeightedQuickUnionUF.
     */
    private void unionNeighbours(int row, int col) {
        for (int[] neighbour : neighbours) {
            int newRow = row + neighbour[0];
            int newCol = col + neighbour[1];
            if (newRow < 0
                || newCol < 0
                || newRow >= gridSize
                || newCol >= gridSize
                || grid[newRow][newCol] == 0) {
                continue;
            }
            uF.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
        }
    }
}
