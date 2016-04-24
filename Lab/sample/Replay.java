package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Replay extends Stage implements Constants {
  private Board gameBoard;
  private Options gameOptions;
  private ArrayList<Move> moves;
  private ArrayList<Text> scores;

  Replay() {
    gameOptions = new Options();
    moves = new ArrayList<>();
    scores = new ArrayList<>();

    gameOptions = File.loadOptions("replay");

    gameBoard = new Board(gameOptions.getRows(), gameOptions.getColumns());
    gameBoard.setDisable(true);


    moves = File.loadMoves();

    System.out.println(moves.size());

    setTitle("Replay");
    setScene(createScene());
    sizeToScene();
    show();
    play();
  }

  private Scene createScene() {
    GridPane root = new GridPane();
    VBox firstPlayerScoreLayout;
    VBox secondPlayerScoreLayout;
    VBox gameBoardLayout = new VBox();

    firstPlayerScoreLayout = createPlayerLayout(gameOptions.getFirstPlayer(), FIRST);
    firstPlayerScoreLayout
        .setPadding(new Insets(0, DEFAULT_OFFSET, DEFAULT_OFFSET, DEFAULT_OFFSET));
    secondPlayerScoreLayout = createPlayerLayout(gameOptions.getSecondPlayer(), SECOND);
    secondPlayerScoreLayout
        .setPadding(new Insets(0, DEFAULT_OFFSET, DEFAULT_OFFSET, DEFAULT_OFFSET));

    secondPlayerScoreLayout.setAlignment(Pos.TOP_CENTER);
    gameBoardLayout.getChildren().addAll(gameBoard);
    gameBoardLayout.setAlignment(Pos.CENTER);

    GridPane.setConstraints(firstPlayerScoreLayout, 0, 0);
    GridPane.setConstraints(gameBoardLayout, 1, 0);
    GridPane.setConstraints(secondPlayerScoreLayout, 2, 0);


    root.getChildren().addAll(firstPlayerScoreLayout, gameBoardLayout, secondPlayerScoreLayout);
    root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
    root.autosize();

    return new Scene(root);
  }

  private VBox createPlayerLayout(Player player, int number) {
    VBox playerLayout = new VBox();

    Text playerName = new Text(player.getName());
    playerName.setFont(Font.font(20));
    if (number == FIRST)
      playerName.setFill(DEFAULT_COLOR_FOR_FIRST_PLAYER);
    else
      playerName.setFill(DEFAULT_COLOR_FOR_SECOND_PLAYER);

    Text playerScore = new Text(Integer.toString(player.getScore(), 10));
    playerScore.setFill(Color.WHITE);
    playerScore.setFont(Font.font(20));

    scores.add(playerScore);

    playerLayout.getChildren().addAll(playerName, playerScore);
    playerLayout.setAlignment(Pos.TOP_CENTER);
    return playerLayout;
  }

  private void play() {
    for (Move move : moves) {
      Side side =
          new Side(move.getMove().getStartCoordinates(), move.getMove().getEndCoordinates());
      if (move.getCurrentPlayer() == FIRST)
        side.setStroke(DEFAULT_COLOR_FOR_FIRST_PLAYER);
      else
        side.setStroke(DEFAULT_COLOR_FOR_SECOND_PLAYER);
      gameBoard.addSide(side);

      ArrayList<Mark> marks;
      marks = move.getMarks();

      if (marks.size() != 0) {
        for (Mark mark : marks) {
          Text text = new Text(mark.getText());
          text.setX(mark.getX());
          text.setY(mark.getY());
          text.setFont(Font.font(mark.getFontSize()));
          if (mark.getColorOfPlayer() == FIRST)
            text.setFill(DEFAULT_COLOR_FOR_FIRST_PLAYER);
          else
            text.setFill(DEFAULT_COLOR_FOR_SECOND_PLAYER);
          gameBoard.addMark(text);
        }
      }

      if (move.getFirstPlayerScore() > 0)
        scores.get(FIRST).setText(Integer.toString(move.getFirstPlayerScore(), 10));
      System.out.println("First player score: " + move.getFirstPlayerScore());
      if (move.getSecondPlayerScore() > 0)
        scores.get(SECOND).setText(Integer.toString(move.getSecondPlayerScore(), 10));
      System.out.println("Second player score: " + move.getSecondPlayerScore());
    }
  }
}
