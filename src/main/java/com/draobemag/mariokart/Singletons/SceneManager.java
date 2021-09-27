package com.draobemag.mariokart.Singletons;

public class SceneManager {
    private static SceneManager instance = null;

    // Various scene loading information
    public String s;

    private SceneManager()
    {
        s = "Hello I am a string part of Singleton class";
    }

    public static SceneManager SceneManager()
    {
        if (instance == null) {
            instance = new SceneManager();
        }
        return instance;
    }
}
