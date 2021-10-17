package com.draobemag.mariokart;

import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.HelloApplication;


import com.draobemag.mariokart.Singletons.GameManager;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import org.junit.Test;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.text.Text;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.*;


public class TileTests extends ApplicationTest {
    private  Stage myStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.start(primaryStage);
        myStage = primaryStage;
    }

    @BeforeAll
    public void getToGameScreen()
    {
        clickOn("#initStart");
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
    public void testMoneyPresent()
    {
        getToGameScreen();
        verifyThat("#test1Label", hasText("test1: 50 kmph"));
    }

    @Test
    public void testMoneyChanges()
    {
        getToGameScreen();
        clickOn("#moveButton");
        clickOn("OK");
        clickOn("#moveButton");
        clickOn("OK");

        ArrayList<Player> players = GameManager.getPlayerList();
        Assertions.assertNotEquals(50, players.get(0).getMoney());
    }

    private int getDiceValueFromAlertText(String alertText) {
        // Alert text has the form: "You rolled a 5!"
        // Index 13 contains the dice roll number
        return alertText.charAt(13) - 48;
    }

    @Test
    public void testDiceRollValues() {
        getToGameScreen();
        // Arbitrary number of iterations
        for (int i = 0; i < 5; i++) {
            clickOn("#moveButton");
            Node alertNode = lookup(".dialog-pane").query();
            assertNotNull(alertNode);

            String alertText = ((DialogPane) alertNode).getContentText();
            int diceValue = getDiceValueFromAlertText(alertText);
            assert(diceValue >= 1 && diceValue <= 6);
            clickOn("OK");
        }
    }

    @Test
    public void testDiceRollMovement() {
        getToGameScreen();
        // Arbitrary number of iterations, but must be sufficiently small (see comment on assertion)
        for (int i = 0; i < 4; i++) {
            int nplayers = GameManager.GetNumPlayers();
            Player curr_player = GameManager.getPlayerList().get(i % nplayers);
            int start_pos = curr_player.getPosition();
            // TODO: refactor this duplicated code into separate function
            clickOn("#moveButton");
            Node alertNode = lookup(".dialog-pane").query();
            assertNotNull(alertNode);
            String alertText = ((DialogPane) alertNode).getContentText();
            int diceValue = getDiceValueFromAlertText(alertText);
            clickOn("OK");
            int end_pos = curr_player.getPosition();
            // Check that the player's position was updated correctly based on the dice value
            // This doesn't correctly handle wraparound when the player makes it back to position 0
            assertEquals(end_pos - start_pos, diceValue);
        }

    }
}
