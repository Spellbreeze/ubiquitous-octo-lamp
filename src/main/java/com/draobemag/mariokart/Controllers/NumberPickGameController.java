package com.draobemag.mariokart.Controllers;
import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.lang.Math;

import java.io.IOException;
import java.util.ArrayList;

public class NumberPickGameController {
    @FXML
    private TextField playerGuess;
    @FXML
    private Button go;
    @FXML
    private Label invalidLabel;
    private int number;
    private  Player currPlayer;
    private ArrayList<Player> playerTempList = new ArrayList<Player>();
    private ArrayList<Integer> playerGuesses = new ArrayList<Integer>();
    int count = 0;

    public void initialize() {
        playerTempList = GameManager.GetPlayerList();
        this.currPlayer = playerTempList.get(0);
        int min = 1;
        int max = 100;
        number = (int)(Math.random() * (max-min+1)+min);
        System.out.println("Number is: " + number);
    }

    @FXML
    private void check() throws IOException {
        checker(currPlayer);
        if (go.getText().contentEquals("Finished")) {
            // Put winner text then quit ig
            GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.MAIN));
        } else if (count >= playerTempList.size()) {
            int best = 200;
            int bestInd = -1;
            for (int i = 0; i < playerTempList.size(); i++) {
                int dist = Math.abs(playerGuesses.get(i) - number);
                if (dist < best) {
                    best = dist;
                    bestInd = i;
                }
            }
            playerTempList.get(bestInd).setMoney(playerTempList.get(bestInd).getMoney() + 15);
            playerGuess.setVisible(false);
            go.setText("Finished");
            invalidLabel.setText("Congratulations player " + playerTempList.get(bestInd).getName() + " your guess was closest.\n" +
                    "The answer was " + String.valueOf(number));
            playerTempList.get(bestInd).addMinigameWin();
        }
    }
    private void checker(Player player) throws IOException {
        if (!isNumeric(playerGuess.getText()) || Integer.parseInt(playerGuess.getText()) > 100 ||  Integer.parseInt(playerGuess.getText()) < 1) {
            invalidLabel.setText("Please pick a valid number between 1 and 100. No decimals!");
            return;
        }
        invalidLabel.setText("");
        int pG = Integer.parseInt(playerGuess.getText());
        playerGuesses.add(pG);
        playerGuess.setText("");
        count += 1;
    }
    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
