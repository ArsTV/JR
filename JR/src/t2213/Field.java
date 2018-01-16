package t2213;

import java.util.ArrayList;

/**
 * Created by DELL on 1/15/2018.
 * Field class describes the "field of cells" of the game Tetris
 */

public class Field {
    //width and height
    private int width;
    private int height;

    //Matrix of filed of game (1 is for not empty cell, 0 is for empty cell)
    private int[][] matrix;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new int[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * The method returns a value that is contained in the matrix with coordinates x, y.
     * If the coordinates are outside the matrix, the method returns null.
     */
    public Integer getValue(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            return matrix[y][x];

        return null;
    }

    /**
     * The method sets the value to the cell of the matrix with coordinates (x, y)
     */
    public void setValue(int x, int y, int value) {
        if (x >= 0 && x < width && y >= 0 && y < height)
            matrix[y][x] = value;
    }

    /**
     * The method prints the current matrix content
     */
    public void print() {
        //Matrix for current statement of the game's field
        int[][] canvas = new int[height][width];

        //Making copy of the game's field to the array
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = matrix[i][j];
            }
        }

        //Copy the figure into an array, only non-empty cells
        int left = Tetris.game.getFigure().getX();
        int top = Tetris.game.getFigure().getY();
        int[][] brickMatrix = Tetris.game.getFigure().getMatrix();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (top + i >= height || left + j >= width) continue;
                if (brickMatrix[i][j] == 1)
                    canvas[top + i][left + j] = 2;
            }
        }


        //Print drawn on the screen and we begin with the frame boundary.
        System.out.println("---------------------------------------------------------------------------\n");

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int index = canvas[i][j];
                if (index == 0)
                    System.out.print(" . ");
                else if (index == 1)
                    System.out.print(" X ");
                else if (index == 2)
                    System.out.print(" X ");
                else
                    System.out.print("???");
            }
            System.out.println();
        }


        System.out.println();
        System.out.println();
    }

    /**
     * Delete the filled lines
     */
    public void removeFullLines() {
        //Create a list for storing lines
        ArrayList<int[]> lines = new ArrayList<int[]>();

        //Copy all the non-empty lines to the list.
        for (int i = 0; i < height; i++) {
            //count the number of units in a row
            int count = 0;
            for (int j = 0; j < width; j++) {
                count += matrix[i][j];
            }

            //If the sum of a line is not equal to its width we add it to the list
            if (count != width)
                lines.add(matrix[i]);
        }

        //Add the missing lines to the top of the list.
        while (lines.size() < height) {
            lines.add(0, new int[width]);
        }

        //Convert the list back to the matrix
        matrix = lines.toArray(new int[height][width]);
    }
}
