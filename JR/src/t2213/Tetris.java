package t2213;

import java.awt.event.KeyEvent;

/**
 * Created by DELL on 1/15/2018.
 * Tetris class contains the main functionality of the game.
 */

public class Tetris {

    private Field field;                //Field with cells
    private Figure figure;              //Figure

    private boolean isGameOver;         //Boolean if the game is over

    public Tetris(int width, int height) {
        field = new Field(width, height);
        figure = null;
    }

    /**
     * Getter for field.
     */
    public Field getField() {
        return field;
    }

    /**
     * Getter for figure.
     */
    public Figure getFigure() {
        return figure;
    }

    /**
     * Main game loop.
     */
    public void run() throws Exception {
        //Create the object of KeyboardObserver and start it.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //set the initial value of the variable "game over" to FALSE
        isGameOver = false;
        //create the first figure in the middle from the top: x - half the width, y - 0.
        figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0);

        //until the game is not over
        while (!isGameOver) {
            //if observer has events about keys pushed
            if (keyboardObserver.hasKeyEvents()) {
                //get the first event from the queue
                KeyEvent event = keyboardObserver.getEventFromTop();
                //If equal to the 'q' character, exit the game.
                if (event.getKeyChar() == 'q') return;
                //If the "arrow to the left" - move the figure to the left
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    figure.left();
                    //If the "arrow to the right" - move the figure to the right
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    figure.right();
                    //If the key code is 12 ("digit 5 on the additional keyboard") - rotate the figure
                else if (event.getKeyCode() == 12)
                    figure.rotate();
                    //If the "space" - the figure falls down to the maximum
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    figure.downMaximum();
            }

            step();             //majing next step
            field.print();      //print the state of the game field
            Thread.sleep(300);  //pause 300 milliseconds
        }

        //Print message "Game Over"
        System.out.println("Game Over");
    }

    public void step() {
        //Move down the figure
        figure.down();

        //if it is impossible to set the figure
        if (!figure.isCurrentPositionAvailable()) {
            figure.up();                    //move it back
            figure.landed();                //landing it

            isGameOver = figure.getY() <= 1;//if the figure landed on the top the game is over

            field.removeFullLines();        //delete the filled lines

            figure = FigureFactory.createRandomFigure(field.getWidth() / 2, 0); //creating new figure
        }
    }

    /**
     * Setter for figure
     */
    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    /**
     * Setter for field
     */
    public void setField(Field field) {
        this.field = field;
    }

    public static Tetris game;

    public static void main(String[] args) throws Exception {
        game = new Tetris(10, 20);
        game.run();
    }
}
