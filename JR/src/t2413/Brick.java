package t2413;

/**
 * Class is for the "brick" game object
 */
public class Brick extends BaseObject {
    //	Picture for drawing.
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Brick(double x, double y) {
        super(x, y, 3);
    }

    /**
     * Draw ourself on the canvas.
     */
    @Override
    void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'H');
    }

    /**
     * The brick is without moving
     */
    @Override
    void move() {
        //do nothing
    }
}
