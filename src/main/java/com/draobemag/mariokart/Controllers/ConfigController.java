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
    protected void onPlayerConfigClick(ActionEvent event) throws IOException
    {
        RadioButton toggleButton= (RadioButton) group.getSelectedToggle();
        if (toggleButton == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setContentText("Please select a number of drivers before proceeding!");
            alert.showAndWait();
        } else {
            String value = toggleButton.getText();
            numOfDrivers = Integer.parseInt(value);
            GameManager.SetNumPlayers(numOfDrivers);
            GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.PLAYER));
        }
    }
}
