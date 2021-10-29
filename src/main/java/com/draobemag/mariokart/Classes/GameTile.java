package com.draobemag.mariokart.Classes;

import javafx.scene.shape.Rectangle;
import com.draobemag.mariokart.Enums.GameTileType;

public class GameTile {
    public Rectangle tile;
    public GameTileType tileType;


    public GameTile(Rectangle tile, GameTileType tileType) {
       this.tile = tile;
       this.tileType = tileType;
    }

}
