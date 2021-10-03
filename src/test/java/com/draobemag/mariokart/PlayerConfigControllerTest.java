package com.draobemag.mariokart;

import com.draobemag.mariokart.Controllers.PlayerConfigController;
import com.draobemag.mariokart.Enums.SceneType;
import com.draobemag.mariokart.Singletons.GameManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javafx.stage.Stage;
import java.io.IOException;

import org.junit.jupiter.api.extension.ExtendWith;
/*import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;*/

class PlayerConfigControllerTest
{
    @Test
    void validateName()
    {
        PlayerConfigController foo = new PlayerConfigController();
        assertEquals(false, foo.validateName(""));
        assertEquals(false, foo.validateName(null));
        assertEquals(false, foo.validateName("\t   "));
    }

    //Does not work yet due to using .class.getResource() for loading fxml files
    @Test
    void testFXMLLocations() throws IOException {
        for (SceneType sceneType : SceneType.values())
        {
            assertNotNull(SceneType.LoadScene(sceneType));
        }
    }
}