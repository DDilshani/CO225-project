public class PointGrid {

    static int width = 800, height = 800;
    static int[][] grid;

    PointGrid(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new int[width][height];
    }

    public static void setPoint(int x, int y, int val) {
        grid[x][y] = val;
    }

    public static int getPoint(int x, int y) {
        return grid[x][y];
    }
}
