package t3513;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 1/12/2018.
 */
public class Model {
    int score;
    int maxTile;
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    public Model() {
        resetGameTiles();
        score = 0;
        maxTile = 2;
    }

    private void addTile() {
        List<Tile> list = getEmptyTiles();
        int random = 0;
        if (list != null && list.size() != 0) {
            list.get((int) (list.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if(gameTiles[i][j].isEmpty())
                    list.add(gameTiles[i][j]);
            }
        }
        return list;
    }

    protected void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private void compressTiles(Tile[] tiles) {
        for (int k = 0; k < tiles.length; k++) {
            for (int i = 0; i < tiles.length - 1; i++) {
                if (tiles[i].getValue() == 0) {
                    for (int j = i; j < tiles.length - 1; j++) {
                        if (tiles[j].getValue() == 0) {
                            tiles[j].setValue(tiles[j + 1].getValue());
                            tiles[j + 1].setValue(0);
                        }
                    }
                }
            }
        }
    }

    private void mergeTiles(Tile[] tiles) {
        int order = 0;
        boolean isChanged = false;
        for (int i = 0; i < tiles.length - 1; i++) {
            if (tiles[i].getValue() == tiles[i + 1].getValue()) {
                tiles[i].setValue(0);
                tiles[order].setValue(tiles[i + 1].getValue() * 2);
                tiles[i + 1].setValue(0);

                if(tiles[order].getValue() > maxTile) {
                    maxTile = tiles[order].getValue();
                }
                score += tiles[order].getValue();
                order++;
                isChanged = true;
            }
        }

        if (isChanged) {
            Tile temp;
            for (int i = 0; i < 3; i++) {
                if (tiles[i].getValue() == 0 && tiles[i + 1].getValue() != 0) {
                    temp = tiles[i];
                    tiles[i] = tiles[i + 1];
                    tiles[i + 1] = temp;
                }
            }
        }
    }

}
