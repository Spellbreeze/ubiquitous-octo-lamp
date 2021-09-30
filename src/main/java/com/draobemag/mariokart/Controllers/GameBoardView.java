package com.draobemag.mariokart.Controllers;


import com.draobemag.mariokart.Classes.Player;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameBoardView {

    @FXML
    private GridPane gameBoard;

    @FXML
    private Player player1;

    public void initialize() {
        gameBoard.setPrefSize(755, 755);

        player1 = new Player(new Image("file:src/main/resources/images/mario_sprite.png", 40, 40, false, false));
        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0 || j == 0 || i == 9 || j == 9) {
                    Rectangle tile = new Rectangle(i, j, 50, 50);
                    tile.setFill(Color.BURLYWOOD);
                    tile.setStroke(Color.BLACK);

                    Text text = new Text(String.format(i + "," + j));
                    if (i == 0 && j == 0) {
                        tile.setFill(new ImagePattern(player1.getSprite()));
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

    public void moveSprite() {
        Rectangle temp = this.getTile(player1);
        temp.setFill(Color.BURLYWOOD);
        player1.move();
        temp = this.getTile(player1);
        temp.setFill(new ImagePattern(player1.getSprite()));
        System.out.println(temp.getFill());
    }

    public void moveSprite3() {
        for (int i = 0; i < 3; i++) {
            this.moveSprite();
        }
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
