package t2213;

/**
 * Created by DELL on 1/15/2018.
 */
public class Field {
    private int width, height;
    private int[][] matrix;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public void print(){}

    public void removeFullLines(){}

    public Integer getValue(int x, int y){
        return matrix[y][x];
    }

    public void setValue(int x, int y, int value){
        matrix[y][x] = value;
    }
}
