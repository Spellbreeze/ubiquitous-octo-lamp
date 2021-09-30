package com.draobemag.mariokart.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class PlayerConfigController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Button nextOrGo;
    public int numDrivers;



    public void validateEntries(ActionEvent event) throws IOException {
        FXMLLoader configLoaded = new FXMLLoader(getClass().getResource("/config-view.fxml"));
        ConfigController config = configLoaded.getController();
        numDrivers = config.getNumOfDrivers();

        System.out.println(numDrivers);
    }
}
