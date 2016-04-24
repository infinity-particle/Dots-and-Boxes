package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.io.File;

public class MainMenu extends Pane implements Constants {
  private static Scene mainMenuScene;

  public MainMenu() {
    setPrefSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);

    MenuItem newGameButton = new MenuItem("NEW GAME");
    MenuItem replayButton = new MenuItem("REPLAY");
    MenuItem exitButton = new MenuItem("EXIT");

    newGameButton.setOnMouseReleased(event -> {
      Options gameOptions = NewGameWindow.display();
      Game game;
      if (gameOptions != null)
        game = new Game(gameOptions);
    });
    replayButton.setOnMouseClicked(event -> {
      FileChooser replayFileChooser = new FileChooser();
      java.io.File file = replayFileChooser.showOpenDialog(Main.getPrimaryStage());
      if (file != null)
        new Replay();
    });
    exitButton.setOnMouseClicked(event -> System.exit(0));

    MenuBox menu = new MenuBox("DOTS & BOXES", newGameButton, replayButton, exitButton);

    getChildren().addAll(menu);

    Background background = new Background(new BackgroundFill(Color.BLACK, null, null));
    setBackground(background);

    mainMenuScene = new Scene(this, DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
  }

  public static Scene getMainMenuScene() {
    return mainMenuScene;
  }
}
