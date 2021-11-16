package com.draobemag.mariokart.Controllers;
import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.util.ArrayList;

public class WinnerController {

    @FXML
    private Label player1Location;
    @FXML
    private Label player2Location;
    @FXML
    private Label player3Location;
    @FXML
    private Label player4Location;
    @FXML
    private Label p1Location;
    @FXML
    private Label p2Location;
    @FXML
    private Label p3Location;
    @FXML
    private Label p4Location;
    @FXML
    private Label winnerName;
    @FXML
    private Button close;
    @FXML
    private Button playAgain;

    @FXML
    private Label minigameWinner;
    @FXML
    private Label moneyWinner;
    @FXML
    private Label generousWinner;





    public void initialize() {
        ArrayList<Player> rankedPlayers = GameManager.GetPlayerList();

        // Rank players in descending order based on position
        rankedPlayers.sort((p1, p2) -> p1.getPosition() - p2.getPosition() >= 0 ? -1 : 1);
        Player winner = rankedPlayers.get(0);
        winnerName.setText(winner.getName() + " reached the final tile first and hence is the WINNER!");

        int numOfPlayers = rankedPlayers.size();
        if (numOfPlayers > 1) {
            player1Location.setText("1. " + rankedPlayers.get(0).getName() + " location:");
            player1Location.setVisible(true);
            p1Location.setText(String.valueOf(rankedPlayers.get(0).getPosition()));
            p1Location.setVisible(true);

            player2Location.setText("2. " + rankedPlayers.get(1).getName()+ " location:");
            player2Location.setVisible(true);
            p2Location.setText(String.valueOf(rankedPlayers.get(1).getPosition()));
            p2Location.setVisible(true);
        }
        if (numOfPlayers > 2) {
            player3Location.setText("3. " + rankedPlayers.get(2).getName() + " location:");
            player3Location.setVisible(true);
            p3Location.setText(String.valueOf(rankedPlayers.get(2).getPosition()));
            p3Location.setVisible(true);
        }
        if (numOfPlayers > 3) {
            player4Location.setText("4. " + rankedPlayers.get(3).getName() + " location:");
            player4Location.setVisible(true);
            p4Location.setText(String.valueOf(rankedPlayers.get(3).getPosition()));
            p4Location.setVisible(true);
        }

        Player miniWin = null, moneyWin = null, generousWin = null;
        for (Player player : GameManager.GetPlayerList())
        {
            if (miniWin == null) miniWin = player;
            if (moneyWin == null) moneyWin = player;
            if (generousWin == null) generousWin = player;

            if (player.getMinigameWins() > miniWin.getMinigameWins())
                miniWin = player;
            if (player.getMoney() > miniWin.getMoney()) moneyWin = player;
            if (player.getPaidPaywalls() > miniWin.getPaidPaywalls())
                generousWin = player;
        }
        minigameWinner.setText("The Best Minigame Winner: "
                + miniWin.getName());
        moneyWinner.setText("The Most Speediest Winner: "
                + moneyWin.getName());
        generousWinner.setText("The Most Generous Paywall Payer: "
                + generousWin.getName());
    }


    @FXML
    protected void closeGame() {
        System.exit(0);
    }
    @FXML
    protected void playAgain() throws IOException {
        GameManager.startGame(GameManager.GameManager().stage);
    }
}