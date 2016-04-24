package sample;

import javafx.animation.AnimationTimer;
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
import javafx.stage.Stage;

import java.util.ArrayList;

public class Replay extends Stage implements Constants {
  private Options gameOptions;
  private Board gameBoard;
  private ArrayList<Move> moves;
  private ArrayList<Text> scores;
  private int counter = 0;

  Replay(java.io.File file) {
    gameOptions = new Options();
    scores = new ArrayList<>();

    File.createReadStream(file.getName());
    gameOptions = File.loadOptions();

    gameBoard = new Board(gameOptions.getRows(), gameOptions.getColumns());
    gameBoard.setDisable(true);

    moves = File.loadMoves();

    File.closeReadStream();

    System.out.println(moves.size());

    setTitle("Replay");
    setScene(createScene());
    sizeToScene();
    show();
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

    Button playButton = new Button("Play");
    GridPane.setConstraints(playButton, 0, 1);
    GridPane.setHalignment(playButton, HPos.CENTER);
    playButton.setOnMouseClicked(event -> {
      at.start();
    });

    Button exitButton = new Button("Exit");
    GridPane.setConstraints(exitButton, 2, 1);
    GridPane.setHalignment(exitButton, HPos.CENTER);
    exitButton.setOnMouseClicked(event -> {
      this.close();
    });

    root.getChildren()
        .addAll(firstPlayerScoreLayout, gameBoardLayout, playButton, secondPlayerScoreLayout,
            exitButton);
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

  protected AnimationTimer at = new AnimationTimer() {
    @Override public void handle(long now) {
      play();
    }
  };

  private void play() {
    if (counter >= moves.size()) {
      at.stop();
      return;
    }
    Side side = new Side(moves.get(counter).getMove().getStartCoordinates(),
        moves.get(counter).getMove().getEndCoordinates());

    if (moves.get(counter).getCurrentPlayer() == FIRST)
      side.setStroke(DEFAULT_COLOR_FOR_FIRST_PLAYER);
    else
      side.setStroke(DEFAULT_COLOR_FOR_SECOND_PLAYER);

    gameBoard.addSide(side);

    ArrayList<Mark> marks;
    marks = moves.get(counter).getMarks();

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

    if (moves.get(counter).getFirstPlayerScore() > 0)
      scores.get(FIRST).setText(Integer.toString(moves.get(counter).getFirstPlayerScore(), 10));
    if (moves.get(counter).getSecondPlayerScore() > 0)
      scores.get(SECOND).setText(Integer.toString(moves.get(counter).getSecondPlayerScore(), 10));

    counter++;
  }
}


