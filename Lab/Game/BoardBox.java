package Game;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BoardBox extends StackPane {
  private Pane gameBoard;
  private final int distance = 50;

  public BoardBox(BoardItem... items) {
    DropShadow shadow = new DropShadow(10, Color.BLACK);
    shadow.setSpread(0.8);

    Rectangle background = new Rectangle(300, 280);
    background.setOpacity(0.2);
    background.setEffect(shadow);

    createGameBoard();

    VBox verticalLayout = new VBox();
    verticalLayout.setAlignment(Pos.CENTER);
    verticalLayout.setSpacing(20);
    verticalLayout.getChildren().add(gameBoard);
    verticalLayout.getChildren().addAll(items);

    setAlignment(Pos.BOTTOM_CENTER);

    getChildren().addAll(background, verticalLayout);
  }

  public void hide() {
    TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), this);
    animation.setToY(-250);
    animation.setOnFinished(event -> setVisible(false));
    animation.play();
  }

  public void show() {
    setVisible(true);
    TranslateTransition animation = new TranslateTransition(Duration.seconds(0.5), this);
    animation.setFromX(100);
    animation.setToY(0);
    animation.play();
  }

  private void createGameBoard() {
    gameBoard = new Pane();

    Circle[][] dots = new Circle[4][4];
    StackPane[][] boxes = new StackPane[3][3];
    Line[][] verticalSides = new Line[3][4];
    Line[][] horizontalSides = new Line[4][3];

    int x = 0;
    int y = 0;

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        dots[i][j] = new Circle(x, y, 5, Color.WHITE);
        gameBoard.getChildren().add(dots[i][j]);
        x += distance;
      }
      y += distance;
      x = 0;
    }

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        double startX = dots[i][j].getCenterX();
        double startY = dots[i][j].getCenterY();
        double endX = dots[i][j + 1].getCenterX();
        double endY = dots[i][j + 1].getCenterY();

        horizontalSides[i][j] = new Line(startX, startY, endX, endY);
        horizontalSides[i][j].setScaleY(4);
        horizontalSides[i][j].setStroke(Color.WHITE);
        horizontalSides[i][j].setOpacity(0.2);

        final int finalJ = j;
        final int finalI = i;

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

        gameBoard.getChildren().add(horizontalSides[i][j]);
      }
    }

    for (int j = 0; j < 4; j++) {
      for (int i = 0; i < 3; i++) {
        double startX = dots[i][j].getCenterX();
        double startY = dots[i][j].getCenterY();
        double endX = dots[i + 1][j].getCenterX();
        double endY = dots[i + 1][j].getCenterY();

        verticalSides[i][j] = new Line(startX, startY, endX, endY);
        verticalSides[i][j].setScaleX(4);
        verticalSides[i][j].setStroke(Color.WHITE);
        verticalSides[i][j].setOpacity(0.2);

        final int finalJ = j;
        final int finalI = i;

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

        gameBoard.getChildren().add(verticalSides[i][j]);
      }
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        boxes[i][j] = new StackPane();
        boxes[i][j].setMaxSize(distance, distance);
        boxes[i][j].setLayoutX(dots[i][j].getCenterX());
        boxes[i][j].setLayoutY(dots[i][j].getCenterY());
        boxes[i][j].setAlignment(Pos.CENTER);
        Text text = new Text("TEST");
        boxes[i][j].getChildren().add(text);
        gameBoard.getChildren().add(boxes[i][j]);
      }
    }
  }
}
