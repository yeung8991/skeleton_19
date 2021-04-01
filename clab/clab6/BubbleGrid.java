public class BubbleGrid {
    private int[][] grid;
    private UnionFind uF;
    private int rowNum, colNum;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        colNum = grid[0].length;
        // 0 represents ceiling.
        uF = new UnionFind(rowNum * colNum + 1);
        for (int i = 0; i < rowNum; i++){
            for (int j = 0; j < colNum; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0) {
                        uF.union(xy2uF(i, j), 0);
                    }
                    unionNeighbour(i, j);
                }
            }
        }
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        int[] fallingBubbles = new int[darts.length];
        int originBubbles = uF.sizeOf(0);
        int count = 0;

        for (int[] dart : darts) {
            grid[dart[0]][dart[1]] = 0;
            for (int i = 0; i < rowNum; i++){
                for (int j = 0; j < colNum; j++) {
                    if (grid[i][j] == 1) {
                        if (i == 0) {
                            uF.union(xy2uF(i, j), 0);
                        }
                        unionNeighbour(i, j);
                    }
                }
            }
            fallingBubbles[count] = originBubbles - uF.sizeOf(0);
            count += 1;
        }
        return fallingBubbles;
    }

    private int xy2uF(int row, int col) {
        return row * colNum + col + 1;
    }

    private int[] uF2xy(int pos) {
        int row = 0;
        while (pos > colNum) {
            pos = pos - colNum;
            row += 1;
        }
        int[] xyPos = {row, pos - 1};
        return xyPos;
    }

    private void unionNeighbour(int x, int y) {
        int pos = xy2uF(x, y);
        int[] neighbours = {pos - 1, pos + 1, pos + colNum, pos - colNum};
        for (int neighbourPos : neighbours) {
            if (neighbourPos > 0 && neighbourPos <= rowNum * colNum
             && grid[uF2xy(neighbourPos)[0]][uF2xy(neighbourPos)[1]] == 1) {
                uF.union(pos, neighbourPos);
            }
        }
    }
}
