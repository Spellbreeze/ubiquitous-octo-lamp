package com.draobemag.mariokart.Singletons;

import com.draobemag.mariokart.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

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

    public static Scene LoadScene() throws IOException {
        //Currently only loads welcome scene
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 420, 240);
        return scene;
    }
}
