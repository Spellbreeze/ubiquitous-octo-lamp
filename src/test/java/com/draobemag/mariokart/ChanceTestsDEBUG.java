package com.draobemag.mariokart;

import com.draobemag.mariokart.Classes.GameTileManager;
import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.Controllers.GameBoardView;
import com.draobemag.mariokart.Enums.GameTileType;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class ChanceTestsDEBUG extends ApplicationTest {

    private Stage myStage;
    private GameManager gameManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.start(primaryStage);
        gameManager = GameManager.GameManager();
        myStage = primaryStage;
        //GameTileManager.CHANCE_TILE_LOC = 2;
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
    public void ChanceTile1Alert() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile1 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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

        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("You do a flip and speed up.".equals(alertText));
        clickOn("OK");

        }

    @Test
    public void ChanceTile2Alert() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile2 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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

        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("Your Kart slips on a banana peel and slows down.".equals(alertText));
        clickOn("OK");

    }
    @Test
    public void ChanceTile3Alert() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile3 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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

        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("You found a shortcut!".equals(alertText));
        clickOn("OK");

    }
    @Test
    public void ChanceTile4Alert() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile4 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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

        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("Your Kart suddenly changes form.".equals(alertText));
        clickOn("OK");
    }
    @Test
    public void ChanceTile5Alert() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile5 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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

        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("You swapped places with another Kart.".equals(alertText));
        clickOn("OK");

    }

    @Test
    public void ChanceTile1Effect() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile1 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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
        int prev_money = currPlayer[0].getMoney();
        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("You do a flip and speed up.".equals(alertText));
        clickOn("OK");
        int curr_money = currPlayer[0].getMoney();
        assert(prev_money + 25 == curr_money);
    }

    @Test
    public void ChanceTile2Effect() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile2 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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
        int prev_money = currPlayer[0].getMoney();
        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("You do a flip and speed up.".equals(alertText));
        clickOn("OK");
        int curr_money = currPlayer[0].getMoney();
        assert(prev_money - 25 == curr_money);
    }

    @Test
    public void ChanceTile5Effect() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile5 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testChanceButtonVisible();
                        finishedMovt[0] = true;
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

        int prev_position = currPlayer[0].getPosition();
        clickOn("#moveTwelve");
        //clickOn("OK");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("You found a shortcut!".equals(alertText));
        clickOn("OK");
        int curr_position = currPlayer[0].getPosition();
        assert(prev_position != curr_position);

    }
}
