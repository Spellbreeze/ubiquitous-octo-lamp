package com.draobemag.mariokart.Enums;

import com.draobemag.mariokart.Controllers.GameBoardView;
import com.draobemag.mariokart.Controllers.WinnerController;
import com.draobemag.mariokart.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public enum SceneType {
    WELCOME,
    CONFIG,
    PLAYER,
    MAIN,
    WINNER,
    PICKNUMBER;

    private static GameBoardView gameBoardView;
    private static WinnerController winnerController;

    public static GameBoardView getGameBoardView() {
        return gameBoardView;
    }

    public static WinnerController getWinnerController() {
        return winnerController;
    }

    public static Scene LoadScene(SceneType sceneType) throws IOException {
        String fxmlPath;
        int width, height;
        switch(sceneType)
        {
            case WELCOME:
                fxmlPath = "welcome-view.fxml";
                width = 420; height = 240;
                break;
            case CONFIG:
                fxmlPath = "config-view.fxml";
                width = 420; height = 240;
                break;
            case PLAYER:
                fxmlPath = "player-view.fxml";
                width = 420; height = 440;
                break;
            case MAIN:
                fxmlPath = "gameboard-view.fxml";
                width = 1220; height = 1040;
                break;
            case WINNER:
                fxmlPath = "winner-view.fxml";
                width = 420; height = 240;
                break;
            case PICKNUMBER:
                fxmlPath = "numberPick-view.fxml";
                width = 420; height=240;
                break;
            default:
                //should not be reached
                fxmlPath = "welcome-view.fxml";
                width = 420; height = 240;
                break;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load(), width, height);
        if (sceneType == MAIN)
        {
            gameBoardView = (GameBoardView) fxmlLoader.getController();
        }
        else if (sceneType == WINNER)
        {
            winnerController = (WinnerController) fxmlLoader.getController();
        }
        return scene;
    }
}
