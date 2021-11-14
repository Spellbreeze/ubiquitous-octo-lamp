package com.draobemag.mariokart.Singletons;

import com.draobemag.mariokart.Classes.Player;
import com.draobemag.mariokart.Enums.SceneType;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private static GameManager instance = null;

    // Various game & player information
    public Stage stage;
    public ArrayList<Player> playerList;
    public int numberOfPlayers;
    public int startNum;
    public int startingMoney;

    public boolean overrideGame1 = false;
    public boolean overrideGame2 = false;

    private GameManager(Stage stage)
    {
        this.stage = stage;
        numberOfPlayers = 1;
    }

    public static GameManager GameManager(Stage stage)
    {
        instance = new GameManager(stage);
        instance.playerList = new ArrayList<>();
        return instance;
    }

    public static ArrayList<Player> GetPlayerList() {
        return instance.playerList;
    }

    public static void SetPlayerList(ArrayList<Player> playerList) {instance.playerList = playerList;}

    public static int getStartPoint() {
        return instance.startNum;
    }

    public static void setStartPoint(int start) {instance.startNum = start;}

    // This method should be revised
    // Temporary fix to allow GameManager to be called without
    // giving it a stage.
    public static GameManager GameManager()
    {
        if (instance != null)
        {
            return instance;
        }
        else
        {
            return null;
        }
    }

    public static int GetNumPlayers()
    {
        return instance.numberOfPlayers;
    }

    public static void SetNumPlayers(int numberOfPlayers)
    {
        instance.numberOfPlayers = numberOfPlayers;
    }

    public static int GetStartingMoney()
    {
        return instance.startingMoney;
    }

    public static void SetStartingMoney(int startingMoney)
    {
        instance.startingMoney = startingMoney;
    }

    public static void refresh() {
        if (instance.playerList != null){
            instance.playerList.clear();
        }
    }
    public static void startGame(Stage stage) throws IOException {
        GameManager gameManager = GameManager.GameManager(stage);
        gameManager.stage.setTitle("Hello!");
        gameManager.stage.setScene(SceneType.LoadScene(SceneType.WELCOME));
        gameManager.stage.show();
    }
}
