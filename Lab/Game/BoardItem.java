package Game;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardItem extends StackPane {
  public BoardItem(String name) {
    DropShadow shadow = new DropShadow(10, Color.BLACK);
    shadow.setSpread(0.5);

    Rectangle background = new Rectangle(250, 24);
    background.setOpacity(0.4);
    background.setFill(Color.DARKCYAN);
    background.setEffect(shadow);
    background.setVisible(false);
    background.setEffect(new DropShadow(5, Color.BLACK));

    Text text = new Text(name);
    text.setFill(Color.LIGHTGREY);
    text.setFont(Font.font(20));

    setAlignment(Pos.CENTER);
    getChildren().addAll(background, text);

    setOnMouseEntered(event -> {
      background.setVisible(true);
      text.setFill(Color.WHITE);
    });
    setOnMouseExited(event -> {
      background.setVisible(false);
      text.setFill(Color.LIGHTGREY);
    });
    setOnMousePressed(event -> {
      background.setFill(Color.WHITE);
      text.setFill(Color.BLACK);
    });
    setOnMouseReleased(event -> {
      background.setFill(Color.BLACK);
      text.setFill(Color.WHITE);
    });
  }
}
