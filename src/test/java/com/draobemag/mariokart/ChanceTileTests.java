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

public class ChanceTileTests extends ApplicationTest {

    private Stage myStage;
    private GameManager gameManager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.start(primaryStage);
        gameManager = GameManager.GameManager();
        myStage = primaryStage;
        GameTileManager.CHANCE_TILE_LOC = 1;
        //GameTileManager.MINIGAME_TILE_LOC = 1;
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

    private void getToGameScreen4Players()
    {
        clickOn("#initStart");
        GameManager.GameManager().refresh();
        clickOn("#toggle4");
        clickOn("#speedToggle1");
        clickOn("#playerConfig");

        clickOn("#playerName").write("test1");
        clickOn("#sprite1");
        clickOn("#nextOrGo");

        clickOn("#playerName").write("test2");
        clickOn("#sprite2");
        clickOn("#nextOrGo");

        clickOn("#playerName").write("test3");
        clickOn("#sprite3");
        clickOn("#nextOrGo");

        clickOn("#playerName").write("test4");
        clickOn("#sprite4");
        clickOn("#nextOrGo");

        clickOn("#start");
    }

    @Test
    public void ChanceTile1Alert() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile1 = true;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("above core logic");
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0),1);
                            //SceneType.getGameBoardView().moveSpriteNumTilesChances(gameManager.playerList.get(0), 1, GameTileType.CHANCE);
                            System.out.println("below core logic");
                            finishedMovt[0] = true;
                        } catch (IOException e) {
                            System.out.println("except");
                            e.printStackTrace();
                        }
                    }
                };

                while (!finishedMovt[0]) {
                    try {
                        System.out.println("in the lock loop");
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
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
        String alertText = ((DialogPane) alertNode).getContentText();
        //assertEquals(alertText, ((DialogPane) alertNode).getContentText());
        assert("You do a flip and speed up.".equals(alertText));
        clickOn("OK");
    }

    @Test
    public void MiniGame2Opens() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        GameManager.GameManager().overrideChanceTile2 = true;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0),1);
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
        verifyThat("#GameName", hasText("ProgressRacer"));
    }

    @Test
    public void MiniGame1InvalidInput() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        GameManager.GameManager().overrideGame1 = true;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0),1);
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

        final boolean[] finishedMovt2 = {false};
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("NumPicker"));
                        Node alertNode = lookup("#playerGuess").query();
                        if (!((TextField) alertNode).getText().contentEquals("invalid")) {
                            clickOn("#playerGuess").write("invalid");
                            clickOn("#go");
                        }
                        finishedMovt2[0] = true;
                    }
                };

                while (!finishedMovt2[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread2.setDaemon(true);
        thread2.start();
        thread2.join();

        verifyThat("#invalidLabel", hasText("Please pick a valid number between 1 and 100. No decimals!"));
    }

    @Test
    public void MiniGame1Play4() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen4Players();
        GameManager.GameManager().overrideGame1 = true;
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0), 1);
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

        boolean next = false;
        while (!next){
            Node alertNode2 = lookup("#playerGuess").query();
            if (alertNode2.isVisible() == false) {
                next = true;
            }
            final boolean[] finishedMovt2 = {false};
            Thread thread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    Runnable updater = new Runnable() {
                        @Override
                        public void run() {
                            verifyThat("#GameName", hasText("NumPicker"));
                            Node alertNode5 = lookup("#playerGuess").query();
                            if ((!((TextField) alertNode5).getText().contains("3")) && alertNode5.isVisible()) {
                                clickOn("#playerGuess").write("30");
                            }
                            if (((TextField) alertNode5).getText().contentEquals("30")) {
                                clickOn("#go");
                            }
                            finishedMovt2[0] = true;
                        }
                    };

                    while (!finishedMovt2[0]) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                        // UI update is run on the Application thread
                        Platform.runLater(updater);
                    }
                }
            });
            //         don't let thread prevent JVM shutdown
            thread2.setDaemon(true);
            thread2.start();
            thread2.join();
        }
        verifyThat("#go", hasText("Finished"));
    }

    @Test
    public void MiniGame2UpdateCheck() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        GameManager.GameManager().overrideGame2 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0),1);
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
//         don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        thread.join();

        final boolean[] finishedMovt2 = {false};
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#horse1").query();
                        Node alertNode2 = lookup("#horse2").query();
                        if (alertNode.isVisible()) {
                            clickOn("#horse1");
                        }
                        if (alertNode2.isVisible()) {
                            clickOn("#horse2");
                        }
                        finishedMovt2[0] = true;
                    }
                };

                while (!finishedMovt2[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread2.setDaemon(true);
        thread2.start();
        thread2.join();

        verifyThat("#update", Node::isVisible);

        final boolean[] finishedMovt3 = {false};
        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#update").query();
                        if (alertNode.isVisible()) {
                            clickOn("#update");
                        }
                        finishedMovt3[0] = true;
                    }
                };

                while (!finishedMovt3[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread3.setDaemon(true);
        thread3.start();
        thread3.join();

        verifyThat("#update", Node::isVisible);
    }

    @Test
    public void MiniGame2WinnerCheck() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        GameManager.GameManager().overrideGame2 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0), 1);
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
//         don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        thread.join();

        final boolean[] finishedMovt2 = {false};
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#horse1").query();
                        Node alertNode2 = lookup("#horse2").query();
                        if (alertNode.isVisible()) {
                            clickOn("#horse1");
                        }
                        if (alertNode2.isVisible()) {
                            clickOn("#horse2");
                        }
                        finishedMovt2[0] = true;
                    }
                };

                while (!finishedMovt2[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread2.setDaemon(true);
        thread2.start();
        thread2.join();

        verifyThat("#update", Node::isVisible);

        Node alertNode = lookup("#update").query();
        System.out.println(((Button) alertNode).getText());
        while (((Button) alertNode).getText().contentEquals("Next Turn")) {
            final boolean[] finishedMovt3 = {false};
            Thread thread3 = new Thread(new Runnable() {

                @Override
                public void run() {
                    Runnable updater = new Runnable() {
                        @Override
                        public void run() {
                            verifyThat("#GameName", hasText("ProgressRacer"));
                            verifyThat("#horse1", Node::isPickOnBounds);
                            Node alertNode = lookup("#update").query();
                            if (alertNode.isVisible() && ((Button) alertNode).getText().contentEquals("Next Turn")) {
                                clickOn("#update");
                            }
                            finishedMovt3[0] = true;
                        }
                    };

                    while (!finishedMovt3[0]) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                        }
                        // UI update is run on the Application thread
                        Platform.runLater(updater);
                    }
                }
            });
            //         don't let thread prevent JVM shutdown
            thread3.setDaemon(true);
            thread3.start();
            thread3.join();
        }
        assert ((Button) alertNode).getText().contentEquals("Finished");
        Node winnerNode = lookup("#Winner").query();
        assert ((Label) winnerNode).getText().startsWith("Congratulations to the winner: ");
    }

    @Test
    public void MiniGame2ProgBarUpd() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        GameManager.GameManager().overrideGame2 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0), 1);
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
//         don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        thread.join();

        final boolean[] finishedMovt2 = {false};
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#horse1").query();
                        Node alertNode2 = lookup("#horse2").query();
                        if (alertNode.isVisible()) {
                            clickOn("#horse1");
                        }
                        if (alertNode2.isVisible()) {
                            clickOn("#horse2");
                        }
                        finishedMovt2[0] = true;
                    }
                };

                while (!finishedMovt2[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread2.setDaemon(true);
        thread2.start();
        thread2.join();

        verifyThat("#update", Node::isVisible);

        Node p1ProgrBar = lookup("#horse1P").query();
        Double prog = ((ProgressBar) p1ProgrBar).getProgress();
        final boolean[] finishedMovt3 = {false};
        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#update").query();
                        if (alertNode.isVisible()) {
                            clickOn("#update");
                        }
                        finishedMovt3[0] = true;
                    }
                };

                while (!finishedMovt3[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread3.setDaemon(true);
        thread3.start();
        thread3.join();

        assert ((ProgressBar) p1ProgrBar).getProgress() != prog;
    }

    @Test
    public void MiniGame2Play4() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen4Players();
        GameManager.GameManager().overrideGame2 = true;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            SceneType.getGameBoardView().moveSpriteNumTiles(gameManager.playerList.get(0), 1);
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
//         don't let thread prevent JVM shutdown
        thread.setDaemon(true);
        thread.start();
        thread.join();

        final boolean[] finishedMovt2 = {false};
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#horse1").query();
                        Node alertNode2 = lookup("#horse2").query();
                        if (alertNode.isVisible()) {
                            clickOn("#horse1");
                        }
                        if (alertNode2.isVisible()) {
                            clickOn("#horse2");
                        }
                        finishedMovt2[0] = true;
                    }
                };

                while (!finishedMovt2[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread2.setDaemon(true);
        thread2.start();
        thread2.join();

        final boolean[] finishedMovt4 = {false};
        Thread thread4 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#horse3").query();
                        Node alertNode2 = lookup("#horse4").query();
                        if (alertNode.isVisible()) {
                            clickOn("#horse3");
                        }
                        if (alertNode2.isVisible()) {
                            clickOn("#horse4");
                        }
                        finishedMovt4[0] = true;
                    }
                };

                while (!finishedMovt4[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread4.setDaemon(true);
        thread4.start();
        thread4.join();

        verifyThat("#update", Node::isVisible);

        final boolean[] finishedMovt3 = {false};
        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        verifyThat("#GameName", hasText("ProgressRacer"));
                        verifyThat("#horse1", Node::isPickOnBounds);
                        Node alertNode = lookup("#update").query();
                        if (alertNode.isVisible()) {
                            clickOn("#update");
                        }
                        finishedMovt3[0] = true;
                    }
                };

                while (!finishedMovt3[0]) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }
                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }
        });
//         don't let thread prevent JVM shutdown
        thread3.setDaemon(true);
        thread3.start();
        thread3.join();

        verifyThat("#horse1P", Node::isVisible);
        verifyThat("#horse2P", Node::isVisible);
        verifyThat("#horse3P", Node::isVisible);
        verifyThat("#horse4P", Node::isVisible);
    }
}
