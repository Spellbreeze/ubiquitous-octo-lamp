package com.draobemag.mariokart.Controllers;


import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.Classes.GameTileManager;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.GlobalDefine;
import com.draobemag.mariokart.Enums.GameTileType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javafx.scene.control.Button;

import javax.swing.*;

public class GameBoardView {

    @FXML
    private GridPane gameBoard;

    @FXML
    private Button moveButton;
    @FXML
    private Button moveFive;

    private Player currPlayer;

    private int currInd;

    private GameTileManager gameTileManager;

    @FXML
    private Label p1_speedlabel;

    @FXML
    private Label p2_speedlabel;

    @FXML
    private Label p3_speedlabel;

    @FXML
    private Label p4_speedlabel;

    @FXML
    private void movePlayer()  throws IOException {
        moveSpriteRandomNumTiles(currPlayer);
    }

    public Player testMakePaywallButtonVisible() {
        moveFive.setVisible(true);

        return currPlayer;
    }

    public void initialize() {
        gameBoard.setPrefSize(755, 755);
        ArrayList<Player> playerTempList = new ArrayList<Player>();
        playerTempList = GameManager.GetPlayerList();
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


        this.gameTileManager = new GameTileManager();
        this.gameTileManager.initializeGameTiles(gameBoard);
        this.gameTileManager.updatePlayerTile(this.currPlayer);

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

    public void moveSpriteNumTiles(Player player, int num_tiles) throws IOException {
        // TODO: def need to refactor this function
        ArrayList<Player> playerTempList = GameManager.GameManager().GetPlayerList();
        int oldPosition = player.getPosition();
        boolean isGameOver = player.move(num_tiles);
        if (isGameOver) {
            GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.WINNER));
            return;
        }
        gameTileManager.updateNonPlayerTile(oldPosition);
        GameTileType tileType =  gameTileManager.getGameTileType(player.getPosition());
        int playerMoney = player.getMoney();
        if (tileType == GameTileType.UNPAIDWALL) {
            if (playerMoney >= GlobalDefine.paywallTax) {
                Alert alert =
                        new Alert(Alert.AlertType.CONFIRMATION,
                                "Do you want to pay the paywall tax?",
                                ButtonType.YES,
                                ButtonType.NO
                                );
                alert.setTitle("Paywall Tax");
                java.util.Optional<ButtonType> result = alert.showAndWait();

                if (result.get() == ButtonType.YES) {
                    player.setMoney(playerMoney - GlobalDefine.paywallTax);
                    gameTileManager.setGameTileType(player.getPosition(), GameTileType.PAIDWALL);
                } else {
                    player.move(-1 * num_tiles);
                }
            } else {
                Alert alert =
                        new Alert(Alert.AlertType.ERROR,
                                "You don't have enough money to pay the paywall tax!");
                alert.setTitle("Insufficient Funds");
                java.util.Optional<ButtonType> result = alert.showAndWait();
                player.move(-1 * num_tiles);
            }
        } else if (tileType == GameTileType.CHANCE) {
            Random rand = new Random();
            int val = rand.nextInt(200);
            if (playerMoney >= 100) {
                GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.PICKNUMBER));
            } else {
                GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.RACESIM));
            }

        } else if (tileType == GameTileType.LOSEMONEY) {
            player.setMoney(playerMoney - 5);
        } else if (tileType == GameTileType.GAINMONEY) {
            player.setMoney(playerMoney + 5);
        }
        player.updateLabel();


        //TODO: Does this change the current player?
        // We probably want this to be tied to the button behavior
        if (this.currInd == playerTempList.size() - 1) {
            this.currInd = 0;
            this.currPlayer = playerTempList.get(this.currInd);
        } else {
            this.currInd += 1;
            this.currPlayer = playerTempList.get(this.currInd);
        }
        for (Player p: playerTempList) {
            gameTileManager.updatePlayerTile(p);
        }
    }


    // TODO: Consider moving the 'random' logic to a separate utility class
    public void moveSpriteRandomNumTiles(Player player) throws IOException {

        int MIN_MOVE = 1;
        int MAX_MOVE = 6;
        Random rand = new Random();
        int random_num_tiles_to_move = rand.nextInt(MAX_MOVE-MIN_MOVE + 1) + MIN_MOVE;
        //diceRoll.setText(player.getName() + " rolled: " + String.valueOf(random_num_tiles_to_move));
        
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

    public void movePlayer5(ActionEvent actionEvent) throws IOException {
        moveSpriteNumTiles(currPlayer, 5);
    }
}

