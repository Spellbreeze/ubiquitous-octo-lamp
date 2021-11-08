
import com.draobemag.mariokart.HelloApplication;
import javafx.scene.Node;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.assertNotNull;
import static org.testfx.api.FxAssert.verifyThat;

public class GameBoardTest  extends ApplicationTest {
    private Stage myStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        HelloApplication helloApplication = new HelloApplication();
        helloApplication.start(primaryStage);
        myStage = primaryStage;
    }

    @Test
    public void testWelcomeScreenLaunched() {
        // Ensure the 'Start Your Engines!' button is present
        verifyThat("Start Your Engines!", NodeMatchers.isVisible());
    }

    @Test
    public void testNextScreen() {
        clickOn("#initStart");
        // Check that we progressed to the next screen by ensuring the 'Go!' button is present
        verifyThat("Go!", NodeMatchers.isVisible());
    }

    @Test
    public void testNumDriversAlert() {
        // Advance to next screen
        clickOn("#initStart");
        // Alert should occur since the number of drivers was not selected
        clickOn("#playerConfig");
        // Get the Node of the Alert
        Node alertNode = lookup(".dialog-pane").query();
        assertNotNull(alertNode);
    }
}
