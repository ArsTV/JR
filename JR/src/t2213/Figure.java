package t2213;

/**
 * Created by DELL on 1/15/2018.
 */

public class Figure {
    //matrix who renders figure(1 is for not empty cell, 0 is for empty cell)
    private int[][] matrix;
    //coordinates
    private int x;
    private int y;

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * Rotate figure
     * For simplicity just around the main diagonal.
     */
    public void rotate() {
        int[][] matrix2 = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix2[i][j] = matrix[j][i];
            }
        }

        matrix = matrix2;
    }

    /**
     * Move left figure 
     * Verify current position
     */
    public void left() {
        x--;
        if (!isCurrentPositionAvailable())
            x++;
    }

    /**
     * Move right figure 
     * Verify current position
     */
    public void right() {
        x++;
        if (!isCurrentPositionAvailable())
            x--;
    }

    /**
     * Move up figure
     * Using if the figure was intersected with cells
     */
    public void up() {
        y--;
    }

    /**
     * Move down figure
     */
    public void down() {
        y++;
    }

    /**
     * Move down figure until intersection with other cells
     */
    public void downMaximum() {
        while (isCurrentPositionAvailable()) {
            y++;
        }

        y--;
    }

    /**
     * Verify if figure can stay in current position:
     * а) the figure stays inside of borders
     * б) the figure do not intersect with other cells
     */
    public boolean isCurrentPositionAvailable() {
        Field field = Tetris.game.getField();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 1) {
                    if (y + i >= field.getHeight())
                        return false;

                    Integer value = field.getValue(x + j, y + i);
                    if (value == null || value == 1)
                        return false;
                }
            }
        }

        return true;
    }

    /**
     * Landing figure (adding all not empty cells)
     */
    public void landed() {
        Field field = Tetris.game.getField();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] == 1)
                    field.setValue(x + j, y + i, 1);
            }
        }
    }
}
