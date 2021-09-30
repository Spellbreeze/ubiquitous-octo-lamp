package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.fxml.FXML;

import java.io.IOException;

public class ConfigController {

    @FXML
    public void onGoButtonClick() throws IOException {
        GameManager.GameManager().stage
                .setScene(SceneType.LoadScene(SceneType.MAIN));
    }
}
