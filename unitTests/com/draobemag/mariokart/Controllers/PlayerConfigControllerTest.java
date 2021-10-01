package com.draobemag.mariokart.Controllers;

import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;

import org.loadui.testfx.GuiTest;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import static org.loadui.testfx.GuiTest.*;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

class PlayerConfigControllerTest extends ApplicationTest{
    @Override
    public void start(Stage stage) throws IOException, Exception {
        GameManager gameManager = GameManager.GameManager(stage);
        gameManager.stage.setScene(SceneType.LoadScene(SceneType.PLAYER));
        stage.show();
    }

    @BeforeEach
    void setUp() throws IOException {
    }

    @Test
    void validateEntries() throws IOException {
        clickOn("#firstName");
        write("    ");
        final Button test = find("#nextOrGo");
        clickOn(test);

    }
}