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
    private int number;
    private  Player currPlayer;

    public void initialize() {
        ArrayList<Player> playerTempList = new ArrayList<Player>();
        playerTempList = GameManager.GetPlayerList();
        this.currPlayer = playerTempList.get(GameManager.getStartPoint() - 1);
        int min = 1;
        int max = 100;
        number = (int)(Math.random() * (max-min+1)+min);
        System.out.println("Number is: " + number);
    }

    @FXML
    private void check() throws IOException {
        checker(currPlayer);
    }
    private void checker(Player player) throws IOException {
        int pG = Integer.parseInt(playerGuess.getText());

        if (pG == number) {
          player.setMoney(player.getMoney() + 15);
        }
        GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.MAIN));
        //GameManager.GameManager().stage.close();
    }
}
