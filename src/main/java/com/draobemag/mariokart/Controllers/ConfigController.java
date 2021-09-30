package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.security.PrivateKey;

public class ConfigController extends VBox {
    @FXML
    private RadioButton toggle1;
    @FXML
    private RadioButton toggle2;
    @FXML
    private RadioButton toggle3;
    @FXML
    private RadioButton toggle4;
    @FXML
    private ToggleGroup group;

    private int numOfDrivers;

    @FXML
    protected void onPlayerConfigClick(ActionEvent event) throws IOException {
        RadioButton toggleButton= (RadioButton) group.getSelectedToggle();
        String value = toggleButton.getText();
        numOfDrivers = Integer.parseInt(value);
        setNumOfDrivers(numOfDrivers);

        GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.PLAYER));
    }
    public int getNumOfDrivers() {
        return numOfDrivers;
    }
    public void setNumOfDrivers(int nums) {
        numOfDrivers = nums;
    }
}
