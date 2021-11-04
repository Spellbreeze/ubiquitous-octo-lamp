package com.draobemag.mariokart;

        import com.draobemag.mariokart.Classes.Player;
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

        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertNotNull;
        import static org.testfx.api.FxAssert.verifyThat;
        import static org.testfx.matcher.control.LabeledMatchers.hasText;

public class PaywallTests extends ApplicationTest {

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
    public void PaywallOpensTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        SceneType.getGameBoardView().testMakePaywallButtonVisible();
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
        clickOn("#moveFive");
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);

        clickOn("Yes");

    }

    @Test
    public void UnpaidWallPositionTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testMakePaywallButtonVisible();
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

        clickOn("#moveFive");
        // Get the Node of the Alert

        clickOn("No");
        assertEquals(0, currPlayer[0].getPosition());
    }

    @Test
    public void PaidWallPositionTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testMakePaywallButtonVisible();
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

        clickOn("#moveFive");
        // Get the Node of the Alert

        clickOn("Yes");
        assertEquals(5, currPlayer[0].getPosition());
    }

    @Test
    public void TollDecrementsSpeedTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        final Player[] currPlayer = new Player[1];
        getToGameScreen();

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        currPlayer[0] = SceneType.getGameBoardView().testMakePaywallButtonVisible();
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

        clickOn("#moveFive");
        // Get the Node of the Alert

        clickOn("Yes");
        assertEquals(35, currPlayer[0].getMoney());
    }

    @Test
    public void PaywallNotEnoughSpeedTest() throws IOException, InterruptedException {
        final boolean[] finishedMovt = {false};
        getToGameScreen();
        gameManager.playerList.get(0).setMoney(0);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {
                    @Override
                    public void run() {
                        SceneType.getGameBoardView().testMakePaywallButtonVisible().setMoney(0);
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

        clickOn("#moveFive");

        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(from(alertNode).lookup((Text t)
                -> t.getText()
                .startsWith("You don't have enough money to pay the paywall tax!")));
        verifyThat("OK", NodeMatchers.isVisible());
        clickOn("OK");
    }
}
