package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.io.IOException;
import java.util.ArrayList;

public class racePickingGame {
    @FXML
    private Label title;

    @FXML
    private Label Winner;

    @FXML
    private Button update;

    @FXML
    private Button horse1;
    @FXML
    private Button horse2;
    @FXML
    private Button horse3;
    @FXML
    private Button horse4;

    @FXML
    private ProgressBar horse1P;
    @FXML
    private ProgressBar horse2P;
    @FXML
    private ProgressBar horse3P;
    @FXML
    private ProgressBar horse4P;

    private Boolean done;

    private int number;
    private Player currPlayer;
    private ArrayList<Player> playerTempList = new ArrayList<Player>();

    private ProgressBar[] holder;
    private float[] progress;

    public void initialize() {
        this.playerTempList = GameManager.GetPlayerList();
        this.currPlayer = this.playerTempList.get(0);
        this.number = 0;
        title.setText(this.currPlayer.getName() + " select a horse:");
    }

    private void getNextPlayer() {
        this.number += 1;
        this.currPlayer = this.playerTempList.get(this.number);
        title.setText(this.currPlayer.getName() + " select a horse:");
    }

    @FXML
    private void selectChar1(ActionEvent event) throws InterruptedException, IOException {
        System.out.println(event.getTarget().getClass().getName());
        horse1.setVisible(false);
        if (this.number == this.playerTempList.size() - 1) {
            this.commenceRace();
        } else {
            getNextPlayer();
        }
    }

    @FXML
    private void selectChar2(ActionEvent event) throws InterruptedException, IOException {
        System.out.println(event.getTarget().getClass().getName());
        horse2.setVisible(false);
        if (this.number == this.playerTempList.size() - 1) {
            this.commenceRace();
        } else {
            getNextPlayer();
        }
    }

    @FXML
    private void selectChar3(ActionEvent event) throws InterruptedException, IOException {
        System.out.println(event.getTarget().getClass().getName());
        horse3.setVisible(false);
        if (this.number == this.playerTempList.size() - 1) {
            this.commenceRace();
        } else {
            getNextPlayer();
        }
    }

    @FXML
    private void selectChar4(ActionEvent event) throws InterruptedException, IOException {
        System.out.println(event.getTarget().getClass().getName());
        horse4.setVisible(false);
        if (this.number == this.playerTempList.size() - 1) {
            this.commenceRace();
        } else {
            getNextPlayer();
        }
    }

    @FXML
    private void onUpdate(ActionEvent event) throws IOException {
        Random rand = new Random();
        if(!done) {
            float upd = rand.nextFloat();
            progress[this.number % this.playerTempList.size()] += upd;
            holder[this.number % this.playerTempList.size()].setProgress(progress[this.number % this.playerTempList.size()]);
            if (progress[this.number % this.playerTempList.size()] >= 1) {
                Winner.setText("Congratulations to the winner: " + this.playerTempList.get(this.number % this.playerTempList.size()).getName());
                done = true;
                update.setText("Finished");
            }
            this.number += 1;
        } else {
            this.exit(this.playerTempList.get(this.number % this.playerTempList.size()));
        }
    }

    // Not an ideal implementation but I was speedrunning
    private void commenceRace() throws InterruptedException, IOException {
        this.number = 0;
        horse1.setVisible(false);
        horse2.setVisible(false);
        horse3.setVisible(false);
        horse4.setVisible(false);
        update.setVisible(true);
        this.done = false;
        switch (this.playerTempList.size()){
            case 2:
                horse1P.setVisible(true);
                horse2P.setVisible(true);
                horse3P.setVisible(false);
                horse4P.setVisible(false);
                horse1P.setProgress(0);
                horse2P.setProgress(0);
                this.holder = new ProgressBar[]{horse1P, horse2P};
                this.progress = new float[]{0, 0};
                break;
            case 3:
                horse1P.setVisible(true);
                horse2P.setVisible(true);
                horse3P.setVisible(true);
                horse4P.setVisible(false);
                horse1P.setProgress(0);
                horse2P.setProgress(0);
                horse3P.setProgress(0);
                this.holder = new ProgressBar[]{horse1P, horse2P, horse3P};
                this.progress = new float[]{0, 0, 0};
                break;
            default:
                horse1P.setVisible(true);
                horse2P.setVisible(true);
                horse3P.setVisible(true);
                horse4P.setVisible(true);
                horse1P.setProgress(0);
                horse2P.setProgress(0);
                horse3P.setProgress(0);
                horse4P.setProgress(0);
                this.holder = new ProgressBar[]{horse1P, horse2P, horse3P, horse4P};
                this.progress = new float[]{0, 0, 0, 0};
                break;
        }
    }

    private void exit(Player player) throws IOException {
        player.setMoney(player.getMoney() + 30);
        GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.MAIN));
    }
}
