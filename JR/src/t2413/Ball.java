package t2413;

/**
 * Class is for the "ball" game object
 */
public class Ball extends BaseObject {
    //speed
    private double speed;
    //direction  (angle from 0 to 360)
    private double direction;

    //current value of the motion vector (dx,dy)
    private double dx;
    private double dy;

    // Is frozen our object or it can move.
    private boolean isFrozen;

    public Ball(double x, double y, double speed, double direction) {
        super(x, y, 1);

        this.direction = direction;
        this.speed = speed;

        this.isFrozen = true;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    /**
     * Set new direction of moving.
     * Here also calculate the new vector.
     * This approach is convenient for bouncing off walls.
     */
    void setDirection(double direction) {
        this.direction = direction;

        double angel = Math.toRadians(direction);
        dx = Math.cos(angel) * speed;
        dy = -Math.sin(angel) * speed;
    }

    /**
     * Draw ourself on the canvas
     */
    @Override
    void draw(Canvas canvas) {
        canvas.setPoint(x, y, 'O');
    }

    /**
     * Move ourself one step.
     */
    public void move() {
        if (isFrozen) return;

        x += dx;
        y += dy;

        checkRebound(1, Arkanoid.game.getWidth(), 1, Arkanoid.game.getHeight() + 5);
    }

    /**
     * Check if the ball flew past the wall.
     * If yes changing direction.
     */
    void checkRebound(int minx, int maxx, int miny, int maxy) {
        if (x < minx) {
            x = minx + (minx - x);
            dx = -dx;
        }

        if (x > maxx) {
            x = maxx - (x - maxx);
            dx = -dx;
        }

        if (y < miny) {
            y = miny + (miny - y);
            dy = -dy;
        }

        if (y > maxy) {
            y = maxy - (y - maxy);
            dy = -dy;
        }
    }

    /**
     * Start ball.
     * isFrozen = false.
     * Overdraw direction (dx,dy) of moving.
     */
    void start() {
        this.setDirection(direction);
        this.isFrozen = false;
    }
}
