package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Singletons.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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

    public int numDrivers;
    public boolean valid;
    public String name;
    List<String> names = new ArrayList<String>();
    List<Integer> playerOrder;
    public int startPlayer;

    public void validateEntries(ActionEvent event) throws IOException {
        numDrivers = GameManager.GetNumPlayers();
        if (names.size() < numDrivers-1) {
            do {
                if (firstName.getText() == null || firstName.getText().trim().isEmpty())
                    valid = false;
                else
                    valid = true;
                //System.out.println(valid);
                if (!valid) {
                    System.out.println("Name cannot be null nor have any white spaces in it. Please try again.");
                    break;
                } else if (names.contains(firstName.getText())) {
                    System.out.println("Sorry! Username already exists. Please try again!");
                } else {
                    names.add(firstName.getText());
                    firstName.clear();
                }
            } while (!valid);
        } else {
            nextOrGo.setVisible(false);
            start.setVisible(true);
            nextOrGo.setVisible(false);
        }
    }
    public void gameInit(ActionEvent event) {
        //Placeholder for now.
        // Here we get the random player to start the game.
        Random rand = new Random();
        Set<Integer> playOrder = new LinkedHashSet<Integer>();
        while (playOrder.size() < numDrivers) {
            playOrder.add(rand.nextInt(numDrivers) + 1);
        }
        playerOrder = new ArrayList<Integer>(playOrder);
        startPlayer = playerOrder.get(0);
        //System.out.println(playerOrder);
        //System.out.println("start player is: " + startPlayer);
    }
}
