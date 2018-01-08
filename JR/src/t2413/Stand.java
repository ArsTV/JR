package t2413;

/**
 * Stand for return the ball.
 */
public class Stand extends BaseObject {
    //Picture for drawing
    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    //Speed
    private double speed = 1;
    //Direction (-1 left, +1 right)
    private double direction = 0;

    public Stand(double x, double y) {
        super(x, y, 3);
    }

    /**
     * The method moves the stand according to the current direction.
     */
    void move() {
        double dx = speed * direction;
        x = x + dx;

        checkBorders(radius, Arkanoid.game.getWidth() - radius + 1, 1, Arkanoid.game.getHeight() + 1);
    }

    /**
     * Set direction -1
     */
    void moveLeft() {
        direction = -1;
    }

    /**
     * Set direction +1
     */
    void moveRight() {
        direction = 1;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    /**
     * Draw ourself on the canvas
     */
    @Override
    void draw(Canvas canvas) {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }
}
