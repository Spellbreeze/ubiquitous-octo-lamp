package com.draobemag.mariokart;

import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.HelloApplication;


import com.draobemag.mariokart.Singletons.GameManager;
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

import static org.junit.Assert.assertNotNull;
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
}
