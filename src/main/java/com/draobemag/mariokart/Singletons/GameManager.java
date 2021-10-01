package com.draobemag.mariokart.Singletons;

import javafx.stage.Stage;

public class GameManager {
    private static GameManager instance = null;

    // Various game & player information
    public Stage stage;

    public int numberOfPlayers;

    private GameManager(Stage stage)
    {
        this.stage = stage;
        numberOfPlayers = 1;
    }

    public static GameManager GameManager(Stage stage)
    {
        if (instance == null)
        {
            instance = new GameManager(stage);
        }
        return instance;
    }

    //This method should be revised
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
}
