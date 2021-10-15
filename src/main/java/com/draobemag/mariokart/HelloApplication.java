package com.draobemag.mariokart;

import com.draobemag.mariokart.AddOns.MusicHandler;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;

import com.draobemag.mariokart.Singletons.GameManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, MidiUnavailableException, InvalidMidiDataException, Exception {
        // This music doesn't seem modular enough
        MusicHandler musicHandler = MusicHandler.MusicHandler();
        musicHandler.setCurrentSong("mario-raceway.mid");

        GameManager gameManager = GameManager.GameManager(stage);
        gameManager.stage.setTitle("Hello!");
        gameManager.stage.setScene(SceneType.LoadScene(SceneType.WELCOME));
        gameManager.stage.show();

        musicHandler.play();
    }

    @Override
    public void stop() throws MidiUnavailableException, InvalidMidiDataException, IOException
    {
        MusicHandler.MusicHandler().close();
    }

    public static void main(String[] args) {
        launch();
    }

}