package com.draobemag.mariokart.Controllers;
import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

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





    public void initialize() {
<<<<<<< HEAD
        ArrayList<Player> players = GameManager.getPlayerList();
        for (Player p : players) {
            if (p.getPosition() == 59)
                winnerName.setText(p.getName() + " reached tile 59/59 first and hence is the WINNER!");
        }

        //p1Location.setText(String.valueOf(players.get(0).getPosition()));
        int numOfPlayers = players.size();
        System.out.print(numOfPlayers);
        switch (numOfPlayers) {
            case 2:
                player1Location.setText(players.get(0).getName() + " location:");
                player1Location.setVisible(true);
                p1Location.setText(String.valueOf(players.get(0).getPosition()));
                p1Location.setVisible(true);
                player1Location.setText(players.get(1).getName()+ " location:");
                player2Location.setVisible(true);
                p2Location.setText(String.valueOf(players.get(1).getPosition()));
                p2Location.setVisible(true);
                break;
            case 3:
                player1Location.setText(players.get(0).getName() + " location:");
                player1Location.setVisible(true);
                p1Location.setText(String.valueOf(players.get(0).getPosition()));
                p1Location.setVisible(true);
                player2Location.setText(players.get(1).getName() + " location:");
                player2Location.setVisible(true);
                p2Location.setText(String.valueOf(players.get(1).getPosition()));
                p2Location.setVisible(true);
                player3Location.setText(players.get(2).getName() + " location:");
                player3Location.setVisible(true);
                p3Location.setText(String.valueOf(players.get(2).getPosition()));
                p3Location.setVisible(true);
                break;
            case 4:
                player1Location.setText(players.get(0).getName() + " location:");
                player1Location.setVisible(true);
                p1Location.setText(String.valueOf(players.get(0).getPosition()));
                p1Location.setVisible(true);
                player2Location.setText(players.get(1).getName() + " location:");
                player2Location.setVisible(true);
                p2Location.setText(String.valueOf(players.get(1).getPosition()));
                p2Location.setVisible(true);
                player3Location.setText(players.get(2).getName() + " location:");
                player3Location.setVisible(true);
                p3Location.setText(String.valueOf(players.get(2).getPosition()));
                p3Location.setVisible(true);
                player4Location.setText(players.get(3).getName() + " location:");
                player4Location.setVisible(true);
                p4Location.setText(String.valueOf(players.get(3).getPosition()));
                p4Location.setVisible(true);
                break;
        }

=======
        ArrayList<Player> rankedPlayers = GameManager.getPlayerList();

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
>>>>>>> 4df051c28bc4e525557f54096faa00a501200fe2
    }
    @FXML
    protected void closeGame() {
        System.exit(0);
    }
    @FXML
    protected void playAgain() throws IOException {
<<<<<<< HEAD
        GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.WELCOME));
=======
        GameManager.startGame(GameManager.GameManager().stage);
>>>>>>> 4df051c28bc4e525557f54096faa00a501200fe2
    }
}