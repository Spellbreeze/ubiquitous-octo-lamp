package com.draobemag.mariokart;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;

import com.draobemag.mariokart.Singletons.GameManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GameManager gameManager = GameManager.GameManager(stage);
        gameManager.stage.setTitle("Hello!");
        gameManager.stage.setScene(SceneType.LoadScene(SceneType.MAIN));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}