package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.w3c.dom.Text;

import java.lang.Math;
import java.io.IOException;
import java.util.*;
import com.draobemag.mariokart.GlobalDefine;

public class PlayerConfigController {
    @FXML
    private TextField playerName;
    @FXML
    private Button nextOrGo;
    @FXML
    private Button start;
    @FXML
    private Label fName;
    @FXML
    private Label fSprite;

    @FXML
    private ToggleGroup group;

    public int numDrivers;
    public boolean valid = false;
    public String name;
    List<String> names = new ArrayList<String>();
    ArrayList<Integer> characters = new ArrayList<>();
    List<Integer> playerOrder;
    public int startPlayer;

    public void validateEntries(ActionEvent event) throws IOException {
        numDrivers = GameManager.GetNumPlayers();
        System.out.printf("size: %d, numDrivers: %d", names.size(), numDrivers);
        if (names.size() < numDrivers)
        {
            do
            {
                String name = playerName.getText();
                valid = validateName(name);
                if (!valid)
                {
                    // Invalid Name
                    Alert invalid = new Alert(Alert.AlertType.ERROR);
                    invalid.setHeaderText("ERROR");
                    invalid.setContentText("Name cannot be null nor be only white spaces. Please try again");
                    invalid.showAndWait();
                    break;
                }
                else if (names.contains(playerName.getText()))
                {
                    // Repeat Name
                    Alert nameExists = new Alert(Alert.AlertType.ERROR);
                    nameExists.setHeaderText("ERROR");
                    nameExists.setContentText("Sorry! Username already exists. Please try again!");
                    nameExists.showAndWait();
                    break;
                }
                else if (group.getToggles().size() < 1)
                {
                    // Unselected Sprite
                    Alert nameExists = new Alert(Alert.AlertType.ERROR);
                    nameExists.setHeaderText("ERROR");
                    nameExists.setContentText("Please pick a character!");
                    nameExists.showAndWait();
                    break;
                }
                else
                {
                    // Legal Name
                    names.add(playerName.getText());
                    characters.add(Integer.parseInt(((RadioButton) group.getSelectedToggle()).getId()));
                    System.out.printf("name (\"%s\") added successfully\n",playerName.getText());
                    playerName.clear();
                    break;
                }
            } while (!valid);
        }
        if (names.size() >= numDrivers)
        {
            fName.setVisible(false);
            fSprite.setVisible(false);
            playerName.setVisible(false);
            playerName.clear();
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
        GameManager.setPlayerConfig(numDrivers, characters, playerOrder.get(0));


        GameManager.GameManager().stage.setScene(SceneType.LoadScene(SceneType.MAIN));
        //System.out.println(playerOrder);
        //System.out.println("start player is: " + startPlayer);
    }

    public boolean validateName(String name)
    {
        return name != null  && !name.trim().isEmpty()
                && !name.isEmpty();
    }

    public TextField getTextField() {
        return playerName;
    }
    public Button getNextOrGo() {
        return nextOrGo;
    }
}
