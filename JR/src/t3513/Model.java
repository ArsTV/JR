package t3513;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by DELL on 1/12/2018.
 */
public class Model {
    int score;
    int maxTile;
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private Stack previousStates = new Stack();
    private Stack previousScores = new Stack();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
        score = 0;
        maxTile = 2;
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private void addTile() {
        List<Tile> list = getEmptyTiles();
        if (list != null && list.size() != 0) {
            list.get((int) (list.size() * Math.random())).setValue(Math.random() < 0.9 ? 2 : 4);
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].isEmpty())
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

    private boolean compressTiles(Tile[] tiles) {
        boolean isChanged = false;
        Tile temp;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tiles[j].getValue() == 0 && tiles[j + 1].getValue() != 0) {
                    temp = tiles[j];
                    tiles[j] = tiles[j + 1];
                    tiles[j + 1] = temp;
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isChanged = false;
        for (int j = 0; j < 3; j++) {
            if (tiles[j].getValue() != 0 && tiles[j].getValue() == tiles[j + 1].getValue()) {
                tiles[j].setValue(tiles[j].getValue() * 2);
                tiles[j + 1].setValue(0);
                if (tiles[j].getValue() > maxTile) maxTile = tiles[j].getValue();
                score += tiles[j].getValue();
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

        return isChanged;
    }

    public void left() {
        boolean isChanged = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }
        if (isChanged)
            addTile();
    }

    public void right() {
        Tile[][] arrayTemp = new Tile[4][4];

        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        left();
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;


    }

    public void up() {
        Tile[][] arrayTemp = new Tile[4][4];

        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        left();
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;

    }

    public void down() {
        Tile[][] arrayTemp = new Tile[4][4];

        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        left();

        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
        arrayTemp = rotateArray(gameTiles);
        gameTiles = arrayTemp;
    }

    //Rotation a two dimensional array
    public Tile[][] rotateArray(Tile[][] array) {
        Tile[][] arrayRot = new Tile[FIELD_WIDTH][FIELD_WIDTH];

        for (int i = 0; i < arrayRot.length; i++) {
            for (int j = 0; j < arrayRot[0].length; j++) {
                arrayRot[i][j] = array[arrayRot[0].length - j - 1][i];
            }
        }

        return arrayRot;
    }

    public boolean canMove(){
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles[0].length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles[0].length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }
    

    private void saveState(Tile[][] tiles){
        Tile[][] matrixTemp = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                matrixTemp[i][j] = new Tile(tiles[i][j].getValue());
            }
        }

        previousStates.push(matrixTemp);
        int scoreTemp = score;
        previousScores.push(scoreTemp);

        isSaveNeeded = false;
    }

    public void rollback(){
        if(!previousStates.isEmpty() && !previousScores.isEmpty()){
            this.score = (int) previousScores.pop();
            this.gameTiles = (Tile[][])previousStates.pop();
        }

    }


}
