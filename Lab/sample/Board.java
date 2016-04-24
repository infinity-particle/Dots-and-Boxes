package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Vector;

/**
 * This class implements the playing field.
 */
public class Board extends GridPane implements Constants {
  private int rows;
  private int columns;
  private Corner[][] corners;
  private Box[][] boxes;
  private int remainingMoves;
  private Pane cornersPane;
  private Pane sidesPane;
  private ArrayList<Move> moves;

  /**
   * @param rows    Number of rows
   * @param columns Number of columns
   */
  Board(int rows, int columns) {
    this.rows = rows;
    this.columns = columns;
    remainingMoves = ((rows - 1) * columns) + ((columns - 1) * rows);

    corners = new Corner[rows][columns];
    boxes = new Box[rows - 1][columns - 1];

    cornersPane = new Pane();
    sidesPane = new Pane();
    getChildren().add(sidesPane);
    getChildren().add(cornersPane);

    moves = new ArrayList<>();

    int coordinateX = 0;
    int coordinateY = 0;

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        corners[i][j] = new Corner(coordinateX, coordinateY);
        GridPane.setConstraints(corners[i][j], j, i);
        cornersPane.getChildren().add(corners[i][j]);
        coordinateX += DEFAULT_OFFSET;
      }
      coordinateY += DEFAULT_OFFSET;
      coordinateX = 0;
    }

    for (int i = 0; i < rows - 1; i++)
      for (int j = 0; j < columns - 1; j++)
        boxes[i][j] =
            new Box(corners[i][j], corners[i][j + 1], corners[i + 1][j + 1], corners[i + 1][j]);

    setPadding(new Insets(DEFAULT_OFFSET));
    setOnMouseClicked(event -> checkForMove());
  }

  /**
   * This method checks whether the stroke has occurred,and make bot move if necessary
   */
  private void checkForMove() {
    Vector<Corner> clickedCorners = findClickedCorners();
    if (clickedCorners.size() == 2) {
      Move move = new Move();

      if (clickedCorners.get(FIRST).isNearbyTo(clickedCorners.get(SECOND))) {
        if (moveAlreadyBeen(clickedCorners.get(FIRST), clickedCorners.get(SECOND))) {
          clickedCorners.forEach(Corner::unclick);
          return;
        }
        Side side = new Side(clickedCorners.get(FIRST).getLocation(),
            clickedCorners.get(SECOND).getLocation());

        side.setStroke(Game.getCurrentPlayerColor());
        setBoxSides(clickedCorners.get(FIRST), clickedCorners.get(SECOND));
        decreaseRemainingMoves();
        move.setSide(side);
        moves.add(move);
        moves.get(moves.size() - 1).setCurrentPlayer(Game.getNumberOfTheCurrentPlayer());
        sidesPane.getChildren().add(side);
      }

      clickedCorners.forEach(Corner::unclick);

      //If the current player does not outlined cell , the next player goes
      if (!checkOnCircledCells()) {
        moves.get(moves.size() - 1).setScores(Game.getScores());
        Game.changePlayer();
      } else {
        moves.get(moves.size() - 1).setScores(Game.getScores());
      }

      if (isEnd()) {
        File.saveMoves(moves);
        File.closeWriteStream();
        EndGameWindow.display(Game.getPlayers());
        return;
      }

      //If the next player bot , he goes
      if (Game.getCurrentPlayer().getType() == PlayerType.BOT) {
        Bot.move(this);
        moves.get(moves.size() - 1).setCurrentPlayer(Game.getNumberOfTheCurrentPlayer());
        while (checkOnCircledCells()) {
          moves.get(moves.size() - 1).setScores(Game.getScores());
          if (isEnd()) {
            File.saveMoves(moves);
            File.closeWriteStream();
            EndGameWindow.display(Game.getPlayers());
            return;
          }

          Bot.move(this);
          moves.get(moves.size() - 1).setScores(Game.getScores());
          moves.get(moves.size() - 1).setCurrentPlayer(Game.getNumberOfTheCurrentPlayer());
        }


        Game.changePlayer();

        if (isEnd()) {
          File.saveMoves(moves);
          File.closeWriteStream();
          EndGameWindow.display(Game.getPlayers());
        }
      }
    }
  }

  /**
   * @return Array of boxes
   */
  public Box[][] getBoxes() {
    return boxes;
  }

  /**
   * When making a move in the right box sets the right direction
   *
   * @param first  Start corner of side
   * @param second End corner of side
   */
  public void setBoxSides(Corner first, Corner second) {
    for (int i = 0; i < rows - 1; i++)
      for (int j = 0; j < columns - 1; j++)
        boxes[i][j].checkForSide(first, second);
  }

  /**
   * Seeking pushed the player ( human ) corners
   */
  private Vector<Corner> findClickedCorners() {
    Vector<Corner> clickedCorners = new Vector<>();
    for (int i = 0; i < rows; i++)
      for (int j = 0; j < columns; j++)
        if (corners[i][j].isClicked())
          clickedCorners.add(corners[i][j]);
    return clickedCorners;
  }

  /**
   * Seeking is there on the field circled cells,marks them label the current player
   * and the player adds points.
   *
   * @return true if has at least one cell is circled,and false if not.
   */
  public boolean checkOnCircledCells() {
    boolean haveCircledCells = false;

    for (int i = 0; i < rows - 1; i++)
      for (int j = 0; j < columns - 1; j++)
        if (boxes[i][j].isOutlined() && !boxes[i][j].isOccupied()) {
          Game.addScoreToPlayer();
          boxes[i][j].setOwner(Game.getCurrentPlayer().getName());
          markCell(Game.getCurrentPlayer(), boxes[i][j]);
          haveCircledCells = true;
        }
    return haveCircledCells;
  }

  /**
   * Draws in a cell player tag.
   *
   * @param player The player whose label is drawn.
   * @param cell   The cell in which the label is drawn.
   */
  public void markCell(Player player, Box cell) {
    Text text = new Text(player.getInitials());
    text.setFill(player.getColor());
    text.setFont(Font.font(18));
    text.setX(cell.getUpperLeft().getLocation().getX() + DEFAULT_OFFSET / 2);
    text.setY(cell.getUpperLeft().getLocation().getY() + DEFAULT_OFFSET / 2);

    Mark mark = new Mark();
    mark.setX(text.getX());
    mark.setY(text.getY());
    mark.setText(text.getText());
    mark.setFontSize(text.getFont().getSize());
    mark.setColorOfPlayer(Game.getNumberOfTheCurrentPlayer());

    moves.get(moves.size() - 1).addMark(mark);

    cornersPane.getChildren().add(text);
  }


  public void decreaseRemainingMoves() {
    remainingMoves--;
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public void addSide(Side move) {
    sidesPane.getChildren().add(move);
  }

  public void botGoesFirst() {
    Bot.randomMove(this);
    moves.get(moves.size() - 1).setScores(Game.getScores());
    moves.get(moves.size() - 1).setCurrentPlayer(Game.getNumberOfTheCurrentPlayer());
    Game.changePlayer();
  }

  public void makeMoveBot(Corner first, Corner second) {
    Move move = new Move();
    Side side = new Side(first.getLocation(), second.getLocation());
    side.setStroke(Game.getCurrentPlayerColor());
    setBoxSides(first, second);
    decreaseRemainingMoves();
    move.setSide(side);
    moves.add(move);
    moves.get(moves.size() - 1).setCurrentPlayer(Game.getNumberOfTheCurrentPlayer());
    addSide(side);
  }

  public void addMark(Text mark) {
    sidesPane.getChildren().add(mark);
  }

  /**
   * If both players bot.
   */
  public void botVsBot() {
    while (remainingMoves > 0) {
      Bot.move(this);
      if (!checkOnCircledCells()) {
        moves.get(moves.size() - 1).setScores(Game.getScores());
        Game.changePlayer();
      }
      moves.get(moves.size() - 1).setScores(Game.getScores());
    }
    File.saveMoves(moves);
    System.out.println(moves.size());
    EndGameWindow.display(Game.getPlayers());
  }

  /**
   * @return true, if no more moves,and false, if moves left.
   */
  public boolean isEnd() {
    if (remainingMoves == 0)
      return true;
    else
      return false;
  }

  private boolean moveAlreadyBeen(Corner first, Corner second) {
    Point firstCoordinate = first.getLocation();
    Point secondCoordinate = second.getLocation();

    for (Move move : moves) {
      Point startCoordinate = move.getMove().getStartCoordinates();
      Point endCoordinate = move.getMove().getEndCoordinates();

      if (firstCoordinate.equals(startCoordinate) || secondCoordinate.equals(startCoordinate)) {
        if (firstCoordinate.equals(endCoordinate) || secondCoordinate.equals(endCoordinate))
          return true;
      }
    }
    return false;
  }
}
