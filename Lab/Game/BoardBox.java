package Game;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BoardBox extends StackPane {
  public BoardBox(BoardItem... items) {
    DropShadow shadow = new DropShadow(10, Color.BLACK);
    shadow.setSpread(0.8);

    Rectangle background = new Rectangle(300, 280);
    background.setOpacity(0.2);
    background.setEffect(shadow);

    VBox verticalLayout = new VBox();
    verticalLayout.setAlignment(Pos.CENTER);
    verticalLayout.setSpacing(20);
    verticalLayout.getChildren().add(new GameBoard());
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
}
