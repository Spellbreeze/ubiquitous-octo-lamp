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
        int width, height;
        switch(sceneType)
        {
            case WELCOME:
                fxmlPath = "welcome-view.fxml";
                width = 420; height = 240;
                break;
            case CONFIG:
                fxmlPath = "config-view.fxml";
                width = 420; height = 240;
                break;
            case PLAYER:
                fxmlPath = "player-view.fxml";
                width = 420; height = 440;
                break;
            case MAIN:
                fxmlPath = "gameboard-view.fxml";
                width = 1220; height = 1040;
                break;
            default:
                //should not be reached
                fxmlPath = "welcome-view.fxml";
                width = 420; height = 240;
                break;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        return scene;
    }
}
