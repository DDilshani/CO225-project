public class PointGrid {

    static int width = 800;
    static int height = 600;

    static int[][] grid = new int[width][height];


    PointGrid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public static void setPoint(int x, int y, int val){
        grid[x][y] = val;
    }

    public static int getPoint(int x, int y) {
        return grid[x][y];
    }
}
