package t2413;

/*
*  Base class for all objects of the game.
**/
public abstract class BaseObject {
    //object coordinates
    protected double x;
    protected double y;
    //object radius
    protected double radius;

    protected BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     * Draw our object on the canvas.
     */
    abstract void draw(Canvas canvas);

    /**
     * Move this for one turn
     */
    abstract void move();

    /**
     *  Check if x or y go out of the borders
     */
    void checkBorders(double minx, double maxx, double miny, double maxy) {
        if (x < minx) x = minx;
        if (x > maxx) x = maxx;
        if (y < miny) y = miny;
        if (y > maxy) y = maxy;
    }

    /**
     * Check intersection transmitted object(0) with our object(this)
     */
    boolean isIntersec(BaseObject o) {
        double dx = x - o.x;
        double dy = y - o.y;
        double destination = Math.sqrt(dx * dx + dy * dy);
        double destination2 = Math.max(radius, o.radius);
        return destination <= destination2;
    }
}
