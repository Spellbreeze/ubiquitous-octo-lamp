package com.draobemag.mariokart;

import com.draobemag.mariokart.Singletons.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Hello!");
        stage.setScene(SceneManager.LoadScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}