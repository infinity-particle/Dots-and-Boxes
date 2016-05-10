package sample;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class MainMenu extends Pane implements Constants {
  private static Scene mainMenuScene;

  public MainMenu() {
    setPrefSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);

    MenuItem newGameButton = new MenuItem("NEW GAME");
    MenuItem replayButton = new MenuItem("REPLAY");
    MenuItem exitButton = new MenuItem("EXIT");

    newGameButton.setOnMouseReleased(event -> {
      Options gameOptions = NewGameWindow.display();
      if (gameOptions != null) {
        Game newGame = new Game(gameOptions);
        newGame.start();
      }
    });
    replayButton.setOnMouseClicked(event -> {
      FileChooser replayFileChooser = new FileChooser();
      FileChooser.ExtensionFilter filter =
          new FileChooser.ExtensionFilter("Replay files (*.replay)", "*.replay");
      replayFileChooser.getExtensionFilters().add(filter);
      java.io.File file = replayFileChooser.showOpenDialog(Main.getPrimaryStage());
      if (file != null) {
        new Replay(file);
      }
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
