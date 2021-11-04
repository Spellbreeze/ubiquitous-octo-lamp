package com.draobemag.mariokart;

import com.draobemag.mariokart.Controllers.GameBoardView;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.HelloApplication;


import com.draobemag.mariokart.Singletons.GameManager;
import javafx.application.Platform;
import org.junit.Test;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.text.Text;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class GameEndTests extends ApplicationTest {

    private Stage myStage;
    private GameManager gameManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.start(primaryStage);
        gameManager = GameManager.GameManager();
        myStage = primaryStage;
    }

    private void getToGameScreen()
    {
        clickOn("#initStart");
        GameManager.GameManager().refresh();
        clickOn("#toggle2");
        clickOn("#speedToggle1");
        clickOn("#playerConfig");

        clickOn("#playerName").write("test1");
        clickOn("#sprite1");
        clickOn("#nextOrGo");

        clickOn("#playerName").write("test2");
        clickOn("#sprite2");
        clickOn("#nextOrGo");

        clickOn("#start");
    }

    @Test
    public void WinScreenOpensTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0),63);
                            finishedMovt[0] = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                while (!finishedMovt[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();

        thread.join();
        verifyThat("#winnerName", NodeMatchers.isVisible());
    }

    @Test
    public void WinScreenPositionsTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0),63);
                            finishedMovt[0] = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                while (!finishedMovt[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }

            }
        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();


        thread.join();

        verifyThat("#p1Location", hasText("63"));
        verifyThat("#p2Location", hasText("0"));
    }

    @Test
    public void WinScreenOrderTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0),63);
                            finishedMovt[0] = true;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };

                while (!finishedMovt[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
        // don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();

        thread.join();

        verifyThat("#player1Location", hasText("1. test1 location:"));
        verifyThat("#player2Location", hasText("2. test2 location:"));
    }
}
