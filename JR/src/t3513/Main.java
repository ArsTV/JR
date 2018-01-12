package t3513;

/**
 * Created by DELL on 1/12/2018.
 */
public class Main {
    static int score = 0;
    static int maxTile = 0;

    public static void main(String[] args) {

        Tile[] t = new Tile[4];
        t[0] = new Tile(4);
        t[1] = new Tile(4);
        t[2] = new Tile(4);
        t[3] = new Tile(0);

        compressTiles(t);
        for (Tile tt : t) {
            System.out.print(tt.getValue() + " ");
        }
        System.out.println();
        mergeTiles(t);
        for (Tile tt : t) {
            System.out.print(tt.getValue() + " ");
        }
        System.out.println();
        System.out.println(score + " " + maxTile);
    }

    private static void mergeTiles(Tile[] tiles) {
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

    private static void compressTiles(Tile[] tiles) {
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


}