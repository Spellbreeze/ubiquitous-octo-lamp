package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.lang.Math;
import java.io.IOException;
import java.util.*;

public class PlayerConfigController {
    @FXML
    private TextField firstName;
    @FXML
    private Button nextOrGo;
    @FXML
    private Button start;
    @FXML
    private Label fName;

    public int numDrivers;
    public boolean valid = false;
    public String name;
    List<String> names = new ArrayList<String>();
    List<Integer> playerOrder;
    public int startPlayer;

    public void validateEntries(ActionEvent event) throws IOException {
        numDrivers = GameManager.GetNumPlayers();
        if (names.size() < numDrivers) {
            do {
                valid = firstName.getText() != null && !firstName.getText().trim().isEmpty() && !firstName.getText().isEmpty();
                //System.out.println(valid);
                if (!valid) {
                    //System.out.println("Name cannot be null nor have any white spaces in it. Please try again.");
                    Alert invalid = new Alert(Alert.AlertType.ERROR);
                    invalid.setHeaderText("ERROR");
                    invalid.setContentText("Name cannot be null nor be only white spaces. Please try again");
                    invalid.showAndWait();
                    break;
                } else if (names.contains(firstName.getText())) {
                    //System.out.println("Sorry! Username already exists. Please try again!");
                    Alert nameExists = new Alert(Alert.AlertType.ERROR);
                    nameExists.setHeaderText("ERROR");
                    nameExists.setContentText("Sorry! Username already exists. Please try again!");
                    nameExists.showAndWait();
                    break;
                } else {
                    names.add(firstName.getText());
                    System.out.println("name added successfully");
                    firstName.clear();
                    break;
                }
            }while (!valid);
        } else {
            fName.setVisible(false);
            firstName.setVisible(false);
            firstName.clear();
            nextOrGo.setVisible(false);
            start.setVisible(true);
            nextOrGo.setVisible(false);
        }
    }
    public void gameInit(ActionEvent event) throws IOException {
        //Placeholder for now.
        // Here we get the random player to start the game.
        Random rand = new Random();
        Set<Integer> playOrder = new LinkedHashSet<Integer>();
        while (playOrder.size() < numDrivers) {
            playOrder.add(rand.nextInt(numDrivers) + 1);
        }
        playerOrder = new ArrayList<Integer>(playOrder);
        startPlayer = playerOrder.get(0);
        GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.MAIN));
        //System.out.println(playerOrder);
        //System.out.println("start player is: " + startPlayer);
    }
    public TextField getTextField() {
        return firstName;
    }
    public Button getNextOrGo() {
        return nextOrGo;
    }
}
