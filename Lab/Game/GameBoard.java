package Game;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

public class GameBoard extends Pane implements Constants {
  private Circle[][] dots;
  private StackPane[][] boxes;
  private Line[][] verticalSides;
  private Line[][] horizontalSides;
  private int sizeOfGameBoard = DEFAULT_SIZE;
  private int distanceBetweenDots = DEFAULT_DISTANCE_BETWEEN_DOTS;
  private int numberOfDots;

  GameBoard() {
    numberOfDots = sizeOfGameBoard + 1;

    dots = new Circle[numberOfDots][numberOfDots];
    boxes = new StackPane[sizeOfGameBoard][sizeOfGameBoard];
    verticalSides = new Line[sizeOfGameBoard][sizeOfGameBoard + 1];
    horizontalSides = new Line[sizeOfGameBoard + 1][sizeOfGameBoard];

    createDots();
    createHorizontalLines();
    createVerticalLines();
    createBoxes();
  }

  GameBoard(int sizeOfGameBoard) {
    this.sizeOfGameBoard = sizeOfGameBoard;
    this.numberOfDots = this.sizeOfGameBoard + 1;

    dots = new Circle[numberOfDots][numberOfDots];
    boxes = new StackPane[this.sizeOfGameBoard][this.sizeOfGameBoard];
    verticalSides = new Line[this.sizeOfGameBoard][this.sizeOfGameBoard + 1];
    horizontalSides = new Line[this.sizeOfGameBoard + 1][this.sizeOfGameBoard];

    createDots();
    createHorizontalLines();
    createVerticalLines();
    createBoxes();
  }

  private void createDots() {
    int dotCoordinateX = 0;
    int dotCoordinateY = 0;

    for (int i = 0; i < numberOfDots; i++) {
      for (int j = 0; j < numberOfDots; j++) {
        dots[i][j] = new Circle(dotCoordinateX, dotCoordinateY, 5, Color.WHITE);
        getChildren().add(dots[i][j]);
        dotCoordinateX += distanceBetweenDots;
      }
      dotCoordinateY += distanceBetweenDots;
      dotCoordinateX = 0;
    }
  }

  private void createHorizontalLines() {
    for (int i = 0; i < numberOfDots; i++) {
      for (int j = 0; j < this.sizeOfGameBoard; j++) {
        double startX = dots[i][j].getCenterX();
        double startY = dots[i][j].getCenterY();
        double endX = dots[i][j + 1].getCenterX();
        double endY = dots[i][j + 1].getCenterY();
        final int finalJ = j;
        final int finalI = i;

        horizontalSides[i][j] = new Line(startX, startY, endX, endY);
        horizontalSides[i][j].setScaleY(4);
        horizontalSides[i][j].setStroke(Color.WHITE);
        horizontalSides[i][j].setOpacity(0.2);

        horizontalSides[i][j].setOnMouseEntered(event -> {
          horizontalSides[finalI][finalJ].setOpacity(0.8);
        });
        horizontalSides[i][j].setOnMouseExited(event -> {
          horizontalSides[finalI][finalJ].setOpacity(0.2);
        });
        horizontalSides[i][j].setOnMouseClicked(event -> {
          horizontalSides[finalI][finalJ].setOpacity(0.8);
          horizontalSides[finalI][finalJ].setOnMouseEntered(null);
          horizontalSides[finalI][finalJ].setOnMouseExited(null);
          horizontalSides[finalI][finalJ].setDisable(true);
        });

        getChildren().add(horizontalSides[i][j]);
      }
    }
  }

  private void createVerticalLines() {
    for (int j = 0; j < numberOfDots; j++) {
      for (int i = 0; i < this.sizeOfGameBoard; i++) {
        double startX = dots[i][j].getCenterX();
        double startY = dots[i][j].getCenterY();
        double endX = dots[i + 1][j].getCenterX();
        double endY = dots[i + 1][j].getCenterY();
        final int finalJ = j;
        final int finalI = i;

        verticalSides[i][j] = new Line(startX, startY, endX, endY);
        verticalSides[i][j].setScaleX(4);
        verticalSides[i][j].setStroke(Color.WHITE);
        verticalSides[i][j].setOpacity(0.2);

        verticalSides[i][j].setOnMouseEntered(event -> {
          verticalSides[finalI][finalJ].setOpacity(0.8);
        });
        verticalSides[i][j].setOnMouseExited(event -> {
          verticalSides[finalI][finalJ].setOpacity(0.2);
        });
        verticalSides[i][j].setOnMouseReleased(event -> {
          verticalSides[finalI][finalJ].setOpacity(0.8);
          verticalSides[finalI][finalJ].setOnMouseEntered(null);
          verticalSides[finalI][finalJ].setOnMouseExited(null);
          verticalSides[finalI][finalJ].setDisable(true);
        });

        getChildren().add(verticalSides[i][j]);
      }
    }
  }

  private void createBoxes() {
    for (int i = 0; i < this.sizeOfGameBoard; i++) {
      for (int j = 0; j < this.sizeOfGameBoard; j++) {
        boxes[i][j] = new StackPane();
        boxes[i][j].setMaxSize(distanceBetweenDots, distanceBetweenDots);
        boxes[i][j].setLayoutX(dots[i][j].getCenterX());
        boxes[i][j].setLayoutY(dots[i][j].getCenterY());
        getChildren().add(boxes[i][j]);
      }
    }
  }
}
