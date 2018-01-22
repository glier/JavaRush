package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score = 0;
    int maxTile = 2;

    public Model() {

        resetGameTiles();

    }

    private void addTile() {

        List<Tile> emptyList = getEmptyTiles();
        if (emptyList != null && emptyList.size() != 0) {
            Tile tile = emptyList.get((int) (Math.random() * emptyList.size()));
            int tileWeight = Math.random() < 0.9 ? 2 : 4;
            tile.value = tileWeight;
        }
    }

    private List<Tile> getEmptyTiles() {

        ArrayList<Tile> emptyTilesList = new ArrayList<Tile>();
        for (int i = 0; i < FIELD_WIDTH; i++){
            for (int j = 0; j < FIELD_WIDTH; j++){
                if (gameTiles[i][j].isEmpty()) {
                    emptyTilesList.add(gameTiles[i][j]);
                }
            }
        }
        return emptyTilesList;
    }

    protected void resetGameTiles() {
        this.gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                this.gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean changes = false;

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value == 0 && i < tiles.length - 1 && tiles[i + 1].value != 0) {
                Tile temp = tiles[i];
                tiles[i] = tiles[i + 1];
                tiles[i + 1] = temp;
                i = -1;
                changes = true;
            }
        }

        return changes;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean changes = false;

        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].value != 0 && i != tiles.length - 1 && (tiles[i].value == tiles[i + 1].value)) {
                tiles[i].value = tiles[i].value * 2;
                tiles[i + 1].value = 0;

                if (maxTile < tiles[i].value) maxTile = tiles[i].value;
                score += tiles[i].value;
                compressTiles(tiles);
                changes = true;
            }
        }

        return changes;
    }

    private void rotate() {
        for (int k = 0; k < 2; k++) {
            for (int j = k; j < 3 - k; j++) {
                Tile tmp = gameTiles[k][j];
                gameTiles[k][j] = gameTiles[j][3 - k];
                gameTiles[j][3 - k] = gameTiles[3 - k][3 - j];
                gameTiles[3 - k][3 - j] = gameTiles[3 - j][k];
                gameTiles[3 - j][k] = tmp;
            }
        }
    }

    public void left() {

        boolean isChanged = false;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }

        if (isChanged) addTile();
    }

    public void right() {

        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up() {

        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void down() {

        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }
}
