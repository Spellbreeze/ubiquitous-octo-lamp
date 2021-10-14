package com.draobemag.mariokart.Controllers;


import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.GlobalDefine;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBoardView {

    @FXML
    private GridPane gameBoard;

    private ArrayList<Player> players;

    @FXML
    private javafx.scene.control.Button moveButton;

    private Player currPlayer;

    public void initialize() {
        gameBoard.setPrefSize(755, 755);

        this.players = new ArrayList<Player>();
        for (int i = 0; i < GameManager.getNumPlayers(); i++) {
            this.players.add(new Player(new Image(GlobalDefine.sprites.get(GameManager.getSpritesList().get(i) - 1), 40, 40, false, false)));
        }

        this.currPlayer = this.players.get(GameManager.getStartPoint() - 1);

        moveButton.setOnAction(event -> {moveSpriteRandomNumTiles(this.currPlayer);});

        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || j == 0 || i == 9 || j == 9) {
                    Rectangle tile = new Rectangle(i, j, 50, 50);
                    tile.setFill(Color.BURLYWOOD);
                    tile.setStroke(Color.BLACK);

                    Text text = new Text(String.format(i + "," + j));
                    if (i == 0 && j == 0) {
                        tile.setFill(new ImagePattern(this.currPlayer.getSprite()));
                    }
                    text.setFont(Font.font(10));
                    gameBoard.add(new StackPane(tile, text), i, j);

                    GridPane.setRowIndex(tile, i);
                    GridPane.setColumnIndex(tile, j);
                    gameBoard.getChildren().add(count, tile);
                    count += 1;
                }
            }
        }
        System.out.println(gameBoard.getChildren());
    }

    public void moveSprite(Player player) {
        Rectangle temp = this.getTile(player);
        temp.setFill(Color.BURLYWOOD);
        player.move();
        temp = this.getTile(player);
        temp.setFill(new ImagePattern(player.getSprite()));
    }

    public void moveSpriteNumTiles(Player player, int num_tiles) {
        for (int i = 0; i < num_tiles; i++) {
            this.moveSprite(player);
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

    Rectangle getTile(Player player) {
        List<Node> temp = gameBoard.getChildren().stream().filter(
                new Predicate<Node>() {
                    @Override
                    public boolean test(Node node) {
                        try {
                            Rectangle temp = (Rectangle) node;
                            return temp.getX() == player.getX() && temp.getY() == player.getY();
                        } catch (Exception e) {
                            return false;
                        }
                    }
                }
        ).collect(Collectors.toList());
        return (Rectangle) temp.get(0);
    }
}
