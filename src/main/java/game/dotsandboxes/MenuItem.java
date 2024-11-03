package game.dotsandboxes;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * This class create menu item(button and etc).
 */
public class MenuItem extends StackPane {
  public MenuItem(String name) {
    Stop[] stops = new Stop[] {new Stop(0, Color.BLACK), new Stop(0.2, Color.DARKCYAN)};
    LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

    Rectangle background = new Rectangle(300, 24);
    background.setOpacity(0.4);
    background.setFill(gradient);
    background.setVisible(false);
    background.setEffect(new DropShadow(5, 0, 5, Color.BLACK));

    Text text = new Text(name + "    ");
    text.setFill(Color.LIGHTGREY);
    text.setFont(Font.font(20));

    setAlignment(Pos.CENTER_RIGHT);
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
      background.setFill(gradient);
      text.setFill(Color.WHITE);
    });
  }
}
