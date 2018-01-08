package t2413;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Main class
 */
public class Arkanoid {
    //Width and height
    private int width;
    private int height;

    //List of bricks
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    //Ball
    private Ball ball;
    //Stand
    private Stand stand;

    //Is game over?
    private boolean isGameOver = false;

    public Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    /**
     * Draw borders and all objects on the canvas.
     */
    void draw(Canvas canvas) {
        drawBorders(canvas);

        //Draw bricks.
        for (Brick brick : bricks) {
            brick.draw(canvas);
        }

        //Draw ball.
        ball.draw(canvas);

        //Draw stand.
        stand.draw(canvas);

    }

    /**
     * Draw borders on the canvas.
     */
    private void drawBorders(Canvas canvas) {
        //Draw game.
        for (int i = 0; i < width + 2; i++) {
            for (int j = 0; j < height + 2; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    /**
     * The main program loop.
     */
    void run() throws Exception {
        //Creating canvas for drawing.
        Canvas canvas = new Canvas(width, height);

        //Creating object of KeyboardObserver and starting him.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //Execute the loop until the game is finished.
        while (!isGameOver) {
            //If the KeyboardObserver has events.
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                //Left arrow key.
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                    //Right arrow key.
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                    //Space key starting the game.
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            //Move all objects.
            move();

            //Check bumping.
            checkBricksBump();
            checkStandBump();

            //Check if the ball fly through the bottom.
            checkEndGame();

            //Drawing all objects.
            canvas.clear();
            draw(canvas);
            canvas.print();

            //Pause.
            Thread.sleep(300);
        }

        //Printing "Game Over" message.
        System.out.println("Game Over!");
    }

    /**
     * Move ball and stand.
     */
    public void move() {
        ball.move();
        stand.move();
    }

    /**
     * Check the collision the ball with the bricks.
     * If the collision occurred the ball flies in a random direction from 0 to 360 degrees.
     */
    void checkBricksBump() {
        for (Brick brick : new ArrayList<Brick>(bricks)) {
            if (ball.isIntersec(brick)) {
                double angle = Math.random() * 360;
                ball.setDirection(angle);

                bricks.remove(brick);
            }
        }
    }

    /**
     * Check the collision the ball with the stand.
     * If the collision occurred the ball flies in a random direction upward from 80 to 100 degrees.
     */
    void checkStandBump() {
        if (ball.isIntersec(stand)) {
            double angle = 90 + 20 * (Math.random() - 0.5);
            ball.setDirection(angle);
        }
    }

    /**
     * Check if the ball has gone off the bottom.
     * If yes the game is over (isGameOver = true)
     */
    void checkEndGame() {
        if (ball.getY() > height && ball.getDy() > 0)
            isGameOver = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static Arkanoid game;

    public static void main(String[] args) throws Exception {
        game = new Arkanoid(20, 30);

        Ball ball = new Ball(10, 29, 2, 95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        game.run();
    }
}