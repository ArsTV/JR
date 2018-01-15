package t3513;

import java.awt.event.KeyAdapter;

/**
 * Created by DELL on 1/12/2018.
 */
public class Controller extends KeyAdapter {
    Model model;
    View view;

    public Tile[][] getGameTiles(){
        return model.getGameTiles();
    }

    public int getScore(){
        return model.score;
    }
}