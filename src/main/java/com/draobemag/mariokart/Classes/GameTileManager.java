package com.draobemag.mariokart.Classes;

import com.draobemag.mariokart.Classes.GameTile;
import com.draobemag.mariokart.GlobalDefine;
import com.draobemag.mariokart.Enums.GameTileType;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class GameTileManager {
    private Map<Integer, GameTile> gameTileList;
    private static int PAYWALL_TILE_LOC = 5;
    public static int CHANCE_TILE_LOC = 12;
    private static int LOSE_TILE_LOC = 2;
    private static int MINIGAME_TILE_LOC = 3;

    public GameTileManager() {
        this.gameTileList = new HashMap<>();
    }

    public void initializeGameTiles(GridPane gameBoard) {
        int gameTileCount = 0;
        for (int i = 0; i < GlobalDefine.boardMaxL + 1; i++) {
            for (int j = 0; j < GlobalDefine.boardMaxL + 1; j++) {
                int tilePosition = Arrays.asList(GlobalDefine.coords).indexOf(new Point(j,i));
                if (tilePosition != -1) {
                    Rectangle tile = new Rectangle(i, j, 50, 50);
                    GameTileType tileType;
                    if (tilePosition % PAYWALL_TILE_LOC == 0) {
                        tile.setFill(new ImagePattern(new Image(GlobalDefine.dollarsignUrl)));
                        tileType = GameTileType.UNPAIDWALL;
                    }
                    else if (tilePosition % CHANCE_TILE_LOC == 0) {
                        tile.setFill(javafx.scene.paint.Color.ORANGE);
                        tileType = GameTileType.CHANCE;
                    } else if (tilePosition % LOSE_TILE_LOC == 0) {
                        tile.setFill(javafx.scene.paint.Color.PALEVIOLETRED);
                        tileType = GameTileType.LOSEMONEY;
                    }
                    else if (tilePosition % MINIGAME_TILE_LOC == 0) {
                        tile.setFill(Color.ROYALBLUE);
                        tileType = GameTileType.MINIGAME;

                    }
                    else {
                        tile.setFill(javafx.scene.paint.Color.GREENYELLOW);
                        tileType = GameTileType.GAINMONEY;
                    }
                    tile.setStroke(Color.BLACK);
                    GameTile gameTile = new GameTile(tile, tileType);
                    this.gameTileList.put(tilePosition, gameTile);

                    GridPane.setRowIndex(tile, i);
                    GridPane.setColumnIndex(tile, j);
                    gameBoard.getChildren().add(gameTileCount, tile);
                    gameTileCount += 1;
                }
            }
        }
    }

    public void updateNonPlayerTile(int position) {
        if (position % PAYWALL_TILE_LOC == 0) {
            this.gameTileList.get(position).tile.setFill(new ImagePattern(new Image(GlobalDefine.dollarsignUrl)));
        } else if (position % CHANCE_TILE_LOC == 0) {
            this.gameTileList.get(position).tile.setFill(Color.ORANGE);
        } else if (position % LOSE_TILE_LOC == 0) {
            this.gameTileList.get(position).tile.setFill(Color.PALEVIOLETRED);
        } else {
            this.gameTileList.get(position).tile.setFill(Color.GREENYELLOW);
        }

    }
    public void updatePlayerTile(Player player) {
        this.gameTileList
                .get(player.getPosition())
                .tile
                .setFill(new ImagePattern(player.getSprite()));
    }

    public GameTileType getGameTileType(int position) {
       return this.gameTileList.get(position).tileType;
    }
    public void setGameTileType(int position, GameTileType tileType) {
        this.gameTileList.get(position).tileType = tileType;
    }
}
