package t3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by DELL on 1/12/2018.
 */
public class Controller extends KeyAdapter {
    Model model;
    View view;
    public static final int WINNING_TILE = 2048;

    public Controller(Model model) {
        this.model = model;
        view = new View(this);
    }

    public Tile[][] getGameTiles() {
        return model.getGameTiles();
    }

    public int getScore() {
        return model.score;
    }

    public void resetGame() {
        model.score = 0;
        view.isGameWon = false;
        view.isGameLost = false;
        model.resetGameTiles();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            resetGame();
        } else {
            if (!model.canMove()) {
                view.isGameLost = true;
            } else {
                if (!view.isGameLost && !view.isGameWon) {
                    switch (keyEvent.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            model.left();
                            break;
                        case KeyEvent.VK_RIGHT:
                            model.right();
                            break;
                        case KeyEvent.VK_UP:
                            model.up();
                            break;
                        case KeyEvent.VK_DOWN:
                            model.down();
                            break;
                    }
                }
                if (model.maxTile == WINNING_TILE) {
                    view.isGameWon = true;
                }
            }
        }
        view.repaint();
    }
}
