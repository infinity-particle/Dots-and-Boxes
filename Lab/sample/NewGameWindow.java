package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NewGameWindow implements Constants {
  static Options gameOptions = null;

  static public Options display() {
    Stage window = new Stage();
    Pane root = new Pane();
    GridPane grid = new GridPane();
    Insets inset = new Insets(10);

    Text firstPlayerLabel = new Text("Player 1: ");
    firstPlayerLabel.setFill(Color.WHITE);
    GridPane.setMargin(firstPlayerLabel, inset);
    GridPane.setConstraints(firstPlayerLabel, 0, 0);

    ComboBox<PlayerType> firstPlayerType = new ComboBox<>();
    firstPlayerType.getItems().addAll(PlayerType.HUMAN, PlayerType.BOT);
    firstPlayerType.setValue(PlayerType.HUMAN);
    GridPane.setMargin(firstPlayerType, inset);
    GridPane.setConstraints(firstPlayerType, 1, 0);

    TextField firstPlayerName = new TextField();
    firstPlayerName.setText("Player 1");
    GridPane.setMargin(firstPlayerName, inset);
    GridPane.setConstraints(firstPlayerName, 2, 0);

    Text secondPlayerLabel = new Text("Player 2: ");
    secondPlayerLabel.setFill(Color.WHITE);
    GridPane.setMargin(secondPlayerLabel, inset);
    GridPane.setConstraints(secondPlayerLabel, 0, 1);

    ComboBox<PlayerType> secondPlayerType = new ComboBox<>();
    secondPlayerType.getItems().addAll(PlayerType.HUMAN, PlayerType.BOT);
    secondPlayerType.setValue(PlayerType.HUMAN);
    GridPane.setMargin(secondPlayerType, inset);
    GridPane.setConstraints(secondPlayerType, 1, 1);

    TextField secondPlayerName = new TextField();
    secondPlayerName.setText("Player 2");
    GridPane.setMargin(secondPlayerName, inset);
    GridPane.setConstraints(secondPlayerName, 2, 1);

    Text rowsNumberLabel = new Text("Rows: ");
    rowsNumberLabel.setFill(Color.WHITE);
    GridPane.setMargin(rowsNumberLabel, inset);
    GridPane.setConstraints(rowsNumberLabel, 0, 2);

    ComboBox<String> rowsNumber = new ComboBox<>();
    rowsNumber.getItems().addAll("3", "4", "5", "6", "7", "8");
    rowsNumber.setValue("3");
    GridPane.setMargin(rowsNumber, inset);
    GridPane.setConstraints(rowsNumber, 1, 2);

    Text columnsNumberLabel = new Text("Columns: ");
    columnsNumberLabel.setFill(Color.WHITE);
    GridPane.setMargin(columnsNumberLabel, inset);
    GridPane.setConstraints(columnsNumberLabel, 0, 3);

    ComboBox<String> columnsNumber = new ComboBox<>();
    columnsNumber.getItems().addAll("3", "4", "5", "6", "7", "8");
    columnsNumber.setValue("3");
    GridPane.setMargin(columnsNumber, inset);
    GridPane.setConstraints(columnsNumber, 1, 3);

    Button startButton = new Button("Start");
    Button cancelButton = new Button("Cancel");

    startButton.setOnMouseReleased(event -> {
      gameOptions = new Options();
      gameOptions.setColumns(Integer.valueOf(columnsNumber.getValue()));
      gameOptions.setRows(Integer.valueOf(rowsNumber.getValue()));
      gameOptions.setFirstPlayer(new Player(firstPlayerName.getText(), firstPlayerType.getValue(),
          DEFAULT_COLOR_FOR_FIRST_PLAYER));
      gameOptions.setSecondPlayer(
          new Player(secondPlayerName.getText(), secondPlayerType.getValue(),
              DEFAULT_COLOR_FOR_SECOND_PLAYER));
      window.close();
    });

    cancelButton.setOnMouseReleased(event -> {
      window.close();
    });
    GridPane.setMargin(startButton, inset);
    GridPane.setMargin(cancelButton, inset);
    GridPane.setHalignment(startButton, HPos.CENTER);
    GridPane.setHalignment(cancelButton, HPos.CENTER);
    GridPane.setConstraints(startButton, 0, 4);
    GridPane.setConstraints(cancelButton, 3, 4);

    grid.getChildren().addAll(firstPlayerLabel, firstPlayerType, firstPlayerName);
    grid.getChildren().addAll(secondPlayerLabel, secondPlayerType, secondPlayerName);
    grid.getChildren().addAll(rowsNumberLabel, rowsNumber);
    grid.getChildren().addAll(columnsNumberLabel, columnsNumber);
    grid.getChildren().addAll(startButton, cancelButton);

    root.getChildren().add(grid);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

    Scene newGameScene = new Scene(root);

    window.setScene(newGameScene);
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("New Game");
    window.setResizable(false);
    window.showAndWait();
    return gameOptions;
  }

  /**
   * This method removes a reference to an object gameOptions, because if you press New game and
   * in the settings of the new game press Cancel, start the game with the previous settings
   * instead of returning to the menu.
   */
  public static void resetGameOptions() {
    gameOptions = null;
  }
}
