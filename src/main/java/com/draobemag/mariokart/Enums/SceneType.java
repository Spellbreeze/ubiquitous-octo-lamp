package com.draobemag.mariokart.Enums;

import com.draobemag.mariokart.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public enum SceneType {
    WELCOME,
    CONFIG,
    PLAYER,
    MAIN;

    public static Scene LoadScene(SceneType sceneType) throws IOException {
        String fxmlPath;
        switch(sceneType)
        {
            case WELCOME:
                fxmlPath = "welcome-view.fxml";
                break;
            case CONFIG:
                fxmlPath = "config-view.fxml";
                break;
            case PLAYER:
                fxmlPath = "player-view.fxml";
                break;
            default:
                //should not be reached
                fxmlPath = "welcome-view.fxml";
                break;
        }
        System.out.println(HelloApplication.class.getResource(fxmlPath));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load(), 420, 240);
        return scene;
    }
}
