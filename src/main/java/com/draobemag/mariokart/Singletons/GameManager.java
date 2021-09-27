package com.draobemag.mariokart.Singletons;

public class GameManager {
    private static GameManager instance = null;

    // Various game & player information
    public String s;

    private GameManager()
    {
        s = "Hello I am a string part of Singleton class";
    }

    public static GameManager GameManager()
    {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }
}
