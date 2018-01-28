package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Model {

    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score = 0;
    int maxTile = 2;

    Stack previousStates = new Stack();
    Stack previousScores = new Stack();
    boolean isSaveNeeded = true;

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

        if (isSaveNeeded) saveState(gameTiles);

        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i])) {
                isChanged = true;
            }
        }

        if (isChanged) addTile();

        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotate();
        rotate();
        left();
        rotate();
        rotate();
    }

    public void up() {
        saveState(gameTiles);
        rotate();
        left();
        rotate();
        rotate();
        rotate();
    }

    public void down() {
        saveState(gameTiles);
        rotate();
        rotate();
        rotate();
        left();
        rotate();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (!getEmptyTiles().isEmpty())
            return true;
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j - 1].value)
                    return true;
            }
        }
        for (int j = 0; j < gameTiles.length; j++) {
            for (int i = 1; i < gameTiles.length; i++) {
                if (gameTiles[i][j].value == gameTiles[i - 1][j].value)
                    return true;
            }
        }
        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] saveTiles = new Tile[tiles.length][tiles[0].length];

        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                saveTiles[i][j] = new Tile(tiles[i][j].value);
            }
        }

        previousStates.push(saveTiles);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {

        if (!previousStates.isEmpty() && !previousScores.isEmpty()) {
            this.score = (int) previousScores.pop();
            this.gameTiles = (Tile[][]) previousStates.pop();
        }
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;

        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
        }
    }
}
