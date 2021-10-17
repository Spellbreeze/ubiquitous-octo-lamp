package com.draobemag.mariokart.Controllers;


import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.GlobalDefine;
import com.draobemag.mariokart.Singletons.GameManager;
import eu.hansolo.tilesfx.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.scene.control.Button;

public class GameBoardView {

    @FXML
    private GridPane gameBoard;

    @FXML
    private Button moveButton;

    private Player currPlayer;

    private int currInd;

    @FXML
    private Label p1_speedlabel;

    @FXML
    private Label p2_speedlabel;

    @FXML
    private Label p3_speedlabel;

    @FXML
    private Label p4_speedlabel;

    @FXML
    private void movePlayer() {
        moveSpriteRandomNumTiles(currPlayer);
    }

    public void initialize() {
        gameBoard.setPrefSize(755, 755);
        ArrayList<Player> playerTempList = new ArrayList<Player>();
        playerTempList = GameManager.getPlayerList();
        int count = 0;
        Label[] localLabels = {
                p1_speedlabel,
                p2_speedlabel,
                p3_speedlabel,
                p4_speedlabel
        };
        for (count = 0; count < playerTempList.size(); count++) {
            playerTempList.get(count).setLabel(localLabels[count]);
            playerTempList.get(count).updateLabel();
        }
        for (;count < 4; count++) {
            localLabels[count].setText("");
        }
        GameManager.GameManager().SetPlayerList(playerTempList);
      
        this.currInd = GameManager.getStartPoint() - 1;
        //this.currPlayer = this.players.get(this.currInd);
        this.currPlayer = playerTempList.get(GameManager.getStartPoint() - 1);

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

    public void moveSpriteNumTiles(Player player, int num_tiles) {
        ArrayList<Player> playerTempList = GameManager.GameManager().GetPlayerList();
        Rectangle temp = this.getTile(GlobalDefine.coords[player.getPosition()].y,GlobalDefine.coords[player.getPosition()].x);
        if (player.getPosition() % 12 == 0) {
            temp.setFill(Color.ORANGE);
        } else if (player.getPosition() % 2 == 0) {
            temp.setFill(Color.PALEVIOLETRED);
        } else {
            temp.setFill(Color.GREENYELLOW);
        }
        player.move(num_tiles);
        temp = this.getTile(GlobalDefine.coords[player.getPosition()].y,GlobalDefine.coords[player.getPosition()].x);
        temp.setFill(new ImagePattern(player.getSprite()));

        this.currPlayer.updateMoney();
        this.currPlayer.updateLabel();

        //TODO: Does this change the current player?
        // We probably want this to be tied to the button behavior
        if (this.currInd == playerTempList.size() - 1) {
            this.currInd = 0;
            this.currPlayer = playerTempList.get(this.currInd);
        } else {
            this.currInd += 1;
            this.currPlayer = playerTempList.get(this.currInd);
        }
    }


    // TODO: Consider moving the 'random' logic to a separate utility class
    public void moveSpriteRandomNumTiles(Player player) {

        int MIN_MOVE = 1;
        int MAX_MOVE = 6;
        Random rand = new Random();
        int random_num_tiles_to_move = rand.nextInt(MAX_MOVE-MIN_MOVE + 1) + MIN_MOVE;

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Dice Roll");
        alert.setContentText(String.format("You rolled a %d!", random_num_tiles_to_move));
        alert.showAndWait();

        moveSpriteNumTiles(player, random_num_tiles_to_move);
    }
  
    public Rectangle getTile(int x, int y) {
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
