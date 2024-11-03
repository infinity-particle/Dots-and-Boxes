package game.dotsandboxes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuBox extends StackPane implements Constants {
  public MenuBox(String title, MenuItem... items) {
    DropShadow shadow = new DropShadow(7, 5, 0, Color.BLACK);
    shadow.setSpread(0.8);

    Rectangle background = new Rectangle(300, 300);
    background.setOpacity(0.2);
    background.setEffect(shadow);

    Text text = new Text(title + "    ");
    text.setFont(Font.font(20));
    text.setFill(Color.WHITE);

    Line horizontalSeparator = new Line();
    horizontalSeparator.setEndX(DEFAULT_HORIZONTAL_SEPARATOR_SIZE);
    horizontalSeparator.setStroke(Color.DARKGRAY);
    horizontalSeparator.setOpacity(0.4);

    Line verticalSeparator = new Line();
    verticalSeparator.setStartX(DEFAULT_VERTICAL_SEPARATOR_START_X);
    verticalSeparator.setEndX(DEFAULT_VERTICAL_SEPARATOR_END_X);
    verticalSeparator.setEndY(DEFAULT_VERTICAL_SEPARATOR_END_Y);
    verticalSeparator.setStroke(Color.DARKGRAY);
    verticalSeparator.setOpacity(0.4);

    VBox verticalLayout = new VBox();
    verticalLayout.setAlignment(Pos.TOP_RIGHT);
    verticalLayout.setPadding(new Insets(60, 0, 0, 0));
    verticalLayout.getChildren().addAll(text, horizontalSeparator);
    verticalLayout.getChildren().addAll(items);

    setAlignment(Pos.TOP_RIGHT);
    getChildren().addAll(background, verticalSeparator, verticalLayout);
  }
}
