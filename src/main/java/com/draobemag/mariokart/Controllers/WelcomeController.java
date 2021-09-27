package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class WelcomeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException {
        GameManager.GameManager().stage
                .setScene(SceneType.LoadScene(SceneType.CONFIG));
    }
}