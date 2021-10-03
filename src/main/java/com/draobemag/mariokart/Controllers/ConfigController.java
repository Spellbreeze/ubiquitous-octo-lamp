package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.security.PrivateKey;
    
public class ConfigController
    {
    @FXML
    private RadioButton toggle2;
    @FXML
    private RadioButton toggle3;
    @FXML
    private RadioButton toggle4;
    @FXML
    private ToggleGroup group;

    @FXML
    private RadioButton speedToggle1;
    @FXML
    private RadioButton speedToggle2;
    @FXML
    private RadioButton speedToggle3;
    @FXML
    private ToggleGroup speedGroup;

    private int numOfDrivers;
    private int startingMoney;

    @FXML
    protected void onPlayerConfigClick(ActionEvent event) throws IOException
    {
        RadioButton toggleButton= (RadioButton) group.getSelectedToggle();
        RadioButton toggleSpeedButton= (RadioButton) speedGroup.getSelectedToggle();
        if (toggleButton == null || toggleSpeedButton == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Please complete configurations before starting!");
            alert.showAndWait();
        } else {
            String value = toggleButton.getText();
            numOfDrivers = Integer.parseInt(value);
            GameManager.SetNumPlayers(numOfDrivers);

            startingMoney = Integer.parseInt(toggleSpeedButton.
                    getText().replaceAll("[\\D]", ""));
            GameManager.SetStartingMoney(startingMoney);

            GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.PLAYER));
        }
    }
}
