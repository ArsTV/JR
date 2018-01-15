package t2213;

/**
 * Created by DELL on 1/15/2018.
 */
public class Tetris {

    private Field field;
    private Figure figure;
    protected static Tetris game;

    public Field getField() {
        return field;
    }

    public Figure getFigure() {
        return figure;
    }

    public static void main(String[] args) {
        game = new Tetris();
        game.run();
        
    }

    public void run(){}

    public void step(){}
}
