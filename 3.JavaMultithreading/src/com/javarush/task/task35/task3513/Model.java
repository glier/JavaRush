package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static final int FIELD_WIDTH = 4;

    private Tile[][] gameTiles;

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


}
