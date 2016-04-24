package sample;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Vector;

/**
 * This class controls gameplay.
 */
public class Game implements Constants {
  private static Vector<Text> playersNames;
  private static ArrayList<Text> scores;
  private static Vector<Player> players;
  private static Board gameBoard;
  private Options gameOptions;
  private static int currentPlayer;

  Game(Options gameOptions) {
    this.gameOptions = gameOptions;
    File.saveOptions("replay", gameOptions);

    players = new Vector<>();
    players.add(gameOptions.getFirstPlayer());
    players.add(gameOptions.getSecondPlayer());

    gameBoard = new Board(gameOptions.getRows(), gameOptions.getColumns());
    currentPlayer = 0;
    scores = new ArrayList<>();
    playersNames = new Vector<>();

    Scene gameScene = createScene();

    Main.setScene(gameScene);

    if (players.get(FIRST).getType() == PlayerType.BOT
        && players.get(SECOND).getType() == PlayerType.BOT) {
      gameBoard.botVsBot();
    } else {
      if (players.get(FIRST).getType() == PlayerType.BOT) {
        gameBoard.botGoesFirst();
      }
    }
  }

  /**
   * Changes the current player at the next.
   */
  public static void changePlayer() {
    playersNames.get(currentPlayer).setUnderline(false);
    currentPlayer++;
    if (currentPlayer >= players.size())
      currentPlayer = FIRST;
    playersNames.get(currentPlayer).setUnderline(true);
  }

  /**
   * Create game scene with players scores and game board.
   */
  private Scene createScene() {
    GridPane root = new GridPane();
    VBox firstPlayerScoreLayout;
    VBox secondPlayerScoreLayout;
    VBox gameBoardLayout = new VBox();

    firstPlayerScoreLayout = createPlayerLayout(FIRST);
    firstPlayerScoreLayout
        .setPadding(new Insets(0, DEFAULT_OFFSET, DEFAULT_OFFSET, DEFAULT_OFFSET));
    secondPlayerScoreLayout = createPlayerLayout(SECOND);
    secondPlayerScoreLayout
        .setPadding(new Insets(0, DEFAULT_OFFSET, DEFAULT_OFFSET, DEFAULT_OFFSET));

    secondPlayerScoreLayout.setAlignment(Pos.TOP_CENTER);
    gameBoardLayout.getChildren().addAll(gameBoard);
    gameBoardLayout.setAlignment(Pos.CENTER);

    GridPane.setConstraints(firstPlayerScoreLayout, 0, 0);
    GridPane.setConstraints(gameBoardLayout, 1, 0);
    GridPane.setConstraints(secondPlayerScoreLayout, 2, 0);

    Button returnToMainMenu = new Button("Main menu");
    returnToMainMenu.setOnMouseClicked(event -> {
      Main.setScene(MainMenu.getMainMenuScene());
    });
    returnToMainMenu.setPrefSize(150, 30);
    GridPane.setConstraints(returnToMainMenu, 1, 1);
    returnToMainMenu.setAlignment(Pos.CENTER);
    GridPane.setHalignment(returnToMainMenu, HPos.CENTER);

    root.getChildren().addAll(firstPlayerScoreLayout, gameBoardLayout, secondPlayerScoreLayout);
    root.getChildren().add(returnToMainMenu);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    root.autosize();

    return new Scene(root);
  }

  public static Color getCurrentPlayerColor() {
    return players.get(currentPlayer).getColor();
  }

  /**
   * Create a table of player points.
   */
  private VBox createPlayerLayout(int player) {
    VBox playerLayout = new VBox();

    Text playerName = new Text(players.get(player).getName());
    playerName.setFont(Font.font(20));
    playerName.setFill(players.get(player).getColor());
    if (player == FIRST)
      playerName.setUnderline(true);
    playersNames.add(playerName);

    Text firstPlayerScore = new Text(Integer.toString(players.get(player).getScore(), 10));
    firstPlayerScore.setFill(Color.WHITE);
    firstPlayerScore.setFont(Font.font(20));
    scores.add(firstPlayerScore);

    playerLayout.getChildren().addAll(playerName, firstPlayerScore);
    playerLayout.setAlignment(Pos.TOP_CENTER);
    return playerLayout;
  }

  public static Player getCurrentPlayer() {
    return players.get(currentPlayer);
  }

  public static int getNumberOfTheCurrentPlayer() {
    return currentPlayer;
  }

  public static void addScoreToPlayer() {
    players.get(currentPlayer).addScore(1);
    scores.get(currentPlayer).setText(Integer.toString(players.get(currentPlayer).getScore()));
  }

  public static Vector<Player> getPlayers() {
    return players;
  }

  public Options getGameOptions() {
    return gameOptions;
  }

  public static ArrayList<Text> getScores() {
    return scores;
  }
}
