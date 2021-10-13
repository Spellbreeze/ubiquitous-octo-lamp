package com.draobemag.mariokart.Controllers;


import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.GlobalDefine;
import com.draobemag.mariokart.Singletons.GameManager;
import eu.hansolo.tilesfx.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBoardView {

    @FXML
    private GridPane gameBoard;

    @FXML
    private HBox layout;

    private ArrayList<Player> players;

    @FXML
    private javafx.scene.control.Button moveButton;

    @FXML
    private javafx.scene.control.Button moveButton3;

    private Player currPlayer;

    private int currInd;

    public void initialize() {
        gameBoard.setPrefSize(755, 755);
        this.players = new ArrayList<Player>();
        HBox temp = new HBox();
        layout.getChildren().add(temp);
        this.players = GameManager.getPlayerList();
        for (int i = 0; i < this.players.size(); i++) {
            temp.getChildren().add(this.players.get(i).getLabel());
            this.players.get(i).updateLabel();
            this.players.get(i).updateMoney();
        }
        this.currInd = GameManager.getStartPoint() - 1;
        this.currPlayer = this.players.get(this.currInd);
        moveButton.setOnAction(event -> {
            moveSprite(this.currPlayer);
            this.currPlayer.updateMoney();
            this.currPlayer.updateLabel();
            if (this.currInd == this.players.size() - 1) {
                this.currInd = 0;
                this.currPlayer = this.players.get(this.currInd);
            } else {
                this.currInd += 1;
                this.currPlayer = this.players.get(this.currInd);
            }
        });

        moveButton3.setOnAction(event -> {
            moveSprite3(this.currPlayer);
            this.currPlayer.updateMoney();
            this.currPlayer.updateLabel();
            if (this.currInd == this.players.size() - 1) {
                this.currInd = 0;
                this.currPlayer = this.players.get(this.currInd);
            } else {
                this.currInd += 1;
                this.currPlayer = this.players.get(this.currInd);
            }
        });

        int count = 0;
        for (int i = 0; i < GlobalDefine.boardMaxL + 1; i++) {
            for (int j = 0; j < GlobalDefine.boardMaxL + 1; j++) {
                if (Arrays.asList(GlobalDefine.coords).contains(new Point(j,i))) {
                    Rectangle tile = new Rectangle(i, j, 50, 50);
                    if (count % 12 == 0) {
                        tile.setFill(Color.ORANGE);
                    } else if (count % 2 == 0 && count != 0) {
                        tile.setFill(Color.PALEVIOLETRED);
                    } else {
                        tile.setFill(Color.GREENYELLOW);
                    }
                    tile.setStroke(Color.BLACK);
                    if (i == 1 && j == 1) {
                        tile.setFill(new ImagePattern(this.currPlayer.getSprite()));
                    }
                    GridPane.setRowIndex(tile, i);
                    GridPane.setColumnIndex(tile, j);
                    gameBoard.getChildren().add(count, tile);
                    count += 1;
                }
            }
        }

        for (int i = 0; i < GlobalDefine.boardMaxL + 1; i++) {
            for (int j = 0; j < GlobalDefine.boardMaxL + 1; j++) {
                if (!Arrays.asList(GlobalDefine.coords).contains(new Point(j,i))) {
                    Rectangle tile = new Rectangle(i, j, 50, 50);
                    tile.setFill(Color.FORESTGREEN);
                    tile.setStroke(Color.GRAY);
                    tile.setFill(new ImagePattern(new Image("file:src/main/resources/images/grass.png")));
                    GridPane.setRowIndex(tile, i);
                    GridPane.setColumnIndex(tile, j);
                    gameBoard.getChildren().add(count, tile);
                    count += 1;
                }
            }
        }
    }

    public void moveSprite(Player player) {
        Rectangle temp = this.getTile(GlobalDefine.coords[player.getPosition()].y,GlobalDefine.coords[player.getPosition()].x);
        if (player.getPosition() % 12 == 0) {
            temp.setFill(Color.ORANGE);
        } else if (player.getPosition() % 2 == 0) {
            temp.setFill(Color.PALEVIOLETRED);
        } else {
            temp.setFill(Color.GREENYELLOW);
        }
        player.move();
        temp = this.getTile(GlobalDefine.coords[player.getPosition()].y,GlobalDefine.coords[player.getPosition()].x);
        temp.setFill(new ImagePattern(player.getSprite()));
    }

    public void moveSprite3(Player player) {
        for (int i = 0; i < 3; i++) {
            this.moveSprite(player);
        }
    }

    Rectangle getTile(int x, int y) {
        List<Node> temp = gameBoard.getChildren().stream().filter(
                new Predicate<Node>() {
                    @Override
                    public boolean test(Node node) {
                        try {
                            Rectangle temp = (Rectangle) node;
                            return temp.getX() == x && temp.getY() == y;
                        } catch (Exception e) {
                            return false;
                        }
                    }
                }
        ).collect(Collectors.toList());
        return (Rectangle) temp.get(0);
    }
}
