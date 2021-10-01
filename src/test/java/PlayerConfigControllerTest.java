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

    //Cursed TestFX tests
    /*private GameManager gameManager;
    @Start
    public void start(Stage stage) throws IOException, Exception
    {
        gameManager = GameManager.GameManager(stage);
        gameManager.stage.setScene(SceneType.LoadScene(SceneType.WELCOME));
        gameManager.stage.show();
        gameManager.stage.toFront();
    }

    @BeforeEach
    void setUp() throws IOException {
    }*/
    /*@Test
    void validateTestFX() throws IOException {
        FxAssert.verifyThat("#initStart", LabeledMatchers.hasText("Start Your Engines!"));
    }*/

    /*@Test
    void validateEntries() throws IOException {
        clickOn("#initStart");
        clickOn("#toggle1");
        clickOn("#playerConfig");
        clickOn("#firstName");
        write("    ");
        //assertEquals();
        //clickOn(test);

    }*/
}